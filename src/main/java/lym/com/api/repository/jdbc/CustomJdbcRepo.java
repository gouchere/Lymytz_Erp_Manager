package lym.com.api.repository.jdbc;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lym.com.api.service.commons.ConstantesManager;

@Repository
public class CustomJdbcRepo<T extends Serializable> {

	@Autowired
	@Qualifier("dataSource")
	DataSource ds;

	Class<T> type;
	String tableName;
	List<String> exclusions=new ArrayList<>();

	public List<List<Colonnes>> selectSqlData(String query) {
		List<List<Colonnes>> datas = new ArrayList<>();
		List<Colonnes> colonnes;
		try (Statement st = ds.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmt = rs.getMetaData();
			int columns = rsmt.getColumnCount();
			while (rs.next()) {
				int icol = 1;
				Colonnes colonne;
				colonnes = new ArrayList<>();
				while (icol <= columns) {
					colonne = new Colonnes();
					colonne.setName(rsmt.getColumnName(icol));
					colonne.setType(rsmt.getColumnTypeName(icol));
					colonne.setNameTable(rsmt.getTableName(icol));
					colonne.setNameTable(rsmt.getTableName(icol));
					colonne.setValue(rs.getObject(icol));
					colonnes.add(colonne);
					icol++;
				}
				datas.add(colonnes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datas;
	}

	public List<T> loadDataWithJdbc(Class<T> type, String query) {
		this.type = type;
		List<T> results = new ArrayList<>();
		if (controleEntity(type)) {
			List<List<Colonnes>> datas = selectSqlData(query);
			T entity;
			for (List<Colonnes> line : datas) {
				entity = giveInstance(type);
				exclusions.clear();
				exclusions.add(type.getName());
				results.add(fillDataWithJdbc(entity, line));
			}
		}
		return results;
	}

	public T fillDataWithJdbc(T entity, List<Colonnes> datas) {
		if (entity != null && datas != null) {
			Entity a = entity.getClass().getAnnotation(Entity.class);
			if (a != null) {
				for (Field f : entity.getClass().getDeclaredFields()) {
					f.setAccessible(true);
					if (hasColumOrJoinColum(f)) {
						if (!f.getType().getClass().equals(entity.getClass())) {
							buildField(entity, f, datas);
						} else {
							// ici, on peut charger seulement certaine propriétés
						}
					}

				}
			} else {
				System.err.println("La classe doit porter l'annotation @Entity");
	 		}

		}
		return entity;
	}

	private void buildField(T entity, Field champ, List<Colonnes> datas) {
		// vérifie si le champ est une classe
		String table = hasTableAnnotation(entity);
		try {
			if (hasEntityAnnotation(champ)) {
				/*
				 * construit l'objet avec ses propriété en excluant... on vérifie dabord que la
				 * colone est représenté dans la requete
				 */
				String s= getJoinColumnOrColumnNameAnnotation(champ);
				String tableAttribut=champ.getType().getName();
				int idx=datas.indexOf(new Colonnes(s, table));		
				if (idx>=0 && !exclusions.contains(tableAttribut)) {
					champ.set(entity, fillDataWithJdbc(giveInstance(champ), datas));
				}
				exclusions.add(tableAttribut);
			} else {
				Object o = getValue(table, champ, datas);
				champ.set(entity, o);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean hasColumOrJoinColum(Field f) {
		Annotation a = f.getAnnotation(Column.class);
		if (a != null) {
			return true;
		} else {
			a = f.getAnnotation(JoinColumn.class);
			if (a != null)
				return true;
		}
		return false;
	}

	private boolean hasEntityAnnotation(Field f) {
		Annotation a = f.getType().getAnnotation(Entity.class);
		if (a != null) {
			return true;
		} else {
			return false;
		}
	}

	private String hasTableAnnotation(T entity) {
		Table a = entity.getClass().getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		} else {
			return null;
		}
	}

	private String hasTableAnnotation(Class<T> entity) {
		Table a = entity.getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		} else {
			return null;
		}
	}

	private String getJoinColumnOrColumnNameAnnotation(Field f) {
		JoinColumn a = f.getAnnotation(JoinColumn.class);
		if (a != null) {
			return a.name();
		} else {
			Column c = f.getAnnotation(Column.class);
			if (c != null) {
				return c.name();
			}
		}
		return null;
	}

	private T giveInstance(Field f) {
		T o = null;
		try {
			Class c = Class.forName(f.getType().getName());
			Constructor[] ctors = c.getDeclaredConstructors();
			Constructor ctor = null;
			for (int i = 0; i < ctors.length; i++) {
				ctor = ctors[i];
				ctor.setAccessible(true);
				if (ctor.getGenericParameterTypes().length == 0)
					break;
			}
			ctor.setAccessible(true);
			o = (T) ctor.newInstance();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	private T giveInstance(Class<T> classe) {
		T o = null;
		try {
			Class c = Class.forName(classe.getName());
			Constructor[] ctors = c.getDeclaredConstructors();
			Constructor ctor = null;
			for (int i = 0; i < ctors.length; i++) {
				ctor = ctors[i];
				ctor.setAccessible(true);
				if (ctor.getGenericParameterTypes().length == 0)
					break;
			}
			ctor.setAccessible(true);
			o = (T) ctor.newInstance();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	private boolean controleEntity(Class<T> type) {
		if (type != null) {
			this.tableName = hasTableAnnotation(type);
			if (this.tableName != null) {
				return true;
			}
		}
		return false;
	}

	public Object getValue(String table, Field f, List<Colonnes> datas) {
		if (f != null) {
			String colName = null;
			// récupère la notation
			Column an = f.getAnnotation(Column.class);
			if (an != null) {
				colName = an.name();
			} else {
				System.err.println(" Not column ... " + f.getName());
			}
			if (ConstantesManager.isString(table) && ConstantesManager.isString(colName)) {
				Colonnes e = new Colonnes(colName, table);
				int idx = datas.indexOf(e);
				if (idx >= 0) {					
					if(!f.getType().getSimpleName().toLowerCase().equals("character")) {
						e = (datas.get(idx));
					}else {						
						e = (datas.get(idx));
						System.err.println(e.getValue());
					}
				}
				return (e != null) ? e.getValue() : null;
			}
		}
		return null;
	}

}

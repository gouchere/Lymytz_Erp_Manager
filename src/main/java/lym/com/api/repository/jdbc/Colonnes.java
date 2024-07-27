package lym.com.api.repository.jdbc;

import java.util.Objects;

public class Colonnes {
	String name;
	String type;
	String nameTable;
	Object value;

	public Colonnes() {
		// TODO Auto-generated constructor stub
	}
	public Colonnes(String name, String nameTable) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.nameTable=nameTable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, nameTable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colonnes other = (Colonnes) obj;
		return Objects.equals(name, other.name) && Objects.equals(nameTable, other.nameTable);
	}
	@Override
	public String toString() {
		return "Colonnes [name=" + name + ", type=" + type + ", nameTable=" + nameTable + ", value=" + value + "]";
	}
	
	

}

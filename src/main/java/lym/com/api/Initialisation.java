package lym.com.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import lym.com.api.service.commons.ConstantesManager;

public class Initialisation {
	public static Properties FILE_SERVEUR_CONFIG = new Properties();	

	public static void contextInitialized() {
		createConfigFile();
	}

	public static void reloadEntityConfig() {
		File file = new File("resources/manager_erp_entity.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
				try (InputStream is = new FileInputStream(file)) {
					FILE_SERVEUR_CONFIG.load(is);
				} catch (IOException e) {
					Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, e);
				}
			} catch (IOException ex) {
				Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			try (InputStream is = new FileInputStream(file)) {
				FILE_SERVEUR_CONFIG.load(is);
			} catch (IOException e) {
				Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	public static void reloadServicesConfig() {
		File file = new File("manager_erp_service.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
				try (InputStream is = new FileInputStream(file)) {
					FILE_SERVEUR_CONFIG.load(is);
				} catch (IOException e) {
					Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, e);
				}
			} catch (IOException ex) {
				Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			try (InputStream is = new FileInputStream(file)) {
				FILE_SERVEUR_CONFIG.load(is);
			} catch (IOException e) {
				Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	public static File createConfigFile() {
//		StringBuilder sb = new StringBuilder("lymytz").append(ConstantesManager.FILE_SEPARATOR).append("config")
//				.append(ConstantesManager.FILE_SEPARATOR).append("impl_services.properties");
		File file = new File("src" + ConstantesManager.FILE_SEPARATOR + "main" + ConstantesManager.FILE_SEPARATOR
				+ "resources" + ConstantesManager.FILE_SEPARATOR + "config" + ConstantesManager.FILE_SEPARATOR
				+ ConstantesManager.NAME_CONFIG_SERVEUR_APPS);
		System.err.println(file.getAbsolutePath() + " exist : " + file.exists());		
		if (!file.exists()) {
			try {
				file = new File("src" + ConstantesManager.FILE_SEPARATOR + "main" + ConstantesManager.FILE_SEPARATOR
						+ "resources" + ConstantesManager.FILE_SEPARATOR + "config");
				if (!file.exists()) {
					file.mkdir();
				} else {
					file = new File("src" + ConstantesManager.FILE_SEPARATOR + "main" + ConstantesManager.FILE_SEPARATOR
							+ "resources" + ConstantesManager.FILE_SEPARATOR + "config"
							+ ConstantesManager.FILE_SEPARATOR + ConstantesManager.NAME_CONFIG_SERVEUR_APPS);
					if (!file.exists()) {
						if (file.createNewFile()) {
							// initialise le fichier
							initFile(file);
						}
					}
				}

				System.err.println(file.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.err.println("File exist");

		}
		return file;
	}

	public static Properties readFileServeurPropertie() {
		File file = new File("src" + ConstantesManager.FILE_SEPARATOR + "main" + ConstantesManager.FILE_SEPARATOR
				+ "resources" + ConstantesManager.FILE_SEPARATOR + "config" + ConstantesManager.FILE_SEPARATOR
				+ ConstantesManager.NAME_CONFIG_SERVEUR_APPS);
		Properties result = new Properties();
		if (file.exists()) {
			try (InputStream is = new FileInputStream(file)) {
				result.load(is);
			} catch (IOException e) {
				Logger.getLogger(Initialisation.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return result;
	}

	private static void initFile(File file) {
		OutputStream os;
		try {
			os = new FileOutputStream(file);
			FILE_SERVEUR_CONFIG.put("adresse_web", "51.68.45.35");
			FILE_SERVEUR_CONFIG.put("port_web", "8080");
			FILE_SERVEUR_CONFIG.put("id_societe", "2296");
			FILE_SERVEUR_CONFIG.put("code_user", "GOUCHERE");
			FILE_SERVEUR_CONFIG.store(os, "Configuration du serveur");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeInFile(Properties bean) {
		OutputStream os;
		File file = new File("src" + ConstantesManager.FILE_SEPARATOR + "main" + ConstantesManager.FILE_SEPARATOR
				+ "resources" + ConstantesManager.FILE_SEPARATOR + "config" + ConstantesManager.FILE_SEPARATOR
				+ ConstantesManager.NAME_CONFIG_SERVEUR_APPS);
		try {
			os = new FileOutputStream(file);
			bean.store(os, "Config des ppropriétés du serveur");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

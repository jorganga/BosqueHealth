package model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

public class FileHandler {
	private static Scanner sc; // Ve el contenido del archivo
	private static PrintWriter pw; // Capacidad de escribir en arhivos
	private static File archivo; // Capacidad de manejar archivos
	private static final String FOLDER_NAME = "data";
	// lo siguientes son serializados
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static FileInputStream fis;
	private static final String CONFIG_FOLER_NAME = "config";
	
	public static void checkFolder() {
		archivo = new File(FOLDER_NAME);
		
		if (archivo.exists() && archivo.isDirectory()) {
			return;
		} else {
			archivo.mkdir(); // mkdirs Para Mac (no uses mac)
		}
		archivo = new File(CONFIG_FOLER_NAME);
		
		if (archivo.exists() && archivo.isDirectory()) {
			return;
		} else {
			archivo.mkdir(); // mkdirs Para Mac (no uses mac)
		}
	}

	public static void writeFile(String url, String content) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			//archivo = new File(url);
			if (!archivo.exists()) {
				archivo = new File(FOLDER_NAME + "/" + url);
				//archivo.createNewFile();
			}
			pw = new PrintWriter(archivo);
			pw.write(content);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al crear el archivo (archivo de texto)");
		}
	}

	public static String readFile(String url) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			} // Leer hasta EOF (end of file)
			String contenido = "";
			sc = new Scanner(archivo);
			while (sc.hasNext()) { // hasNext seguir lectura
				contenido += sc.nextLine() + "\n";
			}
			return contenido;
		} catch (IOException e) {
			System.out.println("Error en la lectura del archivo (archivo de texto)");
			// e.printStackTrace();
		}
		return null;
	}

	public static void writeSerializable(String url, Object content) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content);
			fos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al escribir en el archivo serializado.");
		}

	}

	public static Object readSerializable(String url) {
		try {
			archivo = new File(FOLDER_NAME + "/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}

			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			Object content = ois.readObject();
			fis.close();
			ois.close();
			return content;
		} catch (IOException e) {
			writeSerializable(url, null);
			//System.out.println("Error al leer el archivo serializado.");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		return null;
	}
	
	public static Properties loadProperties(String url) {
		Properties prop = null;
		prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIG_FOLER_NAME + "/" + url));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo y la url no existen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la lectura del archivo de propuedades" + url);
		}
		return prop;
	}
}



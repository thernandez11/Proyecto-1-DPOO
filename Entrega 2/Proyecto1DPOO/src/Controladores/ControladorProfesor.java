package Controladores;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import Componentes.Profesor;

public class ControladorProfesor {

	HashMap<String, Profesor> profesores;
	
	public ControladorProfesor() {
	    this.profesores = new HashMap<>();
	}
	
	public void CrearProfesor(String login, String password) {
		Profesor p = new Profesor(login, password);
		profesores.put(p.getLogin(), p);
	}
	
	public void MostrarProfesores() {
		Set<String> logins = profesores.keySet();
		Collection<Profesor> passwords = profesores.values();
		for (String login : logins) {
			System.out.println(login);
		}
		for (Profesor pass : passwords) {
			System.out.println(pass.getPassword());
		}
	}
	
	public boolean ExisteProfesor(String login) {
		return profesores.containsKey(login);
	}
	
	public boolean IngresoProfesor(String login, String password) {
		Profesor profesor = profesores.get(login);
		if (password.equals(profesor.getPassword())) {
			return true;
		}
		return false;
	}
	
	// Guardar profesores en un archivo
    public void guardarProfesoresEnArchivo(String nombreArchivo) throws IOException {
        String directorioRelativo = "Persistencia"; 
        File directorio = new File(directorioRelativo);
        
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivo = new File(directorio, nombreArchivo);

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) { // Modo apéndice
            for (Profesor profesor : profesores.values()) {
                writer.println(profesor.getLogin() + "," + profesor.getPassword());
            }
            System.out.println("Datos de profesores guardados exitosamente en " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
            throw e;
        }
    }

    // Cargar profesores desde un archivo
    public void cargarProfesoresDesdeArchivo(String nombreArchivo) throws IOException {
        if (profesores == null) {
            profesores = new HashMap<>();
        }

        String directorioRelativo = "Persistencia"; 
        File archivo = new File(directorioRelativo, nombreArchivo);

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length == 2) {
                    String login = partes[0];
                    String password = partes[1];
                    profesores.put(login, new Profesor(login, password)); 
                }
            }
            System.out.println("Datos de profesores cargados exitosamente desde " + archivo.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará al guardar.");
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            throw e;
        }
    }
}

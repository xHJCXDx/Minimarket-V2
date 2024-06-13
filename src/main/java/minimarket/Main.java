package minimarket;

import minimarket.gui.Menu;
import minimarket.herramientas.Funciones;
import minimarket.persistencia.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            try {
                DAO dao = new DAO(){};
                boolean HayConexion = dao.testConnection();
                boolean continuar;
                do{
                    if(!HayConexion){
                        JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
                        continuar= Funciones.CuadroDialogoVoF("Reconectar:","Base de datos");
                        if (!continuar){
                            System.exit(0);
                        }
                    }
                    HayConexion = dao.testConnection();
                }while(!HayConexion);

            }catch (Exception e) {
                System.out.println("Error al unirse a la base de datos: " + e.getMessage());
            }
            try {
                boolean crear_Tabla=Funciones.CuadroDialogoVoF("Crear tablas:","Base de datos");
                if(crear_Tabla){
                    DAO dao = new DAO();
                    dao.crearTablas();
                }
            } catch (Exception e) {
                System.out.println("Error al crear las tablas: " + e.getMessage());
            }

            try {
                Menu menu = new Menu();
                menu.mostrarMenu();
            } catch (Exception e) {
                System.out.println("Error al crear las tablas: " + e.getMessage());
            }
        } finally { // Se cierra la conexión con la base de datos.
            System.out.println("Finalizado");
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}



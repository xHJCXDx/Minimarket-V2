package minimarket.gui;

import minimarket.herramientas.Funciones;

import javax.swing.*;

public class Menu {
    public void mostrarMenu(){
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Usuario", "Aministrador"};
        String mensaje = "Ingresar como:";
        String titulo ="LOGIN";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    Menu_Usuario menu_Usuario = new Menu_Usuario();
                    menu_Usuario.mostrarMenuPrincipal();
                    break;
                case 2:
                    String usuario = Funciones.InputDialogNoVacio("Usuario:");
                    String contrasenia = Funciones.InputDialogNoVacio("Contraseña:");
                    boolean Resultado = Funciones.ComparacionString(usuario, "admin");
                    boolean Resultado1 = Funciones.ComparacionString(contrasenia, "admin");
                    if(Resultado && Resultado1){
                        Menu_Administrador menu_administrador = new Menu_Administrador();
                        menu_administrador.mostrarMenuPrincipal();
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña o Usuario incorrecto");
                        System.out.println("Contraseña o Usuario incorrecto");
                        mostrarMenu();
                    }
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }
}

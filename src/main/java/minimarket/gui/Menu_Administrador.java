package minimarket.gui;

import minimarket.controladores.AdministradorControlador;
import minimarket.herramientas.Funciones;

//Usuario: admin
//Contraseña: admin

public class Menu_Administrador {
    AdministradorControlador controlador = new AdministradorControlador();
    public void mostrarMenuPrincipal() {
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Clientes", "Platos", "Empleado", "Producto", "Proveedor", "Volver"};
        String mensaje = "Clientes: Menu Clientes\nComanda: Menu Comanda\nEmpleado: Menu Empleado\nProducto: Menu Producto\nProveedor: Menu Proveedor\nVolver: Volver al menu de USUARIOS";
        String titulo ="MENU GENERAL ADMINISTRADOR";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuPlato();
                    break;
                case 3:
                    mostrarMenuEmpleado();
                    break;
                case 4:
                    mostrarMenuProducto();
                    break;
                case 5:
                    mostrarMenuProveedor();
                    break;
                case 6:
                    Menu menu = new Menu();
                    menu.mostrarMenu();
                    System.out.println("Volviendo al menu de USUARIOS...");
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V

    }

    private void mostrarMenuClientes() {
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Ingresar", "Modificar", "Eliminar", "Mostrar", "Volver"};
        String mensaje = "Ingresar: Ingresar Cliente\nModificar: Modificar Cliente\nEliminar: Eliminar Cliente\nMostrar: Mostrar Todos los Clientes\nVolver: Volver al Menú Principal";
        String titulo ="MENU CLIENTES";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.Ingresar_cliente();
                    break;
                case 2:
                    controlador.Modificar_cliente();
                    break;
                case 3:
                    controlador.Eliminar_cliente();
                    break;
                case 4:
                    controlador.Mostrar_clientes();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }

    private void mostrarMenuPlato(){

        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Ingresar", "Modificar", "Eliminar", "Mostrar", "Volver"};
        String mensaje = "Ingresar: Ingresar Plato\nModificar: Modificar Plato\nEliminar: Eliminar Plato\nMostrar: Mostrar Todos los Platos\nVolver: Volver al Menú Principal";
        String titulo ="MENU PLATOS";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.Ingresar_plato();
                    break;
                case 2:
                    controlador.Modificar_plato();
                    break;
                case 3:
                    controlador.Eliminar_plato();
                    break;
                case 4:
                    controlador.Mostrar_platos();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }

    private void mostrarMenuEmpleado(){
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Ingresar", "Modificar", "Eliminar", "Mostrar", "Volver"};
        String mensaje = "Ingresar: Ingresar Empleados\nModificar: Modificar Empleados\nEliminar: Eliminar Empleados\nMostrar: Mostrar Todos los Empleados\nVolver: Volver al Menú Principal";
        String titulo ="MENU EMPLEADOS";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;

            switch (opcion) {
                case 1:
                    controlador.Ingresar_empleado();
                    break;
                case 2:
                    controlador.Modificar_empleado();
                    break;
                case 3:
                    controlador.Eliminar_empleado();
                    break;
                case 4:
                    controlador.Mostrar_empleados();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }

    private void mostrarMenuProducto(){
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Ingresar", "Modificar", "Eliminar", "Mostrar", "Volver"};
        String mensaje = "Ingresar: Ingresar Producto\nModificar: Modificar Producto\nEliminar: Eliminar Producto\nMostrar: Mostrar Todos los Productos\nVolver: Volver al Menú Principal";
        String titulo ="MENU PRODUCTOS";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.Ingresar_producto();
                    break;
                case 2:
                    controlador.Modificar_producto();
                    break;
                case 3:
                    controlador.Eliminar_producto();
                    break;
                case 4:
                    controlador.Mostrar_productos();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }

    private void mostrarMenuProveedor(){
        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Ingresar", "Modificar", "Eliminar", "Mostrar", "Volver"};
        String mensaje = "Ingresar: Ingresar Proveedor\nModificar: Modificar Proveedor\nEliminar: Eliminar Proveedor\nMostrar: Mostrar Todos los Proveedores\nVolver: Volver al Menú Principal";
        String titulo ="MENU PROVEEDORES";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.Ingresar_proveedor();
                    break;
                case 2:
                    controlador.Modificar_proveedor();
                    break;
                case 3:
                    controlador.Eliminar_proveedor();
                    break;
                case 4:
                    controlador.Mostrar_proveedores();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("Cerrando la sistema...");
                    System.exit(0);
                    break;
            }
        }while(opcion != -1);//V
    }

}

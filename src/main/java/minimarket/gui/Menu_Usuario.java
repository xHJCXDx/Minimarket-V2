package minimarket.gui;

import minimarket.controladores.UsuarioControlador;
import minimarket.herramientas.Funciones;

public class Menu_Usuario {

    public void mostrarMenuPrincipal() {
        UsuarioControlador controlador = new UsuarioControlador();

        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Vender", "Ingresar", "Pago", "Consulta", "Balance", "Comanda", "Información","Volver" };
        String mensaje = "1.Vender: Vender/Cobrar un producto\n2.Ingresar: Ingreso de mercadería\n3.Pago: Pagar cuenta\n4.Consulta: Consulta de ventas\n5.Balance: Balance(mostrar ganancias y pérdidas)\n6.Comanda: Solicitar una comanda a la cocina.\n7.Información: Informacion estadística de platos más pedidos.\n8.Volver: Volver al menu de USUARIO.";
        String titulo ="MENU USUARIO";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.Vender();
                    break;
                case 2:
                    controlador.Ingresar();
                    break;
                case 3:
                    controlador.Pago_C();
                    break;
                case 4:
                    ConsultasDeVentas();
                    break;
                case 5:
                    controlador.Balance();
                    break;
                case 6:
                    controlador.Comanda();
                    break;
                case 7:
                    controlador.Informacion();
                    break;
                case 8:
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

    private void ConsultasDeVentas() {
        UsuarioControlador controlador = new UsuarioControlador();

        int opcion = -2; //Opcion a elegir
        String[] Botones = new String[] {"Diaria", "Mensual","Volver"};
        String mensaje = "";
        String titulo ="Consulta";

        //------------------------------------OPCIONES-------------------------------------------
        do{
            opcion = Funciones.OpcionesMenu(Botones, mensaje, titulo)+1;
            switch (opcion) {
                case 1:
                    controlador.ConsultaDia();
                    break;
                case 2:
                    controlador.ConsultaMen();
                    break;
                case 3:
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

package minimarket.controladores;

import minimarket.herramientas.Funciones;
import minimarket.modelo.*;

import javax.swing.*;
import java.util.ArrayList;

public class AdministradorControlador {
    ClienteModelo cliente = new ClienteModelo();
    EmpleadoModelo empleado = new EmpleadoModelo();
    PlatoModelo plato = new PlatoModelo();
    ProductoModelo producto = new ProductoModelo();
    ProveedorModelo proveedor = new ProveedorModelo();
    //Incertar datos
    public void Ingresar_cliente(){
        try {
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            String contacto = Funciones.InputDialogNoVacio("Contacto:");
            cliente.ingresarCliente(nombre,contacto);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al crear el cliente");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Ingresar_empleado(){
        try {
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            empleado.ingresarEmpleado(nombre);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al crear el empleado");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Ingresar_plato(){
        try {
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            double precio = Funciones.LimitacionNumericaDouble("Precio:","PRECIO",999999.0,0.0);
            plato.ingresarPlato(nombre, precio);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al crear el plato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Ingresar_producto(){
        try {
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            double precio = Funciones.LimitacionNumericaDouble("Precio:","PRECIO",999999.0,0.0);
            int stock = Funciones.LimitacionNumericaInt("Stock:","STOCK",999999,0);
            producto.ingresarProducto(nombre, stock, precio);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al crear el producto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Ingresar_proveedor(){
        try {
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            String contacto = Funciones.InputDialogNoVacio("Contacto:");
            double pago = Funciones.LimitacionNumericaDouble("Pago:","PAGO",999999.0,0.0);
            double cuentaProveedor = Funciones.LimitacionNumericaDouble("Cuenta:","CUENTA",999999.0,0.0);
            proveedor.ingresarProveedor(nombre, contacto, cuentaProveedor, pago);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al crear el proveedor");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //Modificar datos
    public void Modificar_cliente(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            String contacto = Funciones.InputDialogNoVacio("Contacto:");
            cliente.modificarCliente(id, nombre, contacto);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el cliente");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Modificar_empleado(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            empleado.modificarEmpleado(id,nombre);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el empleado");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Modificar_plato(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            double precio = Funciones.LimitacionNumericaDouble("Precio:","PRECIO",999999.0,0.0);
            plato.modificarPlato(id,nombre, precio);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el plato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Modificar_producto(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            double precio = Funciones.LimitacionNumericaDouble("Precio:","PRECIO",999999.0,0.0);
            int stock = Funciones.LimitacionNumericaInt("Stock:","STOCK",999999,0);
            producto.modificarProducto(id,nombre, stock, precio);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el producto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Modificar_proveedor(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            String nombre = Funciones.InputDialogNoVacio("Nombre:");
            String contacto = Funciones.InputDialogNoVacio("Contacto:");
            double pago = Funciones.LimitacionNumericaDouble("Pago:","PAGO",999999.0,0.0);
            double cuentaProveedor = Funciones.LimitacionNumericaDouble("Cuenta:","CUENTA",999999.0,0.0);
            proveedor.modificarProveedor(id,nombre, contacto, cuentaProveedor, pago);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al modificar el proveedor");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //Mostrar datos
    public void Mostrar_clientes(){
        try {
            System.out.println("Clientes:");
            StringBuilder sb = new StringBuilder();
            ArrayList<String> clientes = cliente.listarClientes();
            for (String i : clientes) {
                System.out.println(i);
                sb.append(i).append("\n");
            }
            String resultado = sb.toString().replace(",", "|");
            // Mostrar el ArrayList en un JOptionPane
            JOptionPane.showMessageDialog(null, resultado, "CLIENTES", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los clientes");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Mostrar_empleados(){
        try {
            System.out.println("Empleados:");
            StringBuilder sb = new StringBuilder();
            ArrayList<String> empleados = empleado.listarEmpleados();
            for (String i : empleados) {
                System.out.println(i);
                sb.append(i).append("\n");
            }
            String resultado = sb.toString().replace(",", "|");
            // Mostrar el ArrayList en un JOptionPane
            JOptionPane.showMessageDialog(null, resultado, "EMPLEADOS", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los empleados");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Mostrar_platos(){
        try {
            System.out.println("Platos:");
            StringBuilder sb = new StringBuilder();
            ArrayList<String> platos = plato.listarPlatos();
            for (String i : platos) {
                System.out.println(i);
                sb.append(i).append("\n");
            }

            String resultado = sb.toString().replace(",", "|");
            // Mostrar el ArrayList en un JOptionPane
            JOptionPane.showMessageDialog(null, resultado, "PLATOS", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los platos");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Mostrar_productos(){
        try {
            System.out.println("Productos:");
            StringBuilder sb = new StringBuilder();
            ArrayList<String> productos = producto.listarProductos();
            for (String i : productos) {
                System.out.println(i);
                sb.append(i).append("\n");
            }
            String resultado = sb.toString().replace(",", "|");
            // Mostrar el ArrayList en un JOptionPane
            JOptionPane.showMessageDialog(null, resultado, "PRODUCTOS", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los productos");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Mostrar_proveedores(){
        try {
            System.out.println("Proveedores:");
            StringBuilder sb = new StringBuilder();
            ArrayList<String> proveedores = proveedor.listarProveedores();
            for (String i : proveedores) {
                System.out.println(i);
                sb.append(i).append("\n");
            }
            String resultado = sb.toString().replace(",", "|");
            // Mostrar el ArrayList en un JOptionPane
            JOptionPane.showMessageDialog(null, resultado, "PROVEEDORES", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los proveedores");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Eliminar datos
    public void Eliminar_cliente(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            cliente.eliminarCliente(id);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar el cliente");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Eliminar_empleado(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            empleado.eliminarEmpleado(id);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar el empleado");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Eliminar_plato(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            plato.eliminarPlato(id);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar el plato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Eliminar_producto(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            producto.eliminarProducto(id);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar el producto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Eliminar_proveedor(){
        try {
            int id = Funciones.LimitacionNumericaInt("Id:","ID",999999,1);
            proveedor.eliminarProveedor(id);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar el proveedor");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}

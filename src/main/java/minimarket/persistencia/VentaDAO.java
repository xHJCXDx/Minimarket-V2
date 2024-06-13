package minimarket.persistencia;

import minimarket.modelo.VentaModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VentaDAO extends DAO {

    public VentaDAO (VentaModelo venta) {
    }

    public void ingresarVenta(String fecha, int idCliente, int idEmpleado) throws Exception {
        //Ingresar venta
        String sql = "INSERT INTO Venta (fecha, id_Cliente, id_Empleado) VALUES ('" + fecha + "', " + idCliente + ", " + idEmpleado + ")";
        ejecutarSQL(sql);
    }

    public void eliminarVenta(int id) throws Exception {
        // Eliminar pedidos de platos relacionados
        String sqlEliminarPedidosPlato = "DELETE FROM PedidoPlato WHERE id_Venta = " + id;
        ejecutarSQL(sqlEliminarPedidosPlato);

        // Eliminar pedidos de productos relacionados
        String sqlEliminarPedidosProducto = "DELETE FROM PedidoProducto WHERE id_Venta = " + id;
        ejecutarSQL(sqlEliminarPedidosProducto);

        //Luego eliminar venta
        String sqlEliminarVenta = "DELETE FROM Venta WHERE id_Venta = " + id;
        ejecutarSQL(sqlEliminarVenta);
    }
    public void modificarVenta(int idVenta, String fecha, int idCliente, int idEmpleado) throws Exception {
        String sql = "UPDATE Venta SET fecha = '" + fecha + "', id_Cliente = " + idCliente + ", id_Empleado = " + idEmpleado + ", " +
                " WHERE id_Venta = " + idVenta;
        ejecutarSQL(sql);
    }

    public String mostrarVentaPorId(int id) throws Exception {
        String sql = "SELECT * FROM Venta WHERE id_Venta = " + id;
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            return "ID: " + resultado.getInt("id_Venta") + ", Fecha: " + resultado.getString("fecha") + ", ID Cliente: " + resultado.getInt("id_Cliente") + ", ID Empleado: " + resultado.getInt("id_Empleado");
        }
        return null;
    }


    public ArrayList<String> buscarVentas() throws Exception {
        ArrayList<String> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String venta = "ID: " + resultado.getInt("id_Venta") + ", Fecha: " + resultado.getString("fecha") + ", ID Cliente: " + resultado.getInt("id_Cliente") + ", ID Empleado: " + resultado.getInt("id_Empleado") + ", ID Pedido Producto: " + resultado.getInt("id_Pedido_Producto") + ", ID Pedido Plato: " + resultado.getInt("id_Pedido_Plato");
            ventas.add(venta);
        }
        desconectarBase();
        return ventas;
    }
    public ArrayList<VentaModelo> obtenerVentasTodas() throws Exception {
        ArrayList<VentaModelo> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            VentaModelo venta = new VentaModelo();
            venta.setId_venta(resultado.getInt("id_Venta"));
            venta.setFecha(resultado.getDate("fecha"));
            venta.setId_cliente(resultado.getInt("id_Cliente"));
            venta.setId_empleado(resultado.getInt("id_Empleado"));
            ventas.add(venta);
        }
        desconectarBase();
        return ventas;
    }
    public ArrayList<VentaModelo> obtenerVentasDiarias(String fecha) throws Exception {
        ArrayList<VentaModelo> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE fecha = '"+ fecha +"'";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            VentaModelo venta = new VentaModelo();
            venta.setId_venta(resultado.getInt("id_Venta"));
            venta.setFecha(resultado.getDate("fecha"));
            venta.setId_cliente(resultado.getInt("id_Cliente"));
            venta.setId_empleado(resultado.getInt("id_Empleado"));
            ventas.add(venta);
        }
        desconectarBase();
        return ventas;
    }
    public ArrayList<VentaModelo> obtenerVentasMensuales(String fech) throws Exception {
        ArrayList<VentaModelo> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE fecha like '"+ fech + "%'";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            VentaModelo venta = new VentaModelo();
            venta.setId_venta(resultado.getInt("id_Venta"));
            venta.setFecha(resultado.getDate("fecha"));
            venta.setId_cliente(resultado.getInt("id_Cliente"));
            venta.setId_empleado(resultado.getInt("id_Empleado"));
            ventas.add(venta);
        }
        desconectarBase();
        return ventas;
    }
}

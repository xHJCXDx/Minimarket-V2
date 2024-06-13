package minimarket.persistencia;

import minimarket.modelo.PedidoPlatoModelo;
import minimarket.modelo.PedidoProductoModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoProductoDAO extends DAO {

    public PedidoProductoDAO(PedidoProductoModelo ProductoModelo) {
    }

    public void ingresarPedidoProducto(int idventa, int idProducto, int cantidad) throws Exception {
        String sql = "INSERT INTO PedidoProducto (id_venta, id_Producto, cantidad) VALUES (" + idventa +"," + idProducto + ", " + cantidad + ")";
        ejecutarSQL(sql);
    }

    public void modificarPedidoProducto(int id, int idProducto, int cantidad) throws Exception {
        String sql = "UPDATE PedidoProducto SET id_Producto = " + idProducto + ", cantidad = " + cantidad + " WHERE id_Pedido_Producto = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarPedidoProductoPorVenta(int id) throws Exception {
        String sql = "DELETE FROM PedidoProducto WHERE id_Venta = " + id;
        ejecutarSQL(sql);
    }

    public ArrayList<PedidoProductoModelo> ObtenerPedidoProductoPorVenta(int id) throws Exception {
        String sql = "SELECT * FROM PedidoProducto WHERE id_Venta = " + id;
        ArrayList<PedidoProductoModelo> productos = new ArrayList<>();
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            PedidoProductoModelo producto = new PedidoProductoModelo();
            producto.setId_PedidoProducto(resultado.getInt("id_Pedido_Producto"));
            producto.setId_Producto(resultado.getInt("id_Producto"));
            producto.setCantidad(resultado.getInt("cantidad"));
            productos.add(producto);
        }
        return productos;
    }


    public ArrayList<PedidoProductoModelo> ObtenerPedidostodos() throws Exception {
        ArrayList<PedidoProductoModelo> pedidosProducto = new ArrayList<>();
        String sql = "SELECT * FROM PedidoProducto";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            PedidoProductoModelo modelo = new PedidoProductoModelo();
            modelo.setId_PedidoProducto(resultado.getInt("id_Pedido_Producto"));
            modelo.setCantidad(resultado.getInt("cantidad"));
            modelo.setId_Producto(resultado.getInt("id_Producto"));
            pedidosProducto.add(modelo);
        }
        desconectarBase();
        return pedidosProducto;
    }

    public ArrayList<String> listarPedidosProducto() throws Exception {
        ArrayList<String> pedidosProducto = new ArrayList<>();
        String sql = "SELECT * FROM PedidoProducto";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String pedidoProducto = "ID: " + resultado.getInt("id_Pedido_Producto") + ", ID Producto: " + resultado.getInt("id_Producto") + ", Cantidad: " + resultado.getString("cantidad");
            pedidosProducto.add(pedidoProducto);
        }
        desconectarBase();
        return pedidosProducto;
    }
}

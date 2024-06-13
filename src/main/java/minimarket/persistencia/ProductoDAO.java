package minimarket.persistencia;

import minimarket.modelo.ProductoModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDAO extends DAO {

    public ProductoDAO(ProductoModelo producto) {
    }

    public void ingresarProducto(String nombre, int stock, double precio) throws Exception {
        String sql = "INSERT INTO Producto (nombre, stock, precio) VALUES ('" + nombre + "', " + stock + ", " + precio + ")";
        ejecutarSQL(sql);
    }

    public void modificarProducto(int id, String nombre, int stock, double precio) throws Exception {
        String sql = "UPDATE Producto SET nombre = '" + nombre + "', stock = " + stock + ", precio = " + precio + " WHERE id_Producto = " + id;
        ejecutarSQL(sql);
    }
    public void modificarStock(int id, int stock) throws Exception {
        String sql = "UPDATE Producto SET stock = " + stock + " WHERE id_Producto = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarProducto(int id) throws Exception {
        // Eliminar pedidos de productos relacionados
        String sqlEliminarPedidosProducto = "DELETE FROM PedidoProducto WHERE id_Producto = " + id;
        ejecutarSQL(sqlEliminarPedidosProducto);

        // Luego eliminar el producto
        String sqlEliminarProducto = "DELETE FROM Producto WHERE id_Producto = " + id;
        ejecutarSQL(sqlEliminarProducto);
    }


    public String buscarProductoPorId(int id) throws Exception {
        String sql = "SELECT * FROM Producto WHERE id_Producto = " + id;
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            return "ID: " + resultado.getInt("id_Producto") + ", Nombre: " + resultado.getString("nombre") + ", Stock: " + resultado.getInt("stock") + ", Precio: " + resultado.getDouble("precio");
        }
        return null;
    }
    public ProductoModelo buscarProductoPorNombre(String Nombre) throws Exception {
        ProductoModelo ProductoBuscado = new ProductoModelo();
        String sql = "SELECT * FROM Producto WHERE Nombre = '" + Nombre+"'";
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            ProductoBuscado.setId_Producto(resultado.getInt("id_Producto"));
            ProductoBuscado.setNombre(resultado.getString("nombre"));
            ProductoBuscado.setStock(resultado.getInt("stock"));
            ProductoBuscado.setPrecio(resultado.getDouble("precio"));
        }
        return ProductoBuscado;
    }

    public ArrayList<String> buscarProductos() throws Exception {
        ArrayList<String> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String producto = "ID: " + resultado.getInt("id_Producto") + ", Nombre: " + resultado.getString("nombre") + ", Stock: " + resultado.getInt("stock") + ", Precio: " + resultado.getDouble("precio");
            productos.add(producto);
        }
        desconectarBase();
        return productos;
    }
}

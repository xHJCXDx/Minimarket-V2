package minimarket.modelo;

import minimarket.persistencia.ProductoDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoModelo {
    private int id_Producto;
    private String nombre;
    private int stock;
    private double precio;
    private ProductoDAO productodao = new ProductoDAO(this);

    public ProductoModelo() {
    }

    public ProductoModelo(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }


    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }
    public int getId_Producto() {
        return id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void ingresarProducto(String nombre, int stock, double precio) throws Exception {
        productodao.ingresarProducto(nombre, stock, precio);
    }

    public void modificarProducto(int id, String nombre, int stock, double precio) throws Exception {
        productodao.modificarProducto(id, nombre,stock,precio);
    }


    public void eliminarProducto(int id) throws Exception {
        productodao. eliminarProducto(id);
    }

    public ProductoModelo obtenerProducto(int id) throws Exception {
        return productodao.obtenerProductoPorId(id);
    }

    public ArrayList<ProductoModelo> obtenerProductosTodos() throws Exception {
        return productodao.obtenerProductoTodos();
    }
    public String mostrarProductoPorId(int id) throws Exception {
        return productodao.mostrarProductoPorId(id);
    }

    public ArrayList<String> listarProductos() throws Exception {
        return productodao.listarProductos();
    }

    public void modificarStock(String nombre, int cantidadIngresada) throws Exception {
        ProductoModelo productoModificar = productodao.buscarProductoPorNombre(nombre);
        int stockNuevo = productoModificar.getStock()+cantidadIngresada;
        productodao.modificarStock(productoModificar.getId_Producto(), stockNuevo);
    }
}

package minimarket.modelo;

import minimarket.persistencia.PedidoProductoDAO;

import java.util.ArrayList;

public class PedidoProductoModelo {
    private int id_PedidoProducto;
    private int id_venta;
    private int id_Producto;
    private int cantidad;
    private ProductoModelo productoPedido;
    private final PedidoProductoDAO pedidoProductodao = new PedidoProductoDAO(this);

    public PedidoProductoModelo() {
    }

    public PedidoProductoModelo(int id_venta, int id_Producto, int cantidad) {
        this.id_venta = id_venta;
        this.id_Producto = id_Producto;
        this.cantidad = cantidad;
    }

    public int getId_PedidoProducto() {
        return id_PedidoProducto;
    }

    public void setId_PedidoProducto(int id_PedidoProducto) {
        this.id_PedidoProducto = id_PedidoProducto;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void ingresarPedidoProducto(int idventa, int idProducto, int cantidad) throws Exception {
        pedidoProductodao.ingresarPedidoProducto(idventa, idProducto, cantidad);
    }
    public PedidoProductoModelo buscarPedidoPlatoPorVenta(int id_venta) throws Exception {
        return pedidoProductodao.buscarPedidoProductoPorVenta(id_venta);
    }
    public double precioPedidoProducto(int id_venta) throws Exception {
        PedidoProductoModelo modelo = new PedidoProductoModelo();
        modelo = buscarPedidoPlatoPorVenta(id_venta);
        ProductoModelo producto = new ProductoModelo();
        producto.buscarProductoPorId(modelo.id_Producto);
        return producto.getPrecio()*modelo.cantidad;
    }
    public ArrayList<PedidoProductoModelo> ObtenerPedidostodos() throws Exception {
        return pedidoProductodao.ObtenerPedidostodos();
    }
    public double ventasProductoTodas() throws Exception {
        double ventaProductos = 0;
        ArrayList<PedidoProductoModelo> modelo = ObtenerPedidostodos();
        for(PedidoProductoModelo i: modelo){
            ventaProductos += i.precioPedidoProducto(i.id_venta);
        }
        return ventaProductos;
    }
}

package minimarket.modelo;

import minimarket.persistencia.PedidoProductoDAO;

import java.util.ArrayList;

public class PedidoProductoModelo {
    private int id_PedidoProducto;
    private int id_venta;
    private int id_Producto;
    private int cantidad;
    private ProductoModelo productoPedido;
    private VentaModelo venta;
    private PedidoProductoDAO pedidoProductodao = new PedidoProductoDAO(this);

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
        ProductoModelo productoVendido = new ProductoModelo();
        productoVendido.obtenerProducto(idProducto);
        productoVendido.modificarStock(productoVendido.getNombre(),(-cantidad));
    }
    public void modificarPedidoProducto(int id, int idProducto, int cantidad) throws Exception {
        pedidoProductodao.modificarPedidoProducto(id, idProducto, cantidad);
    }
    public void eliminarPedidoProductoPorVenta(int id) throws Exception {
        pedidoProductodao.eliminarPedidoProductoPorVenta(id);
        ArrayList<PedidoProductoModelo> productoEliminado = new ArrayList<>();
        productoEliminado = ObtenerPedidoProductoPorVenta(id);
        for (PedidoProductoModelo i: productoEliminado) {
            ProductoModelo producto = new ProductoModelo();
            producto.obtenerProducto(i.id_Producto);
            producto.modificarStock(producto.getNombre(),(i.cantidad));
        }
    }

    public ArrayList<PedidoProductoModelo> ObtenerPedidoProductoPorVenta(int id_venta) throws Exception {
        return pedidoProductodao.ObtenerPedidoProductoPorVenta(id_venta);
    }
    public ArrayList<PedidoProductoModelo> ObtenerPedidostodos() throws Exception {
        return pedidoProductodao.ObtenerPedidostodos();
    }

    public String mostrarPedidoProducto(int id_Venta) throws Exception {
        ArrayList<PedidoProductoModelo> modelo = new ArrayList<>();
        modelo = ObtenerPedidoProductoPorVenta(id_Venta);
        String detalleVenta = "";
        for(PedidoProductoModelo i: modelo){
            detalleVenta="Cantidad "+ i.cantidad;
            ProductoModelo producto = new ProductoModelo();
            producto.obtenerProducto(i.id_Producto);
            detalleVenta += "  Producto: "+producto.getNombre()+"  Precio: "+producto.getPrecio();
        }
        return detalleVenta;
    }

    public double precioPedidoProducto(int id_venta) throws Exception {
        ArrayList<PedidoProductoModelo> modelo = new ArrayList<>();
        modelo = ObtenerPedidoProductoPorVenta(id_venta);
        double precioPedidosPorVenta = 0;
        for(PedidoProductoModelo i: modelo){
            ProductoModelo producto = new ProductoModelo();
            producto.obtenerProducto(i.id_Producto);
            precioPedidosPorVenta += producto.getPrecio()*i.cantidad;
        };
        return precioPedidosPorVenta;
    }

    public double ventasProductoTodas() throws Exception {
        double ventaProductos = 0;
        ArrayList<PedidoProductoModelo> modelo = ObtenerPedidostodos();
        for(PedidoProductoModelo i: modelo){
            ProductoModelo producto = new ProductoModelo();
            producto = producto.obtenerProducto(i.id_Producto);
            ventaProductos += producto.getPrecio()*i.cantidad;
        }
        return ventaProductos;
    }
    public double precioVentasDiarias(String fecha) throws Exception {
        double precioPedidosPorVenta = 0;

        //Obtener ids de todas las ventas diaria
        VentaModelo ventasDiaria = new VentaModelo();
        ArrayList<Integer> ventasDia = ventasDiaria.IdVentasDiaria(fecha);

        //Obtener todos los pedidos asociados a esas ventas
        PedidoProductoModelo pedido = new PedidoProductoModelo();
        ProductoModelo producto = new ProductoModelo();

        for (Integer i : ventasDia) {
            ArrayList<PedidoProductoModelo> pedidos= pedido.ObtenerPedidoProductoPorVenta(i);

            //Obtener precios de esos pedidos
            for (PedidoProductoModelo j : pedidos) {
                ProductoModelo productopedido = new ProductoModelo();
                productopedido = producto.obtenerProducto(j.getId_Producto());
                precioPedidosPorVenta += (productopedido.getPrecio() * j.cantidad);
            }
        }
        return precioPedidosPorVenta;
    }

    public double precioVentasMensuales(String fecha) throws Exception {
        double precioPedidosPorVenta = 0;

        //Obtener ids de todas las ventasMensual
        VentaModelo ventasMensual = new VentaModelo();
        ArrayList<Integer> ventasMes= ventasMensual.IdVentasMensuales(fecha);

        //Obtener todos los pedidos asociados a esas ventas
        PedidoProductoModelo pedido = new PedidoProductoModelo();
        ProductoModelo producto = new ProductoModelo();

        for (Integer i : ventasMes) {
            ArrayList<PedidoProductoModelo> pedidos= pedido.ObtenerPedidoProductoPorVenta(i);

            //Obtener precios de esos pedidos
            for (PedidoProductoModelo j : pedidos) {
                ProductoModelo productopedido = new ProductoModelo();
                productopedido = producto.obtenerProducto(j.getId_Producto());
                precioPedidosPorVenta += (productopedido.getPrecio() * j.cantidad);
            }
        }
        return precioPedidosPorVenta;
    }
}

package minimarket.modelo;

import minimarket.persistencia.PedidoPlatoDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoPlatoModelo {
    private int id_PedidoPlato;
    private int cantidad;
    private int id_Plato;
    private int id_Venta;
    private PlatoModelo platoPedido;
    private VentaModelo venta;
    private PedidoPlatoDAO pedidoplatodao = new PedidoPlatoDAO(this);

    public PedidoPlatoModelo() {
    }

    public PedidoPlatoModelo(int cantidad, int id_Plato, int id_Venta) {
        this.cantidad = cantidad;
        this.id_Plato = id_Plato;
        this.id_Venta = id_Venta;
    }

    public int getId_PedidoPlato() {
        return id_PedidoPlato;
    }

    public void setId_PedidoPlato(int id_PedidoPlato) {
        this.id_PedidoPlato = id_PedidoPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_Plato() {
        return id_Plato;
    }

    public void setId_Plato(int id_Plato) {
        this.id_Plato = id_Plato;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }

    public PlatoModelo getPlatoPedido() {
        return platoPedido;
    }

    public void setPlatoPedido(PlatoModelo platoPedido) {
        this.platoPedido = platoPedido;
    }

    public VentaModelo getVenta() {
        return venta;
    }

    public void setVenta(VentaModelo venta) {
        this.venta = venta;
    }

    public void ingresarPedidoPlato(int idventa, int idPlato, int cantidad) throws Exception {
        pedidoplatodao.ingresarPedidoPlato(idventa, idPlato, cantidad);
    }
    public void modificarPedidoPlato(int id, int cantidad, int idPlato) throws Exception {
        pedidoplatodao.modificarPedidoPlato(id, cantidad, idPlato);
    }
    public void eliminarPedidoPlato(int idventa) throws Exception {
        pedidoplatodao.eliminarPedidoPlatoPorVenta(idventa);
    }

    public ArrayList<PedidoPlatoModelo> ObtenerPedidoPlatoPorVenta(int id_Venta) throws Exception {
        return pedidoplatodao.ObtenerPedidosPorVenta(id_Venta);
    }
    public ArrayList<PedidoPlatoModelo> ObtenerPedidosPlatosTodos() throws Exception {
        return pedidoplatodao.ObtenerPedidosPlatosTodos();
    }
    public String mostrarPedidoPlato(int id_Venta) throws Exception {
        ArrayList<PedidoPlatoModelo> modelo = new ArrayList<>();
        modelo = ObtenerPedidoPlatoPorVenta(id_Venta);
        String detalleVenta = "";
        for(PedidoPlatoModelo i: modelo){
            detalleVenta="Cantidad "+ i.cantidad;
            PlatoModelo plato = new PlatoModelo();
            plato.buscarPlatoPorId(i.id_Plato);
            detalleVenta += "  Plato: "+plato.getNombre()+"  Precio: "+plato.getPrecio();
        }
        return detalleVenta;
    }

    public double precioPedidoPlato(int id_Venta) throws Exception {
        ArrayList<PedidoPlatoModelo> modelo = new ArrayList<>();
        modelo = ObtenerPedidoPlatoPorVenta(id_Venta);
        double precioPedidosPorVenta = 0;
        for(PedidoPlatoModelo i: modelo){
            PlatoModelo plato = new PlatoModelo();
            plato.buscarPlatoPorId(i.id_Plato);
            precioPedidosPorVenta += plato.getPrecio() * i.cantidad;
        }
        return precioPedidosPorVenta;
    }
    public double precioVentasDiarias(String fecha) throws Exception {
        double precioPedidosPorVenta = 0;

        //Obtener ids de todas las ventas diaria
        VentaModelo ventasDiaria = new VentaModelo();
        ArrayList<Integer> ventasDia = ventasDiaria.IdVentasDiaria(fecha);

        //Obtener todos los pedidos asociados a esas ventas
        PedidoPlatoModelo pedido = new PedidoPlatoModelo();
        PlatoModelo plato = new PlatoModelo();

        for (Integer i : ventasDia) {
            ArrayList<PedidoPlatoModelo> pedidos= pedido.ObtenerPedidoPlatoPorVenta(i);

            //Obtener precios de esos pedidos
            for (PedidoPlatoModelo j : pedidos) {
                PlatoModelo platopedido = new PlatoModelo();
                platopedido = plato.buscarPlatoPorId(j.getId_Plato());
                precioPedidosPorVenta += (platopedido.getPrecio() * j.cantidad);
            }
        }
        return precioPedidosPorVenta;
    }
    public double precioVentasMensuales(String fecha) throws Exception {
        double precioPedidosPorVenta = 0;

        //Obtener ids de todas las ventas mensuales
        VentaModelo ventasMensuales = new VentaModelo();
        ArrayList<Integer> ventasMes = ventasMensuales.IdVentasMensuales(fecha);

        //Obtener todos los pedidos asociados a esas ventas
        PedidoPlatoModelo pedido = new PedidoPlatoModelo();
        PlatoModelo plato = new PlatoModelo();

        for (Integer i : ventasMes) {
            ArrayList<PedidoPlatoModelo> pedidos= pedido.ObtenerPedidoPlatoPorVenta(i);

            //Obtener precios de esos pedidos
            for (PedidoPlatoModelo j : pedidos) {
                PlatoModelo platopedido = new PlatoModelo();
                platopedido = plato.buscarPlatoPorId(j.getId_Plato());
                precioPedidosPorVenta += (platopedido.getPrecio() * j.cantidad);
            }
        }
        return precioPedidosPorVenta;
    }

    public double ventasPlatoTodas() throws Exception {
        double ventaPlatos = 0;
        ArrayList<PedidoPlatoModelo> modelo = ObtenerPedidosPlatosTodos();
        for(PedidoPlatoModelo i: modelo){
            PlatoModelo plato = new PlatoModelo();
            plato = plato.buscarPlatoPorId(i.id_Plato);
            ventaPlatos += (plato.getPrecio()*i.cantidad);
        }
        return ventaPlatos;
    }

    public String platoMasVendido() throws Exception {
        //Obtener Lista de platos disponibles
        PlatoModelo plato = new PlatoModelo();
        ArrayList<PlatoModelo> platosTodos = new ArrayList<>();
        platosTodos = plato.obtenerPlatosTodos();

        //obtener lista de pedidos
        PedidoPlatoModelo Pedidoplato = new PedidoPlatoModelo();
        ArrayList<PedidoPlatoModelo> Pedidoplatos = new ArrayList<PedidoPlatoModelo>();
        Pedidoplatos = Pedidoplato.ObtenerPedidosPlatosTodos();

        //Obtener plato mas vendido
        int mayor = 0;
        PlatoModelo platoMasVendido = new PlatoModelo();
        for (PlatoModelo i : platosTodos) {
            int cantidad = 0;
            for (PedidoPlatoModelo j : Pedidoplatos) {
                if (i.getId_Plato() == j.getId_Plato()) {
                    cantidad += j.getCantidad();
                }
            }
            if (cantidad > mayor) {
                mayor = cantidad;
                platoMasVendido = i;
            }
        }
        return platoMasVendido.getNombre();
    }

}

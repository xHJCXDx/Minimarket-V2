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
    private final PedidoPlatoDAO pedidoplatodao = new PedidoPlatoDAO(this);

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

    public void ingresarPedidoPlato(int idventa, int idPlato, int cantidad) throws Exception {
        pedidoplatodao.ingresarPedidoPlato(idventa, idPlato, cantidad);
    }

    public PedidoPlatoModelo buscarPedidoPlatoPorVenta(int id_Venta) throws Exception {
        return pedidoplatodao.buscarPedidoPlatoPorId(id_Venta);
    }

    public double precioPedidoPlato(int id_Venta) throws Exception {
        PedidoPlatoModelo modelo = new PedidoPlatoModelo();
        modelo = buscarPedidoPlatoPorVenta(id_Venta);
        PlatoModelo plato = new PlatoModelo();
        plato.buscarPlatoPorId(modelo.id_Plato);
        return plato.getPrecio() * modelo.cantidad;
    }

    public ArrayList<PedidoPlatoModelo> ObtenerPedidosPlatosTodos() throws Exception {
        return pedidoplatodao.ObtenerPedidosPlatosTodos();
    }
    public double ventasPlatoTodas() throws Exception {
        double ventaPlatos = 0;
        ArrayList<PedidoPlatoModelo> modelo = ObtenerPedidosPlatosTodos();
        for(PedidoPlatoModelo i: modelo){
            ventaPlatos += i.precioPedidoPlato(i.id_Venta);
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

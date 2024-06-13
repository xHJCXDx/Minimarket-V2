package minimarket.modelo;

import minimarket.persistencia.PedidoPlatoDAO;
import minimarket.persistencia.PedidoProductoDAO;
import minimarket.persistencia.VentaDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class VentaModelo {
    private int id_venta;
    private Date fecha;
    private int id_cliente;
    private int id_empleado;
    private ArrayList<PedidoPlatoModelo> platos = new ArrayList<>();
    private ArrayList<PedidoProductoModelo> productos = new ArrayList<>();
    private final VentaDAO ventadao = new VentaDAO(this);
    public VentaModelo() {
    }

    public VentaModelo(Date fecha, int id_cliente, int id_empleado, ArrayList<PedidoPlatoModelo> platos, ArrayList<PedidoProductoModelo> productos) {
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.platos = platos;
        this.productos = productos;
    }
    public VentaModelo(Date fecha, int id_cliente, int id_empleado) {
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public ArrayList<PedidoPlatoModelo> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<PedidoPlatoModelo> platos) {
        this.platos = platos;
    }

    public ArrayList<PedidoProductoModelo> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<PedidoProductoModelo> productos) {
        this.productos = productos;
    }

    public void ingresarVenta(String fecha, int idCliente, int idEmpleado) throws Exception {
        ventadao.ingresarVenta(fecha, idCliente, idEmpleado);
    }

    public void eliminarVenta (int id) throws Exception {
        ventadao.eliminarVenta(id);
    }
    public ArrayList<VentaModelo> obtenerVentasTodas() throws Exception {
        return ventadao.obtenerVentasTodas();
    }
    public ArrayList<VentaModelo> obtenerVentasMensuales() throws Exception {
        return ventadao.obtenerVentasMensuales();
    }
    public ArrayList<VentaModelo> obtenerVentasDiarias() throws Exception {
        return ventadao.obtenerVentasDiarias();
    }

    public double gananciasDiaria() throws Exception {
        VentaModelo venta = new VentaModelo();
        ArrayList<VentaModelo> ventas= venta.obtenerVentasDiarias();
        double total = 0;
//        for(VentaModelo i: ventas){
//            total += i.(i.getId_venta());
//        }
        return total;
    }
    public double gananciasMensuales() throws Exception {
        VentaModelo venta = new VentaModelo();
        ArrayList<VentaModelo> ventas= venta.obtenerVentasMensuales();
        double total = 0;
//        for(VentaModelo i: ventas){
//            total += i.precioVenta(i.getId_venta());
//        }
        return total;
    }
    public double ganancias() throws Exception {
        VentaModelo venta = new VentaModelo();
        ArrayList<VentaModelo> ventas= venta.obtenerVentasTodas();
        double total = 0;
//        for(VentaModelo i: ventas){
//            total += i.precioVenta(i.getId_venta());
//        }
        return total;
    }
    public ArrayList<String> listarVentas() throws Exception {
        return ventadao.buscarVentas();
    }

    public VentaModelo ultimoRegistro() throws Exception {
        VentaModelo venta = new VentaModelo();
        ArrayList<VentaModelo> ventas= venta.obtenerVentasTodas();
        return ventas.get(ventas.size()-1);
    }

}

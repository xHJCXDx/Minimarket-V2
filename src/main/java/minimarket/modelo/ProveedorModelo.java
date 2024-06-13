package minimarket.modelo;

import minimarket.persistencia.ProveedorDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProveedorModelo {
    private int id_Proveedor;
    private String nombre;
    private String contacto;
    private double cuenta;
    private boolean pago;
    private final ProveedorDAO proveedordao = new ProveedorDAO(this);

    public ProveedorModelo() {
    }

    public ProveedorModelo(String nombre, String contacto, double cuenta, boolean pago) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.cuenta = cuenta;
        this.pago = pago;
    }

    public void setId_Proveedor(int id_Proveedor) {this.id_Proveedor = id_Proveedor;}
    public int getId_Proveedor() {
        return id_Proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public double getCuenta() {
        return cuenta;
    }

    public void setCuenta(double cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void ingresarProveedor(String nombre, String contacto, double cuentaProveedor, double pago) throws Exception {
        proveedordao.ingresarProveedor(nombre, contacto, cuentaProveedor, pago);
    }

    public void modificarProveedor(int id, String nombre, String contacto, double cuentaProveedor, double pago) throws Exception {
        proveedordao.modificarProveedor(id, nombre, contacto, cuentaProveedor, pago);
    }

    public void eliminarProveedor(int id) throws Exception {
        proveedordao.eliminarProveedor(id);
    }

    public ProveedorModelo buscarProveedorPorId(int id) throws Exception {
        return proveedordao.buscarProveedorPorId(id);
    }

    public ArrayList<String> listarProveedores() throws Exception {
        return proveedordao.listarProveedores();
    }
    public ArrayList<ProveedorModelo> obtenerProovedoresTodos() throws Exception {
        return proveedordao.obtenerProveedoresTodos();
    }
    public void pagarCuenta(int id_Proveedor) throws Exception {
        proveedordao.modificarProveedorPago(id_Proveedor);
    }
    public double deudaTotal() throws Exception {
        double deuda = 0;
        ArrayList<ProveedorModelo> proveedores = proveedordao.obtenerProveedoresTodos();
        for (ProveedorModelo proveedor : proveedores) {
            if (!proveedor.isPago()) {
                deuda = deuda + proveedor.getCuenta();
            }
        }
        return deuda;
    }
}

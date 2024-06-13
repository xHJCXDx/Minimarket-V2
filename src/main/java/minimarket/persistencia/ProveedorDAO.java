package minimarket.persistencia;

import minimarket.modelo.ClienteModelo;
import minimarket.modelo.ProveedorModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProveedorDAO extends DAO {

    public ProveedorDAO(ProveedorModelo proveedor) {
    }

    public void ingresarProveedor(String nombre, String contacto, double cuentaProveedor, boolean pago) throws Exception {
        String sql = "INSERT INTO Proveedor (nombre, contacto, cuenta_Proveedor, pago) VALUES ('" + nombre + "', '" + contacto + "', " + cuentaProveedor + ", " + pago + ")";
        ejecutarSQL(sql);
    }


    public void modificarProveedor(int id, String nombre, String contacto, double cuentaProveedor, boolean pago) throws Exception {
        String sql = "UPDATE Proveedor SET nombre = '" + nombre + "', contacto = '" + contacto + "', cuenta_Proveedor = " + cuentaProveedor + ", pago = " + pago + " WHERE id_Proveedor = " + id;
        ejecutarSQL(sql);
    }

    public void modificarProveedorPago(int id) throws Exception {
        boolean pago = true;
        String sql = "UPDATE Proveedor SET pago = " + pago + " WHERE id_Proveedor = " + id;
        ejecutarSQL(sql);
    }
    public void eliminarProveedor(int id) throws Exception {
        // No hay necesidad de eliminar productos relacionados ya que no hay restricciones de integridad referencial.
        String sql = "DELETE FROM Proveedor WHERE id_Proveedor = " + id;
        ejecutarSQL(sql);
    }

    public ProveedorModelo buscarProveedorPorId(int id) throws Exception {
        String sql = "SELECT * FROM Proveedor WHERE id_Proveedor = " + id;
        ProveedorModelo proveedorBuscado = new ProveedorModelo();
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
           proveedorBuscado.setId_Proveedor(resultado.getInt("id_Proveedor"));
           proveedorBuscado.setNombre(resultado.getString("nombre"));
           proveedorBuscado.setContacto(resultado.getString("contacto"));
           proveedorBuscado.setCuenta(resultado.getDouble("cuenta_Proveedor"));
           proveedorBuscado.setPago(resultado.getBoolean("pago"));
        }
        return proveedorBuscado;
    }

    public ArrayList<String> listarProveedores() throws Exception {
        ArrayList<String> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String proveedor = "ID: " + resultado.getInt("id_Proveedor") + ", Nombre: " + resultado.getString("nombre") + ", Contacto: " + resultado.getString("contacto") + ", Cuenta Proveedor: " + resultado.getDouble("cuenta_Proveedor") + ", Pago: " + resultado.getBoolean("pago");
            proveedores.add(proveedor);
        }
        desconectarBase();
        return proveedores;
    }
    public ArrayList<ProveedorModelo> obtenerProveedoresTodos() throws Exception {
        ArrayList<ProveedorModelo> ProovedoresTodos = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            ProveedorModelo ProveedorBuscado= new ProveedorModelo();
            ProveedorBuscado.setId_Proveedor(resultado.getInt("id_Proveedor"));
            ProveedorBuscado.setNombre(resultado.getString("nombre"));
            ProveedorBuscado.setContacto(resultado.getString("contacto"));
            ProveedorBuscado.setCuenta(resultado.getDouble("cuenta_Proveedor"));
            ProveedorBuscado.setPago(resultado.getBoolean("pago"));
            ProovedoresTodos.add(ProveedorBuscado);
        }
        desconectarBase();
        return ProovedoresTodos;
    }
}

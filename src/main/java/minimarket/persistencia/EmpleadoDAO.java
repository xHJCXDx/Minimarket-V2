package minimarket.persistencia;

import minimarket.modelo.ClienteModelo;
import minimarket.modelo.EmpleadoModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadoDAO extends DAO {

    public EmpleadoDAO(EmpleadoModelo empleado) {
    }

    public void ingresarEmpleado(String nombre) throws Exception {
        String sql = "INSERT INTO Empleado (nombre) VALUES ('" + nombre + "')";
        ejecutarSQL(sql);
    }

    public void modificarEmpleado(int id, String nombre) throws Exception {
        String sql = "UPDATE Empleado SET nombre = '" + nombre + "' WHERE id_Empleado = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarEmpleado(int id) throws Exception {
        // Aquí no hay restricciones de integridad referencial, así que simplemente eliminamos al empleado
        String sqlEliminarEmpleado = "DELETE FROM Empleado WHERE id_Empleado = " + id;
        ejecutarSQL(sqlEliminarEmpleado);
    }


    public EmpleadoModelo buscarEmpleadoPorId(int id) throws Exception {
        String sql = "SELECT * FROM Empleado WHERE id_Empleado = " + id;
        EmpleadoModelo empleadoBuscado = new EmpleadoModelo();
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            empleadoBuscado.setId_Empleado(resultado.getInt("id_Empleado"));
            empleadoBuscado.setNombre(resultado.getString("nombre"));
            empleadoBuscado.setContacto(resultado.getString("contacto"));
        }
        return empleadoBuscado;
    }

    public ArrayList<String> listarEmpleados() throws Exception {
        ArrayList<String> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String empleado = "ID: " + resultado.getInt("id_Empleado") + ", Nombre: " + resultado.getString("nombre");
            empleados.add(empleado);
        }
        desconectarBase();
        return empleados;
    }
    public ArrayList<EmpleadoModelo> obtenerEmpleadosTodos() throws Exception {
        ArrayList<EmpleadoModelo> empladosTodos = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            EmpleadoModelo empleadoBuscado= new EmpleadoModelo();
            empleadoBuscado.setId_Empleado(resultado.getInt("id_Cliente"));
            empleadoBuscado.setNombre(resultado.getString("nombre"));
            empleadoBuscado.setContacto(resultado.getString("contacto"));
            empladosTodos.add(empleadoBuscado);
        }
        desconectarBase();
        return empladosTodos;
    }
}

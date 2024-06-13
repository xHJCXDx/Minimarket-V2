package minimarket.modelo;

import minimarket.persistencia.ClienteDAO;
import minimarket.persistencia.EmpleadoDAO;

import java.util.ArrayList;

public class EmpleadoModelo {
    private int id_Empleado;
    private String nombre;
    private String contacto;
    private final EmpleadoDAO empleadodao=new EmpleadoDAO(this);

    public EmpleadoModelo() {
    }

    public EmpleadoModelo(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public void setId_Empleado(int id_Empleado) {
        this.id_Empleado = id_Empleado;
    }
    public int getId_Empleado() {
        return id_Empleado;
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
    public void ingresarEmpleado(String nombre) throws Exception {
        empleadodao.ingresarEmpleado(nombre);
    }
    public void modificarEmpleado(int id, String nombre) throws Exception {
        empleadodao.modificarEmpleado(id, nombre);
    }
    public void eliminarEmpleado(int id) throws Exception {
        empleadodao.eliminarEmpleado(id);
    }
    public EmpleadoModelo buscarEmpleadoPorId(int id) throws Exception {
        return empleadodao.buscarEmpleadoPorId(id);
    }
    public ArrayList<String> listarEmpleados() throws Exception {
        return empleadodao.listarEmpleados();
    }
    public ArrayList<EmpleadoModelo> ObtenerEmpleadosTodos() throws Exception {
        return empleadodao.obtenerEmpleadosTodos();
    }
}

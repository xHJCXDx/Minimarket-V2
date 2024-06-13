package minimarket.modelo;

import minimarket.persistencia.ClienteDAO;

import java.util.ArrayList;

public class ClienteModelo {
    private int id_Cliente;
    private String nombre;
    private String contacto;
    final private ClienteDAO clientedao = new ClienteDAO(this);

    public ClienteModelo() {
    }
    public ClienteModelo(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }
    public void setId_Cliente(int id_Cliente) {this.id_Cliente = id_Cliente;}
    public int getId_Cliente() {
        return id_Cliente;
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
    public void ingresarCliente(String nombre, String contacto) throws Exception {
        clientedao.ingresarCliente(nombre, contacto);
    }
    public void modificarCliente(int id, String nombre, String contacto) throws Exception {
        clientedao.modificarCliente(id, nombre, contacto);
    }
    public void eliminarCliente(int id) throws Exception {
        clientedao.eliminarCliente(id);
    }
    public ClienteModelo buscarClientePorId(int id) throws Exception {
        return clientedao.buscarClientePorId(id);
    }
    public ArrayList<String> listarClientes() throws Exception {
        return clientedao.listarClientes();
    }
    public ArrayList<ClienteModelo> ObtenerClientesTodos() throws Exception {
        return clientedao.obtenerClientesTodos();
    }
}
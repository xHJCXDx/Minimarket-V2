package minimarket.persistencia;

import minimarket.modelo.ClienteModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO extends DAO {
    public ClienteDAO(ClienteModelo cliente) {
    }

    public void ingresarCliente(String nombre, String contacto) throws Exception {
        String sql = "INSERT INTO Cliente (nombre, contacto) VALUES ('" + nombre + "', '" + contacto + "')";
        ejecutarSQL(sql);
    }

    public void modificarCliente(int id, String nombre, String contacto) throws Exception {
        String sql = "UPDATE Cliente SET nombre = '" + nombre + "', contacto = '" + contacto + "' WHERE id_Cliente = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarCliente(int id) throws Exception {
        // Eliminar ventas relacionadas
        String sqlEliminarVentas = "DELETE FROM Venta WHERE id_Cliente = " + id;
        ejecutarSQL(sqlEliminarVentas);

        // Luego eliminar el cliente
        String sqlEliminarCliente = "DELETE FROM Cliente WHERE id_Cliente = " + id;
        ejecutarSQL(sqlEliminarCliente);
    }


    public ClienteModelo buscarClientePorId(int id) throws Exception {
        String sql = "SELECT * FROM Cliente WHERE id_Cliente = " + id;
        ClienteModelo clienteBuscado = new ClienteModelo();
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            clienteBuscado.setId_Cliente(resultado.getInt("id_Cliente"));
            clienteBuscado.setNombre(resultado.getString("nombre"));
            clienteBuscado.setContacto(resultado.getString("contacto"));
        }
        return clienteBuscado;
    }

    public ArrayList<String> listarClientes() throws Exception {
        ArrayList<String> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String cliente = "ID: " + resultado.getInt("id_Cliente") + ", Nombre: " + resultado.getString("nombre") + ", Contacto: " + resultado.getString("contacto");
            clientes.add(cliente);
        }
        desconectarBase();
        return clientes;
    }
    public ArrayList<ClienteModelo> obtenerClientesTodos() throws Exception {
        ArrayList<ClienteModelo> clientesTodos = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            ClienteModelo clienteBuscado= new ClienteModelo();
            clienteBuscado.setId_Cliente(resultado.getInt("id_Cliente"));
            clienteBuscado.setNombre(resultado.getString("nombre"));
            clienteBuscado.setContacto(resultado.getString("contacto"));
            clientesTodos.add(clienteBuscado);
        }
        desconectarBase();
        return clientesTodos;
    }
}

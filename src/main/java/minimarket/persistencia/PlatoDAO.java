package minimarket.persistencia;

import minimarket.modelo.PlatoModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PlatoDAO extends DAO {

    public PlatoDAO(PlatoModelo plato) {
    }

    public void ingresarPlato(String nombre, double precio) throws Exception {
        String sql = "INSERT INTO Plato (nombre, precio) VALUES ('" + nombre + "', " + precio + ")";
        ejecutarSQL(sql);
    }

    public void modificarPlato(int id, String nombre, double precio) throws Exception {
        String sql = "UPDATE Plato SET nombre = '" + nombre + "', precio = " + precio + " WHERE id_Plato = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarPlato(int id) throws Exception {
        // Eliminar pedidos de platos relacionados
        String sqlEliminarPedidosPlato = "DELETE FROM PedidoPlato WHERE id_Plato = " + id;
        ejecutarSQL(sqlEliminarPedidosPlato);

        // Luego eliminar el plato
        String sqlEliminarPlato = "DELETE FROM Plato WHERE id_Plato = " + id;
        ejecutarSQL(sqlEliminarPlato);
    }


    public PlatoModelo buscarPlatoPorId(int id) throws Exception {
        String sql = "SELECT * FROM Plato WHERE id_Plato = " + id;
        PlatoModelo platoBuscado = new PlatoModelo();
        ResultSet resultado = consultarBase(sql);
        if (resultado.next()) {
            platoBuscado.setId_Plato(resultado.getInt("id_Plato"));
            platoBuscado.setNombre(resultado.getString("nombre"));
            platoBuscado.setPrecio(resultado.getDouble("precio"));
        }
        return platoBuscado;
    }

    public ArrayList<String> listarPlatos() throws Exception {
        ArrayList<String> platos = new ArrayList<>();
        String sql = "SELECT * FROM Plato";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            String plato = "ID: " + resultado.getInt("id_Plato") + ", Nombre: " + resultado.getString("nombre") + ", Precio: " + resultado.getDouble("precio");
            platos.add(plato);
        }
        desconectarBase();
        return platos;
    }
    public ArrayList<PlatoModelo> obtenerPlatosTodos() throws Exception {
        ArrayList<PlatoModelo> platos = new ArrayList<>();
        String sql = "SELECT * FROM Plato";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            PlatoModelo plato = new PlatoModelo();
            plato.setId_Plato(resultado.getInt("id_Plato"));
            plato.setNombre(resultado.getString("nombre"));
            plato.setPrecio(resultado.getDouble("precio"));
            platos.add(plato);
        }
        desconectarBase();
        return platos;
    }
}

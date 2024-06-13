package minimarket.persistencia;

import minimarket.modelo.PedidoPlatoModelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoPlatoDAO extends DAO {

    public PedidoPlatoDAO (PedidoPlatoModelo pedidoPlato){}

    public void ingresarPedidoPlato(int idventa, int idPlato, int cantidad) throws Exception {
        String sql = "INSERT INTO PedidoPlato (cantidad, id_venta, id_plato) VALUES ('" + cantidad +"','" + idventa + "', " + idPlato + ")";
        ejecutarSQL(sql);
    }

    public void modificarPedidoPlato(int id, int cantidad, int idPlato) throws Exception {
        String sql = "UPDATE PedidoPlato SET cantidad = '" + cantidad + "', id_Plato = " + idPlato + " WHERE id_Pedido_Plato = " + id;
        ejecutarSQL(sql);
    }

    public void eliminarPedidoPlatoPorVenta(int id) throws Exception {
        String sql = "DELETE FROM PedidoPlato WHERE id_Venta = " + id;
        ejecutarSQL(sql);
    }

    public PedidoPlatoModelo buscarPedidoPlatoPorId(int id) throws Exception {
        String sql = "SELECT * FROM PedidoPlato WHERE id_Pedido_Plato = " + id;
        ResultSet resultado = consultarBase(sql);
        PedidoPlatoModelo platoBuscado = new PedidoPlatoModelo();
        if (resultado.next()) {
            platoBuscado.setId_PedidoPlato(resultado.getInt("id_Pedido_Plato"));
            platoBuscado.setCantidad(resultado.getInt("cantidad"));
            platoBuscado.setId_Plato(resultado.getInt("id_Plato"));
        }
        return platoBuscado;
    }

    public ArrayList<PedidoPlatoModelo> ObtenerPedidosPorVenta(int id_venta) throws Exception {
        ArrayList<PedidoPlatoModelo> pedidosPlato = new ArrayList<>();
        String sql = "SELECT * FROM PedidoPlato WHERE id_venta="+id_venta;
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            PedidoPlatoModelo modelo = new PedidoPlatoModelo();
            modelo.setId_PedidoPlato(resultado.getInt("id_Pedido_Plato"));
            modelo.setCantidad(resultado.getInt("cantidad"));
            modelo.setId_Plato(resultado.getInt("id_Plato"));
            pedidosPlato.add(modelo);
        }
        desconectarBase();
        return pedidosPlato;
    }
    public ArrayList<PedidoPlatoModelo> ObtenerPedidosPlatosTodos() throws Exception {
        ArrayList<PedidoPlatoModelo> pedidosPlato = new ArrayList<>();
        String sql = "SELECT * FROM PedidoPlato";
        ResultSet resultado = consultarBase(sql);
        while (resultado.next()) {
            PedidoPlatoModelo modelo = new PedidoPlatoModelo();
            modelo.setId_PedidoPlato(resultado.getInt("id_Pedido_Plato"));
            modelo.setCantidad(resultado.getInt("cantidad"));
            modelo.setId_Plato(resultado.getInt("id_Plato"));
            pedidosPlato.add(modelo);
        }
        desconectarBase();
        return pedidosPlato;
    }
}

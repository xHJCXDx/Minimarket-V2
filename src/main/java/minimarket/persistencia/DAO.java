package minimarket.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class DAO {
    protected Connection conexion;
    protected Statement statement;

    protected final String JDBC_DRIVER = "org.h2.Driver";
    protected final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    protected final String USER = "sa";
    protected final String PASS = "";

    protected void conectarBase() throws Exception {
        if (conexion == null || conexion.isClosed()) {
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conexion.createStatement();
        }
    }

    protected void desconectarBase() throws Exception {
        if (statement != null) statement.close();
        if (conexion != null) conexion.close();
    }

    protected void ejecutarSQL(String sql) throws Exception {
        conectarBase();
        statement.executeUpdate(sql);
        desconectarBase();
    }

    protected ResultSet consultarBase(String sql) throws Exception {
        conectarBase();
        return statement.executeQuery(sql);
    }

    public boolean testConnection() {
        try {
            Connection conn = getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                conn.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos");
            return false;
        }
    }


    public void crearTablas() throws Exception {
        conectarBase();
        try {
            String[] sqlStatements = {
                    "CREATE TABLE IF NOT EXISTS Cliente (" +
                            "id_Cliente INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(45) NOT NULL, " +
                            "contacto VARCHAR(45) NULL);",

                    "CREATE TABLE IF NOT EXISTS Empleado (" +
                            "id_Empleado INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(45) NOT NULL);",

                    "CREATE TABLE IF NOT EXISTS Venta (" +
                            "id_Venta INT AUTO_INCREMENT PRIMARY KEY, " +
                            "fecha DATE NOT NULL, " +
                            "id_Cliente INT NOT NULL, " +
                            "id_Empleado INT NOT NULL, " +
                            "FOREIGN KEY (id_Cliente) REFERENCES Cliente(id_Cliente), " +
                            "FOREIGN KEY (id_Empleado) REFERENCES Empleado(id_Empleado));",

                    "CREATE TABLE IF NOT EXISTS Plato (" +
                            "id_Plato INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(45) NOT NULL, " +
                            "precio DOUBLE NOT NULL);",

                    "CREATE TABLE IF NOT EXISTS PedidoPlato (" +
                            "id_Pedido_Plato INT AUTO_INCREMENT PRIMARY KEY, " +
                            "cantidad INT NOT NULL, " + // Cambiado de VARCHAR(45) a INT
                            "id_Plato INT NOT NULL, " +
                            "id_Venta INT NOT NULL, " +
                            "FOREIGN KEY (id_Plato) REFERENCES Plato(id_Plato),"+
                            "FOREIGN KEY (id_Venta) REFERENCES Venta(id_Venta));",

                    "CREATE TABLE IF NOT EXISTS Producto (" +
                            "id_Producto INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(45) NOT NULL, " +
                            "stock INT NOT NULL, " +
                            "precio DOUBLE NOT NULL);",

                    "CREATE TABLE IF NOT EXISTS PedidoProducto (" +
                            "id_Pedido_Producto INT AUTO_INCREMENT PRIMARY KEY, " +
                            "id_Producto INT NOT NULL, " +
                            "cantidad INT NOT NULL, " + // Cambiado de VARCHAR(45) a INT
                            "id_venta INT NOT NULL, " +
                            "FOREIGN KEY (id_Producto) REFERENCES Producto(id_Producto),"+
                            "FOREIGN KEY (id_venta) REFERENCES Venta(id_Venta));",

                    "CREATE TABLE IF NOT EXISTS Proveedor (" +
                            "id_Proveedor INT AUTO_INCREMENT PRIMARY KEY, " +
                            "nombre VARCHAR(45) NOT NULL, " +
                            "contacto VARCHAR(45) NOT NULL, " +
                            "cuenta_Proveedor DOUBLE NULL, " +
                            "pago BOOLEAN NULL);"
            };

            for (String sql : sqlStatements) {
                statement.executeUpdate(sql);
            }
        } finally {
            desconectarBase();
        }
    }
}

package minimarket.Log4j2;

import minimarket.modelo.PedidoPlatoModelo;
import minimarket.modelo.VentaModelo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2 {
    private static final Logger loggerComanda = LogManager.getLogger("EmailLogger");
    private static final Logger loggerBalance = LogManager.getLogger("FileLogger");
    private static final Logger loggerError = LogManager.getLogger("ConsoleLogger");

    //Logger balance diaria salida por archivo
    public static void logBalanceDiario(double perdidas, double ganancia){
        loggerBalance.info("Perdida: " + perdidas+ " Ganancia " + ganancia + " Total: " + (ganancia - perdidas));
    }
    //Logger error al generara transaccion salida por consola
    public static void logErrorTransaccion(){
        loggerError.error("Error al procesar la transacción");
    }

    //logger venta salida por mail
    public static void ventaRealizada() {
        loggerComanda.debug("Venta Procesada con Exito.\n");
    }
}
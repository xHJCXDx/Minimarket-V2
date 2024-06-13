package minimarket.controladores;

import minimarket.herramientas.Funciones;
import minimarket.modelo.*;

import javax.swing.*;
import java.util.Date;

import static minimarket.Log4j2.Log4j2.*;

public class UsuarioControlador {
    // Crear instancias de clases de modelo
    VentaModelo vender = new VentaModelo();
    PedidoPlatoModelo venderPlato = new PedidoPlatoModelo();
    PedidoProductoModelo venderProducto = new PedidoProductoModelo();
    ProductoModelo producto = new ProductoModelo();
    ProveedorModelo proveedor = new ProveedorModelo();

    public void Vender(){
        try{
            int dia = Funciones.LimitacionNumericaInt("Ingrese dia:","DIA",31,1);
            int mes = Funciones.LimitacionNumericaInt("Ingrese mes:","MES",12,1);
            int anio = Funciones.LimitacionNumericaInt("Ingrese año[ultimos dos dijitos]:","AÑO",99,0);
            Date fecha = new Date(anio,mes,dia);
            String fechastr = Funciones.formatDate(fecha);

            int idC = Funciones.LimitacionNumericaInt("Id Cliente:","ID",999999,1);
            int idE = Funciones.LimitacionNumericaInt("Id Empleado:","ID",999999,1);
            vender.ingresarVenta(fechastr, idC,idE);

            int idP = Funciones.LimitacionNumericaInt("Id Producto:","ID",999999,1);
            int cantidad = Funciones.LimitacionNumericaInt("Cantidad:","CANTIDAD",999999,1);
            venderProducto.ingresarPedidoProducto(vender.ultimoRegistro().getId_venta(), idP, cantidad);
            ventaRealizada();
            JOptionPane.showMessageDialog(null,"Producto vendido");
            System.out.println("Producto vendido.");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al vender producto");
            System.out.println("Error al vender producto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Ingresar(){
        try{
            // Ingresar mercadería
            String nombre = Funciones.InputDialogNoVacio("Nombre del producto");
            int cantidad = Funciones.LimitacionNumericaInt("Cantidad:","CANTIDAD",999999,1);
            producto.modificarStock(nombre, cantidad);
            JOptionPane.showMessageDialog(null,"Mercadería ingresada");
            System.out.println("Mercadería ingresada");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al ingresar producto");
            System.out.println("Error al ingresar producto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void ConsultaDia(){
        try{
            // Consultar ventas
            System.out.println("Venta Diaria: "+vender.gananciasDiaria());
            JOptionPane.showMessageDialog(null,"Venta Diaria: "+vender.gananciasDiaria());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al consultar ventas");
            System.out.println("Error al consultar ventas");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void ConsultaMen(){
        try{
            // Consultar ventas
            System.out.println("Venta Mensual: "+vender.gananciasMensuales());
            JOptionPane.showMessageDialog(null,"Venta Mensual: "+vender.gananciasMensuales());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al consultar ventas");
            System.out.println("Error al consultar ventas");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Balance(){
        try{
            // Balance
            double ingresos= venderProducto.ventasProductoTodas()+venderPlato.ventasPlatoTodas();
            double egresos=proveedor.deudaTotal();
            System.out.println("Balance = " + (ingresos- egresos));
            JOptionPane.showMessageDialog(null,"Balance = " + (ingresos- egresos));

            //Enviar Logger.info
            logBalanceDiario(ingresos, egresos);
            System.out.println("Log de balance diario generado con éxito.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al calcular el balance");
            System.out.println("Error al calcular el balance");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Comanda(){
        try{
            // Solicitar comanda a la cocina
            int dia = Funciones.LimitacionNumericaInt("Ingrese dia:","DIA",31,1);
            int mes = Funciones.LimitacionNumericaInt("Ingrese mes:","MES",12,1);
            int anio = Funciones.LimitacionNumericaInt("Ingrese año[ultimos dos dijitos]:","AÑO",99,0);
            int idC = Funciones.LimitacionNumericaInt("Id Cliente:","ID",999999,1);
            int idE = Funciones.LimitacionNumericaInt("Id Empleado:","ID",999999,1);
            int idP = Funciones.LimitacionNumericaInt("Id Plato:","ID",999999,1);
            int cantidad = Funciones.LimitacionNumericaInt("Cantidad:","CANTIDAD",999999,1);

            vender.ingresarVenta(Funciones.formatDate(new Date(anio,mes,dia)), idC, idE);
            venderPlato.ingresarPedidoPlato(vender.ultimoRegistro().getId_venta(), idP, cantidad);
            ventaRealizada();
            System.out.println("Comanda solicitada.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al solicitar comanda");
            System.out.println("Error al solicitar comanda");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Pago_C(){
        try{
            // Pagar cuenta
            int id_Proveedor = Funciones.LimitacionNumericaInt("Id Proveedor:","ID",999999,1);
            if (!proveedor.buscarProveedorPorId(id_Proveedor).isPago()) {
                proveedor.pagarCuenta(id_Proveedor);
                System.out.println("Cuenta pagada.");
                JOptionPane.showMessageDialog(null,"Cuenta pagada");
            } else {
                logErrorTransaccion();
                System.out.println("Log de error en transacción generado con éxito.");
                System.out.println("No tiene deuda con el proveedor " + proveedor.buscarProveedorPorId(id_Proveedor).getNombre());
                JOptionPane.showMessageDialog(null,"No tiene deuda con el proveedor " + proveedor.buscarProveedorPorId(id_Proveedor).getNombre());
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al pagar cuenta");
            System.out.println("Error al pagar cuenta");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void Informacion(){
        try{
            // Información estadística de platos
            System.out.print("El plato más vendido es " + venderPlato.platoMasVendido());
            JOptionPane.showMessageDialog(null,"El plato más vendido es" + venderPlato.platoMasVendido());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al consultar información");
            System.out.println("Error al consultar información");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

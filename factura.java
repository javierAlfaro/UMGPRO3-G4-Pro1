/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita;

/**
 *
 * @author Javier Alfaro
 */
public class factura {
    String cliente;
    String nit;
    String direccion;
    int total;
    String tipoPago;
    int[] items;
    boolean pagoCuenta;
    inventario invCostos = new inventario();
    
    public factura(){
        
    }
    
    void calcTotal(inventario invActual){
        invCostos = invActual;
        for(int i=0;i<items.length;i++){
            total = total + invCostos.productos[items[i]].costo;
        }
    }
    
    void showFactura(){
        System.out.println("Nombre de Cliente: " + cliente);
        System.out.println("NIT de Cliente: " + nit);
        System.out.println("Dirección: " + direccion);
        if(pagoCuenta){
            System.out.println("Concepto: Pago de Cuenta Pendiente");
            System.out.println("..... Productos Pendientes de Pago .....");
        }
        else{
            System.out.println("Concepto: Compras Realizadas");
            System.out.println(".......... Productos Comprados ..........");
        }
        for(int i=0;i<items.length;i++){
            System.out.println("Producto: " + invCostos.productos[items[i]].nombre + " Costo: Q" + invCostos.productos[items[i]].costo);
        }
        System.out.println("TOTAL: Q" + total);
        System.out.println("Tipo de Pago: " + tipoPago);
        System.out.println(".................. FIN ..................");
    }
}

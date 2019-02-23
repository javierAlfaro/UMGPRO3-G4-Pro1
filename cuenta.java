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
public class cuenta {
    String cliente;
    String nit;
    String direccion;
    int total;
    int[] items = new int[0];
    int cantItems = 0;
    inventario invCostos = new inventario();
    
    public cuenta(){
        
    }
    
    void calcTotal(inventario invActual){
        invCostos = invActual;
        total = 0;
        for(int i=0;i<items.length;i++){
            total = total + invCostos.productos[items[i]].costo;
        }
    }
    
    void resizeItems(){
        int oldSize = items.length;
        int[] newItems = new int[cantItems+1];
        System.arraycopy(items, 0, newItems, 0, oldSize);
        items = newItems;
    }
    
    void showCuenta(){
        System.out.println("Cuenta Pendiente de Pago");
        System.out.println("Nombre de Cliente: " + cliente);
        System.out.println("NIT de Cliente: " + nit);
        System.out.println("Dirección: " + direccion);
        System.out.println(".......... Productos Comprados ..........");
        for(int i=0;i<items.length;i++){
            System.out.println("Producto: " + invCostos.productos[items[i]].nombre + " Costo: Q" + invCostos.productos[items[i]].costo);
        }
        System.out.println("TOTAL: Q" + total);
        System.out.println(".................. FIN ..................");
    }
}

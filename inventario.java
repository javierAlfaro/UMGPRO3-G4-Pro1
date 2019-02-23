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
public class inventario {
    int cantProductos = 3;
    producto[] productos = new producto[10];
    int actualProducto;
    
    public inventario(){
        for(int i=0;i<productos.length;i++){
                productos[i]= new producto();
            }
        llenadoInventario();
    }
    
    void llenadoInventario(){
        productos[0].id = "1";
        productos[0].nombre = "Azucar";
        productos[0].descripcion = "Azucar Caña Real";
        productos[0].costo = 5;
        productos[1].id = "2";
        productos[1].nombre = "Pollo";
        productos[1].descripcion = "1 Libra de Pollo Rey";
        productos[1].costo = 15;
        productos[2].id = "3";
        productos[2].nombre = "Coca Cola";
        productos[2].descripcion = "Gaseosa de 3 Litros Coca Cola";
        productos[2].costo = 20;
        productos[3].id = "4";
        productos[3].nombre = "Carne";
        productos[3].descripcion = "1 Libra de Carne de Res";
        productos[3].costo = 25;
        productos[4].id = "5";
        productos[4].nombre = "Pan";
        productos[4].descripcion = "1 Bolsa de Pan";
        productos[4].costo = 10;
        productos[5].id = "6";
        productos[5].nombre = "Sal";
        productos[5].descripcion = "1 Libra de Sal";
        productos[5].costo = 1;
        productos[6].id = "7";
        productos[6].nombre = "Frijol";
        productos[6].descripcion = "1 Lata de Frijol";
        productos[6].costo = 7;
        productos[7].id = "8";
        productos[7].nombre = "Arroz";
        productos[7].descripcion = "1 Libra de Arroz";
        productos[7].costo = 3;
        productos[8].id = "9";
        productos[8].nombre = "Tortrix";
        productos[8].descripcion = "Bolsa de Tortrix";
        productos[8].costo = 1;
        productos[9].id = "10";
        productos[9].nombre = "Agua Pura";
        productos[9].descripcion = "Garrafon de Agua Pura";
        productos[9].costo = 13;
        
    }
    
    int getPosProduct(String idProducto ){
        for(int i=0;i<productos.length;i++){
            if(productos[i].id.equals(idProducto)){
                return i;
            }
        }
        return 0;
    }
    
    boolean existProduct(String idProducto){
        for(int i=0;i<productos.length;i++){
            if(productos[i].id.equals(idProducto)){
                return true;
            }
        }
        return false;
    }
    
    producto[] resizeProductos(producto[] oldProductos,int newSize){
        int oldSize = oldProductos.length;
        producto[] newProductos = new producto[newSize];
        for(int i=0;i<newSize;i++){
                newProductos[i]= new producto();
            }
        System.arraycopy(oldProductos, 0, newProductos, 0, oldSize);
        return newProductos;
    }
}

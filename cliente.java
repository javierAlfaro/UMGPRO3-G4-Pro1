/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendita;

import java.util.Scanner;

/**
 *
 * @author Javier Alfaro
 */
public class cliente {
    String nit;
    String nombre;
    String direccion;
    boolean debe = false;
    int[] productos;
    int cantProductos;
    int cantFacturas = 0;
    factura[] facturas = new factura[0];
    int actualFactura;
    cuenta cuenta = new cuenta();
    inventario invActual = new inventario();
    
    public cliente(){
    }
    
    void menu(inventario inventario){
        invActual = inventario;
        resetProductos();
        boolean comprando = true;
        Scanner readScan = new Scanner (System.in);
        while(comprando){
            System.out.println("\nSeleccione una de las siguientes opciones para el cliente " + nombre + ":");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos Agregados");
            System.out.println("3. Generar Factura");
            System.out.println("4. Cargar en Cuenta Corriente");
            System.out.println("5. Consultar Cuenta Corriente");
            System.out.println("6. Pagar Cuenta Corriente");
            System.out.println("7. Salir y Borrar Pedido Actual\n");
            String opcion = readScan.nextLine ();
            switch(opcion) {
                case "1" :
                    System.out.println("Ingrese el id del producto");
                    String idProducto = readScan.nextLine ();
                    addProduct(idProducto);
                    break;
                case "2" :
                    System.out.println("\n");
                    if(cantProductos > 0){
                        int tempTotal = 0;
                        for(int i=0;i<productos.length;i++){
                            tempTotal = tempTotal + invActual.productos[productos[i]].costo;
                            System.out.println("Producto: " + invActual.productos[productos[i]].nombre + " Costo: Q" + invActual.productos[productos[i]].costo);
                        }
                        System.out.println("TOTAL: Q" + tempTotal);
                    }
                    else{
                        System.out.println("No ha agregado productos al pedido!!!");
                    }
                    break;
                case "3" :
                    if(cantProductos > 0){
                        boolean selecting = true;
                        while(selecting){
                            System.out.println("Cual sera el metodo de pago?");
                            System.out.println("1. Efectivo");
                            System.out.println("2. Tarjeta de Debito");
                            System.out.println("3. Tarjeta de Credito");
                            System.out.println("4. Cheque");
                            System.out.println("5. Regresar\n");
                            String metodo = readScan.nextLine ();
                            switch(metodo){
                                case "1" :
                                    addFactura(productos,"Efectivo",false);
                                    selecting = false;
                                    resetProductos();
                                    break;
                                case "2" :
                                    addFactura(productos,"Tarjeta de Debito",false);
                                    selecting = false;
                                    resetProductos();
                                    break;
                                case "3" :
                                    addFactura(productos,"Tarjeta de Credito",false);
                                    selecting = false;
                                    resetProductos();
                                    break;
                                case "4" :
                                    addFactura(productos,"Cheque",false);
                                    selecting = false;
                                    resetProductos();
                                    break;
                                case "5" :
                                    System.out.println("Saliendo al Menu del Cliente...\n");
                                    selecting = false;
                                    break;
                                default :
                                    System.out.println("Debe seleccionar una opcion valida, intente nuevamente.\n");
                            }
                        }
                    }
                    else{
                        System.out.println("No se puede generar factura porque no ha agregado productos al pedido!!!");
                    }
                    break;
                case "4" :
                    if(cantProductos > 0){
                        cargarCuenta(productos);
                        debe = true;
                        resetProductos();
                    }
                    else{
                       System.out.println("No se puede cargar a cuenta porque no ha agregado productos al pedido!!!"); 
                    }
                    break;
                case "5" :
                    if(debe){
                        cuenta.showCuenta();
                    }
                    else{
                        System.out.println("Usted no tiene deudas actualmente."); 
                    }
                    break;
                case "6" :
                    if(debe){
                        boolean paying = true;
                        while(paying){
                            System.out.println("Cual sera el metodo de pago?");
                            System.out.println("1. Efectivo");
                            System.out.println("2. Tarjeta de Debito");
                            System.out.println("3. Tarjeta de Credito");
                            System.out.println("4. Cheque");
                            System.out.println("5. Regresar\n");
                            String metodo = readScan.nextLine ();
                            switch(metodo){
                                case "1" :
                                    addFactura(cuenta.items,"Efectivo",true);
                                    debe = false;
                                    paying = false;
                                    break;
                                case "2" :
                                    addFactura(cuenta.items,"Tarjeta de Debito",true);
                                    debe = false;
                                    paying = false;
                                    break;
                                case "3" :
                                    addFactura(cuenta.items,"Tarjeta de Credito",true);
                                    debe = false;
                                    paying = false;
                                    break;
                                case "4" :
                                    addFactura(cuenta.items,"Cheque",true);
                                    debe = false;
                                    paying = false;
                                    break;
                                case "5" :
                                    System.out.println("Saliendo al Menu del Cliente...\n");
                                    paying = false;
                                    break;
                                default :
                                    System.out.println("Debe seleccionar una opcion valida, intente nuevamente.\n");
                            }
                        }
                    }
                    else{
                        System.out.println("Usted no tiene deudas actualmente."); 
                    }   
                    break;
                case "7" :
                    System.out.println("Saliendo al Menu del Usuario...\n");
                    comprando = false;
                    break;
                default :
                    System.out.println("Debe seleccionar una opcion valida, intente nuevamente.\n");
            }
        }
    }
    
    void resetProductos(){
        productos = new int[0];
        cantProductos = 0;
    }
    
    void addProduct(String idProducto){
        if(invActual.existProduct(idProducto)){
            int posProduct = invActual.getPosProduct(idProducto);
            productos = (int[])resizeProductos(productos,productos.length+1);
            productos[cantProductos] = posProduct;
            cantProductos++;
            System.out.println("Producto agregado satisfactoriamente!!!\n");
        }
        else{
            System.out.println("No existe un producto con el identificador ingresado, intenlo nuevamente.");
        }
    }
    
    int[] resizeProductos(int[] oldProductos,int newSize){
        int oldSize = oldProductos.length;
        int[] newProductos = new int[newSize];
        System.arraycopy(oldProductos, 0, newProductos, 0, oldSize);
        return newProductos;
    }
    
    void addFactura(int[] items,String tipo,boolean cuenta){
        facturas = (factura[])resizeFacturas(facturas,facturas.length+1);
        facturas[cantFacturas].pagoCuenta = cuenta;
        facturas[cantFacturas].items = items;
        facturas[cantFacturas].cliente = nombre;
        facturas[cantFacturas].nit = nit;
        facturas[cantFacturas].direccion = direccion;
        facturas[cantFacturas].tipoPago = tipo;
        facturas[cantFacturas].calcTotal(invActual);
        System.out.println("Factura Generada");
        facturas[cantFacturas].showFactura();
        cantFacturas++;
    }
    
    factura[] resizeFacturas(factura[] oldFacturas,int newSize){
        int oldSize = oldFacturas.length;
        factura[] newFacturas = new factura[newSize];
        for(int i=0;i<newSize;i++){
                newFacturas[i]= new factura();
            }
        System.arraycopy(oldFacturas, 0, newFacturas, 0, oldSize);
        return newFacturas;
    }

    void cargarCuenta(int[] newProducts){
        for(int i=0;i<productos.length;i++){
            cuenta.resizeItems();
            cuenta.items[cuenta.cantItems] = newProducts[i];
            cuenta.cliente = nombre;
            cuenta.nit = nit;
            cuenta.direccion = direccion;
            cuenta.cantItems++;
        }
        cuenta.calcTotal(invActual);
        System.out.println("Productos Cargados a Cuenta Corriente Satisfactoriamente");
    }

}

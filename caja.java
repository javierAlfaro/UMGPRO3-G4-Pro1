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
public class caja {
    static int cantUsuarios = 0;
    static usuario[] usuarios = new usuario[0];
    static int actualUser;
    static int cantClientes = 0;
    static cliente[] clientes = new cliente[0];
    static int actualClient;
    static inventario invActual = new inventario();
    
    public caja() {
        Scanner readScan = new Scanner (System.in);
        String tempName;
        String tempUser;
        String tempPass;
        while(true){
            System.out.println("Bienvenido al Sistema de Caja 'Tiendita'");
            System.out.println("Debe iniciar sesion para poder utilizar el sistema, si no tiene un usuario puede registrarse");
            System.out.println("Seleccione una de las siguientes opciones:");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir\n");
            String opcion = readScan.nextLine ();
            switch(opcion) {
                case "1" :
                    System.out.println("Ingrese su usuario:");
                    tempUser = readScan.nextLine();
                    if(existUser(tempUser)){
                        System.out.println("Ingrese su password:");
                        tempPass = readScan.nextLine();
                        if(login(tempUser,tempPass)){
                            menu();
                        }
                        else{
                            System.out.println("Password incorrecta, vuelva a intentarlo por favor.\n");
                        }
                    }
                    else{
                        System.out.println("Usuario no existente, vuelva a intentarlo por favor.\n");
                    }
                    break;
                case "2" :
                    System.out.println("A continuacion podra registrarse como un nuevo usuario, por favor complete los siguientes campos y se validara la disponibilidad del usuario solicitado.");
                    System.out.println("Nombre:");
                    tempName = readScan.nextLine();
                    System.out.println("Usuario:");
                    tempUser = readScan.nextLine();
                    if(existUser(tempUser)){
                        System.out.println("Usuario ya existente, vuelva a intentarlo por favor.\n");
                        break;
                    }
                    else{
                        System.out.println("Password:");
                        tempPass = readScan.nextLine();
                        addUser(tempName,tempUser,tempPass);
                        System.out.println("Hola " + tempName + " , su usuario ha sido registrado! Por favor, inicie sesion.\n");
                    }
                    break;
                case "3" :
                    System.out.println("Saliendo del programa...\n");
                    System.exit(0);
                default :
                    System.out.println("Debe seleccionar una opcion valida, intente nuevamente.\n");
            }
        }
    }

    static boolean existUser(String usuario){
        if(cantUsuarios > 0){
            for(int i = 0;i < cantUsuarios;i++){
                if(usuarios[i].user.equals(usuario)){
                    return true;
                }
            }
        }
        return false;
    }
    
    static void addUser(String name,String user,String pass){
        usuarios = (usuario[])resizeUsuarios(usuarios,usuarios.length+1);
        usuarios[cantUsuarios].name = name;
        usuarios[cantUsuarios].user = user;
        usuarios[cantUsuarios].pass = pass;
        cantUsuarios++;
    }
    
    static boolean login(String user, String pass){
        for(int i=0;i<cantUsuarios;i++){
            if(usuarios[i].user.equals(user)){
                if(usuarios[i].pass.equals(pass)){
                    actualUser = i;
                    return true;
                }
            }
        }
        return false;
    }
    
    static usuario[] resizeUsuarios(usuario[] oldUsuarios,int newSize){
        int oldSize = oldUsuarios.length;
        usuario[] newUsuarios = new usuario[newSize];
        for(int i=0;i<newSize;i++){
                newUsuarios[i]= new usuario();
            }
        System.arraycopy(oldUsuarios, 0, newUsuarios, 0, oldSize);
        return newUsuarios;
    }
    
    static void menu(){
        Scanner readScan = new Scanner (System.in);
        String tempNit;
        String tempName;
        String tempDireccion;
        boolean logged = true;
        while(logged){
            System.out.println("\nHola " + usuarios[actualUser].name + ", bienvenido al Sistema de Caja 'Tiendita'");
            System.out.println("Seleccione la accion que desea realizar:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Generar Reporte Ingresos Facturados");
            System.out.println("3. Generar Reporte Cuentas Corrientes");
            System.out.println("4. Salir\n");
            String opcion = readScan.nextLine ();
            switch(opcion) {
                case "1" :
                    System.out.println("Ingrese el nit del cliente para validar si es nuevo o si ya existe en nuestro sistema:");
                    tempNit = readScan.nextLine();
                    if(existClient(tempNit)){
                        System.out.println("Cliente encontrado en el sistema");
                    }
                    else{
                        System.out.println("El usuario no existe en nuestra sistema, ingrese el nombre y direccion del cliente para registrarlo");
                        System.out.println("Ingrese el nombre del cliente:");
                        tempName = readScan.nextLine();
                        System.out.println("Ingrese la direccion del cliente:");
                        tempDireccion = readScan.nextLine();
                        addClient(tempNit,tempName,tempDireccion);
                    }
                    clientes[actualClient].menu(invActual);
                    break;
                case "2" :
                    getReporteFacturas();
                    break;
                case "3" :
                    getReporteCuentas();
                    break;
                case "4" :
                    System.out.println("Cerrando sesion...\n");
                    logged = false;
                    break;
                default :
                    System.out.println("Debe seleccionar una opcion valida, intente nuevamente.\n");
            }
        }
    }
    
    static boolean existClient(String nit){
        if(cantClientes > 0){
            for(int i = 0;i < cantClientes;i++){
                if(clientes[i].nit.equals(nit)){
                    actualClient = i;
                    return true;
                }
            }
        }
        return false;
    }
    
    static void addClient(String nit,String name,String direccion){
        clientes = (cliente[])resizeClientes(clientes,clientes.length+1);
        clientes[cantClientes].nit = nit;
        clientes[cantClientes].nombre = name;
        clientes[cantClientes].direccion = direccion;
        actualClient = cantClientes;
        cantClientes++;
    }
    
    static cliente[] resizeClientes(cliente[] oldClientes,int newSize){
        int oldSize = oldClientes.length;
        cliente[] newClientes = new cliente[newSize];
        for(int i=0;i<newSize;i++){
                newClientes[i]= new cliente();
            }
        System.arraycopy(oldClientes, 0, newClientes, 0, oldSize);
        return newClientes;
    }
    
    static void getReporteFacturas(){
        System.out.println("REPORTE DE INGRESOS POR TIPO");
        int depositoEfectivo = 0;
        int depositoCheques = 0;
        int depositoBanco = 0;
        int ingresoTipo = 0;
        System.out.println("..... Ingresos en Efectivo .....");
        for(int i=0;i<clientes.length;i++){
            for(int j=0;j<clientes[i].facturas.length;j++){
                if("Efectivo".equals(clientes[i].facturas[j].tipoPago)){
                    System.out.println("Cliente: " + clientes[i].nombre + " NIT: " + clientes[i].nit + " Ingreso: Q" + clientes[i].facturas[j].total + " Tipo de Pago: " + clientes[i].facturas[j].tipoPago);
                    ingresoTipo = ingresoTipo + clientes[i].facturas[j].total;
                }  
            }
        }
        System.out.println("Ingresos Totales en Efectivo: Q" + ingresoTipo);
        depositoEfectivo = ingresoTipo;
        ingresoTipo = 0;
        System.out.println("..... Ingresos en Tarjetas de Debito .....");
        for(int i=0;i<clientes.length;i++){
            for(int j=0;j<clientes[i].facturas.length;j++){
                if("Tarjeta de Debito".equals(clientes[i].facturas[j].tipoPago)){
                    System.out.println("Cliente: " + clientes[i].nombre + " NIT: " + clientes[i].nit + " Ingreso: Q" + clientes[i].facturas[j].total + " Tipo de Pago: " + clientes[i].facturas[j].tipoPago);
                    ingresoTipo = ingresoTipo + clientes[i].facturas[j].total;
                }  
            }
        }
        System.out.println("Ingresos Totales por Tarjeta de Debito: Q" + ingresoTipo);
        ingresoTipo = 0;
        System.out.println("..... Ingresos en Tarjetas de Credito .....");
        for(int i=0;i<clientes.length;i++){
            for(int j=0;j<clientes[i].facturas.length;j++){
                if("Tarjeta de Credito".equals(clientes[i].facturas[j].tipoPago)){
                    System.out.println("Cliente: " + clientes[i].nombre + " NIT: " + clientes[i].nit + " Ingreso: Q" + clientes[i].facturas[j].total + " Tipo de Pago: " + clientes[i].facturas[j].tipoPago);
                    ingresoTipo = ingresoTipo + clientes[i].facturas[j].total;
                }  
            }
        }
        System.out.println("Ingresos Totales por Tarjeta de Credito: Q" + ingresoTipo);
        ingresoTipo = 0;
        System.out.println("..... Ingresos en Cheques .....");
        for(int i=0;i<clientes.length;i++){
            for(int j=0;j<clientes[i].facturas.length;j++){
                if("Cheque".equals(clientes[i].facturas[j].tipoPago)){
                    System.out.println("Cliente: " + clientes[i].nombre + " NIT: " + clientes[i].nit + " Ingreso: Q" + clientes[i].facturas[j].total + " Tipo de Pago: " + clientes[i].facturas[j].tipoPago);
                    ingresoTipo = ingresoTipo + clientes[i].facturas[j].total;
                }  
            }
        }
        System.out.println("Ingresos Totales por Cheque: Q" + ingresoTipo);
        depositoCheques = ingresoTipo;
        depositoBanco = depositoEfectivo + depositoCheques;
        System.out.println(".............................");
        System.out.println("Depositar en Banco el Total de Q" + depositoBanco);
        System.out.println("de lo cual Q" + depositoEfectivo + " son Efectivo y Q" + depositoCheques + " es en cheques.");
        System.out.println(".............................");
    }
    
    static void getReporteCuentas(){
        System.out.println("REPORTE DE CUENTAS CORRIENTES");
        System.out.println(".............................");
        int deudaTotal = 0;
        for(int i=0;i<clientes.length;i++){
            if(clientes[i].debe){
                System.out.println("Cliente: " + clientes[i].nombre + " NIT: " + clientes[i].nit + " Deuda: Q" + clientes[i].cuenta.total);
                deudaTotal = deudaTotal + clientes[i].cuenta.total;
            }
        }
        System.out.println("..............................");
        System.out.println("La deuda total de las cuentas es: Q" + deudaTotal);
        System.out.println("..............................");
    }
}

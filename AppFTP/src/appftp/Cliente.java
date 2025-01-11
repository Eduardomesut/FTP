/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appftp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Cliente {
    public static void main(String[] args) {
        System.out.println("Bienvenido al servicio de transferencia FTP");
        boolean salir = false;
        GestorFTP gestor = new GestorFTP();
        do { 
            mostrarAyuda();
            try {
                System.out.println(gestor.conectar("10.0.2.15", 21, "marco", "marco"));
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
        } while (salir);
        
        
    }
    
    public static void mostrarAyuda(){
        System.out.println("AYUDA");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("-Para conectarse a un servidor remoto, comando: connect NombreServidor Usuario/Contraseña");
        System.out.println("-Para mostrar contenido del directorio de trabajo, comando: List");
        System.out.println("-Para cambiar el directorio de trabajo, comando: changePath NuevoPAth");
        System.out.println("-Para descargar fichero delde el servidor a local, comando: down NombreFicheroRemoto");
        System.out.println("-Para subir fichero desde local al servidor, comando: up NombreFicheroLocal");
        System.out.println("-Para desconectar de un servidor remoto, comando: Disconnect");
        System.out.println("-------------------------------------------------------------------------");
        
        
        
    }
}

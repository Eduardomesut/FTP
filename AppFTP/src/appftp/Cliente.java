/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appftp;

import java.io.IOException;
import java.util.Scanner;
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
        boolean conectado = false;
        GestorFTP gestor = new GestorFTP();
        String respuesta;
        Scanner sc = new Scanner(System.in);
        do { 
            //connect 10.0.2.15 marco/marco
            mostrarAyuda();
            respuesta = sc.nextLine();
            String [] partes = respuesta.split(" ");
            try {
                if (respuesta.contains("connect") && partes.length == 3 && respuesta.contains("/")) {
                    
                    String servidor = partes[1];
                    String usuario = partes[2].substring(0, partes[2].indexOf("/"));
                    String password = partes[2].substring(partes[2].indexOf("/")+1, partes[2].length());

                    if (!conectado) {
                        if (gestor.conectar(servidor, 21, usuario, password)) {
                            System.out.println("Conexión establecida correctamente con " + servidor);
                            conectado = true;
                        }else{
                            System.out.println("Error en los datos de conexión!!");
                        }
                    }else{
                        System.out.println("Ya esta conectado a otro servidor, desconecte primero");
                    }
                }else if (respuesta.equals("List")) {
                    if (conectado) {
                        gestor.listarFicheros();
                    }else{
                        System.out.println("Necesitas conexión primero con un servidor!");
                    }
                    
                    
                }else if (respuesta.contains(respuesta)) {
                    
                }else if (respuesta.contains(respuesta)) {
                    
                }else if (respuesta.contains(respuesta)) {
                    
                }else{
                    
                }
               
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
        } while (!salir);
        
        
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

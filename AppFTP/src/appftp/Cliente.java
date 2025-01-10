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
            try {
                System.out.println(gestor.conectar("10.0.2.15", 21, "marco", "marco"));
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
        } while (salir);
        
        
    }
    
}

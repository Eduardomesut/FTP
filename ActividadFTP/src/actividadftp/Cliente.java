/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividadftp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Cliente {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        System.out.println("Bienvenido al gestor FTP");
        
        try {
            gestor.conectar("10.0.2.15", 21,"marco", "marco");
            System.out.println("Conectado");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

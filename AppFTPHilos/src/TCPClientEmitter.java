/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eduardo
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClientEmitter {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            
            output.writeUTF("EMISOR");
            output.flush();
            
            System.out.println("Conectado al servidor. Escriba un mensaje (o '*' para salir):");
            
            while (true) {
                String message = scanner.nextLine();
                output.writeUTF(message);
                output.flush();
                
                if ("*".equals(message)) {
                    System.out.println("Desconectando...");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

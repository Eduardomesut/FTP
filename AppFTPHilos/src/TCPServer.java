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
import java.util.*;

public class TCPServer {
    private static final int PORT = 5000;
    private static Set<ObjectOutputStream> receivers = Collections.synchronizedSet(new HashSet<>());
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
                
                String clientType = input.readUTF();
                
                if ("EMISOR".equals(clientType)) {
                    handleEmitter();
                } else if ("RECEPTOR".equals(clientType)) {
                    handleReceiver();
                }
            } catch (IOException e) {
                System.out.println("Cliente desconectado abruptamente");
            } finally {
                closeResources();
            }
        }

        private void handleEmitter() throws IOException {
            String message;
            while ((message = input.readUTF()) != null) {
                if ("*".equals(message)) {
                    System.out.println("Cliente emisor desconectado");
                    break;
                }
                System.out.println("Mensaje recibido: " + message);
                sendMessageToReceivers(message);
            }
        }

        private void handleReceiver() {
            receivers.add(output);
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Cliente receptor desconectado abruptamente");
            } finally {
                receivers.remove(output);
                closeResources();
            }
        }

        private void sendMessageToReceivers(String message) {
            synchronized (receivers) {
                Iterator<ObjectOutputStream> iterator = receivers.iterator();
                while (iterator.hasNext()) {
                    try {
                        ObjectOutputStream receiver = iterator.next();
                        receiver.writeUTF(message);
                        receiver.flush();
                    } catch (IOException e) {
                        iterator.remove();
                    }
                }
            }
        }

        private void closeResources() {
            try {
                if (output != null) output.close();
                if (input != null) input.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

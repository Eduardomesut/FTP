/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividadftp;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author marco
 */
public class Gestor {
    
    private FTPClient clienteFTP;
    private String SERVIDOR;
    private int PUERTO;
    private String USUARIO;
    private String PASSWORD;

    public Gestor() {
        clienteFTP = new FTPClient();
    }
    
    public void conectar(String servidor, int puerto, String user, String password) throws SocketException, IOException {
        this.SERVIDOR = servidor;
        this.PUERTO = puerto;
        this.USUARIO = user;
        this.PASSWORD = password;
        clienteFTP.connect(SERVIDOR, PUERTO);
        int respuesta = clienteFTP.getReplyCode();
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            clienteFTP.disconnect();
            throw new IOException("Error al conectar con el servidor FTP");
        }
    
        boolean credencialesOK = clienteFTP.login(USUARIO, PASSWORD);
        if (!credencialesOK) {
            throw new IOException("Error al conectar con el servidor FTP. Credenciales incorrectas.");
        }
        clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

    }
    public void desconectar() throws IOException {
        clienteFTP.disconnect();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author marco
 */
public class GestorFTP {
    private FTPClient clienteFTP;
    private String SERVIDOR;
    private int PUERTO;
    private String USUARIO;
    private String PASSWORD;

    public GestorFTP() {
        clienteFTP = new FTPClient();
    }
    
    public boolean conectar(String servidor, int puerto, String usuario, String password) throws SocketException, IOException {
        this.SERVIDOR = servidor;
        this.PUERTO = puerto;
        this.USUARIO = usuario;
        this.PASSWORD = password;
        clienteFTP.connect(SERVIDOR, PUERTO);
        int respuesta = clienteFTP.getReplyCode();
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            clienteFTP.disconnect();
            System.out.println("Error al conectar con el servidor FTP");
            return false;
        }
    
        boolean credencialesOK = clienteFTP.login(USUARIO, PASSWORD);
        if (!credencialesOK) {
            return false;
           
        }
        clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
        return true;

    }
    
    public void desconectar() throws IOException {
        clienteFTP.disconnect();
    }
    
    public boolean subirFichero(String path) throws IOException {
        File ficheroLocal = new File(path);
        InputStream is = new FileInputStream(ficheroLocal);
        boolean enviado = clienteFTP.storeFile(ficheroLocal.getName(), is);
        is.close();
        return enviado;
    }

    public boolean descargarFichero(String ficheroRemoto, String pathLocal) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(pathLocal));
        boolean recibido = clienteFTP.retrieveFile(ficheroRemoto, os);
        os.close();
        return recibido;
    }
    public void listarFicheros() throws IOException{
        String nombreDirectorio = this.clienteFTP.printWorkingDirectory();
        System.out.println(nombreDirectorio);
        FTPFile[] ficheros = this.clienteFTP.listFiles();
        Arrays.asList(ficheros).forEach(x -> System.out.println(x));
        
    }
    

}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class EnvidoClient{

    private Socket client;
    private DataInputStream din;
    private DataOutputStream dout;
    private boolean isEnabled;

    public EnvidoClient(String ip,int port){
        try{
            client = new Socket(InetAddress.getByName(ip),port);
            System.out.println("cliente conectado al servidor de manera exitosa.");
            isEnabled = false;
        } catch(IOException ioe){
            System.out.println("no pudimos crear el cliente. abortamos.");
            System.exit(2);
        }
    }

    public String receive(){
        String readed = new String();
        if(!isEnabled)
            this.enable();
        try{
            readed = din.readUTF();
        } catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"No pudimos recibir el paquete. abortamos.","AVISO DE CONEXION",JOptionPane.INFORMATION_MESSAGE);
            System.exit(3);
        }
        return readed;}

    public void send(Object o){
        if(!isEnabled)
            this.enable();
        try{
            dout.writeUTF(o.toString());
            dout.flush();
        } catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"No pudimos enviar el paquete. abortamos.","AVISO DE CONEXION",JOptionPane.INFORMATION_MESSAGE);
            System.exit(4);
        }
    }

    public void enable(){
        try{
            din = new DataInputStream(client.getInputStream());
            dout = new DataOutputStream(client.getOutputStream());
        } catch(IOException ioe){
            System.out.println("no pudimos habilitar el cliente. abortamos.");
            System.exit(9);
        }
        isEnabled = true;
    }

    public void close(){
        try{
            client.close();
            din.close();
            dout.close();
        } catch(IOException ioe){
            System.out.println("no pudimos cerrar el cliente.");
        }
    }
}

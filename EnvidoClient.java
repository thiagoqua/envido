import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class EnvidoClient{

    private Socket client;
    private DataInputStream din;
    private DataOutputStream dout;

    public EnvidoClient(String ip,int port){
        try{
            client = new Socket(InetAddress.getByName(ip),port);
            System.out.println("client succesfully connected");
        } catch(IOException ioe){
            System.out.println("no pudimos crear el cliente. abortamos.");
            System.exit(2);
        }
    }

    public String receive(){
        String readed = new String();
        try{
            din = new DataInputStream(client.getInputStream());
            readed = din.readUTF();
        } catch(IOException ioe){
            System.out.println("no pudimos recibir el paquete. abortamos.");
            System.exit(3);
        }
        return readed;}

    public void send(Object o){
        try{
            dout = new DataOutputStream(client.getOutputStream());
            dout.writeUTF(o.toString());
            dout.flush();
        } catch(IOException ioe){
            System.out.println("no pudimos enviar el paquete. abortamos.");
            System.exit(4);
        }
    }

    public void close(){
        try{
            client.close();
            din.close();
            dout.close();
        } catch(IOException ioe){
            System.out.println("no pudimos cerrar el cliente. abortamos.");
            //System.exit(2);
        }
    }
}

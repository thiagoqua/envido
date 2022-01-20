import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EnvidoServer{

    ServerSocket server;
    Socket listener;
    DataInputStream din;
    DataOutputStream dout;

    public EnvidoServer(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("server succesfully created");
        } catch(IOException ioe){
            System.out.println("no pudimos crear el servidor. abortamos.");
            System.exit(5);
        }
    }

    public String receive(){
        String readed = new String();
        try{
            listener = server.accept();
            din = new DataInputStream(listener.getInputStream());
            readed = din.readUTF();
        } catch(IOException ioe){
            System.out.println("no pudimos recibir el paquete. abortamos.");
            System.exit(6);
        }
        return readed;}

    public void send(Object o){
        try{
            //listener = server.accept();
            dout = new DataOutputStream(listener.getOutputStream());
            dout.writeUTF(o.toString());
            dout.flush();
        } catch(IOException ioe){
            System.out.println("no pudimos enviar el paquete. abortamos.");
            System.exit(7);
        }
    }

    public void close(){
        try{
            server.close();
            listener.close();
            din.close();
            dout.close();
        } catch(IOException ioe){
            System.out.println("no pudimos cerrar el servidor. abortamos.");
            //System.exit(2);
        }
    }
}

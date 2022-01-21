import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EnvidoServer{

    private ServerSocket server;
    private Socket listener;
    private DataInputStream din;
    private DataOutputStream dout;
    private boolean isConnected;        //se activa cuando algún cliente se conecta

    public EnvidoServer(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("servidor creado de manera exitosa.");
            isConnected = false;
        } catch(IOException ioe){
            System.out.println("no pudimos crear el servidor. abortamos.");
            System.exit(5);
        }
    }

    public String receive(){
        String readed = new String();
        if(!isConnected){
            this.somebodyIsConnected();
        }
        try{
            readed = din.readUTF();
        } catch(IOException ioe){
            System.out.println("no pudimos recibir el paquete. abortamos.");
            System.exit(6);
        }
    return readed;}

    public void send(Object o){
        try{
            dout.writeUTF(o.toString());
            dout.flush();
        } catch(IOException ioe){
            System.out.println("no pudimos enviar el paquete. abortamos.");
            System.exit(7);
        }
    }

    public void enable(){
        try{
            din = new DataInputStream(listener.getInputStream());
            dout = new DataOutputStream(listener.getOutputStream());
            isConnected = true;
        } catch(IOException ioe){
            System.out.println("no pudimos conectar con el cliente. abortamos.");
            System.exit(8);
        }
    }

    public boolean somebodyIsConnected(){
        try{
            listener = server.accept();
            if(listener.isConnected()){
                System.out.println("cliente con ip '" + listener.getInetAddress() + "' conectado al puerto " +
                listener.getPort() + " de manera exitosa.");
            }
            else
                return false;
        } catch(IOException ioe){}
        this.enable();              //si se conecto algun cliente es cuando abro los canales de entrada-salida
    return true;}

    public boolean getIsConnected(){return isConnected;}

    public void close(){
        try{
            server.close();
            listener.close();
            din.close();
            dout.close();
        } catch(IOException ioe){
            System.out.println("no pudimos cerrar el servidor.");
        }
    }
}

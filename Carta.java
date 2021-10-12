package truco;
public class Carta{

	private int numero;
    private String palo;
    
    public Carta(){
        this(0,"");
    }

    public Carta(int numero,String palo){   //sino ya seteo el número y el palo
        this.numero = numero;
        this.palo = new String(palo);
    }

    public int getNumero(){
        return numero;
    }

    public String getPalo(){
        return palo;
    }
    
    public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	@Override
	public String toString() {
		return ( "(" + this.numero + "," + this.palo + ")" );
	}
	
}
//package truco;
//FORCING
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

    public int getNumero(){return numero;}

    public String getPalo(){return palo;}
    
    public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

    private Carta[] getOrden(){
        Carta orden[] = new Carta[14];
        orden[0] = new Carta(1,"espada");
        orden[1] = new Carta(1,"basto");
        orden[2] = new Carta(7,"espada");
        orden[3] = new Carta(7,"oro");
        orden[4] = new Carta(3,"");
        orden[5] = new Carta(2,"");
        orden[6] = new Carta(1,"");
        orden[7] = new Carta(12,"");
        orden[8] = new Carta(11,"");
        orden[9] = new Carta(10,"");
        orden[10] = new Carta(7,"");
        orden[11] = new Carta(6,"");
        orden[12] = new Carta(5,"");
        orden[13] = new Carta(4,"");
    return orden;}
	
	@Override
	public String toString() {
		return ( "(" + this.numero + "," + this.palo + ")" );
	}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Carta){
            Carta c = (Carta) obj;
            return numero == c.getNumero() && palo.equals(c.getPalo());
        }
    return false;}
	
    public boolean mata(Carta given) throws PardaExeption{
        int indexOfGiven,indexOfMine;
        Carta auxx = given;
        Carta auxmine = new Carta();
        Carta orden[] = getOrden();
        indexOfGiven = indexOfMine = -1;
        auxmine.setNumero(numero);
        auxmine.setPalo(palo);
        //le borro el palo ya que da lo mismo si lo tiene o no para el orden
        if(!auxx.equals(orden[0]) && !auxx.equals(orden[1]) && !auxx.equals(orden[2]) && !auxx.equals(orden[3]))
            auxx.setPalo("");
        if(!auxmine.equals(orden[0]) && !auxmine.equals(orden[1]) && !auxmine.equals(orden[2]) && !auxmine.equals(orden[3]))
            auxmine.setPalo("");
        for(int i=0;i<orden.length;++i){
            if(orden[i].equals(auxx))
                indexOfGiven = i;
            if(orden[i].equals(auxmine))
                indexOfMine = i;
            if(indexOfMine > 0 && indexOfGiven > 0)
                break;
        }
        if(indexOfMine < indexOfGiven)
            return true;
        else if(indexOfMine == indexOfGiven)
            throw new PardaExeption();
    return false;}
}
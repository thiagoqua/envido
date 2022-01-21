public class Carta{

	private int numero;
    private String palo;
    private static final Carta[] ORDEN = new Carta[14];

    public Carta(){
        this(0,"");
    }

    public Carta(int numero,String palo){
        this.numero = numero;
        this.palo = new String(palo);
    }

    public int getNumero(){return numero;}

    public String getPalo(){return palo;}
    
    public void setNumero(int numero) {this.numero = numero;}

	public void setPalo(String palo) {this.palo = palo;}

    public static Carta builder(Carta c){
        Carta temp = new Carta();
        temp.numero = c.numero;
        temp.palo = c.palo;
    return temp;}

    private static void initOrden(){
        ORDEN[0] = new Carta(1,"espada");
        ORDEN[1] = new Carta(1,"basto");
        ORDEN[2] = new Carta(7,"espada");
        ORDEN[3] = new Carta(7,"oro");
        ORDEN[4] = new Carta(3,"");
        ORDEN[5] = new Carta(2,"");
        ORDEN[6] = new Carta(1,"");
        ORDEN[7] = new Carta(12,"");
        ORDEN[8] = new Carta(11,"");
        ORDEN[9] = new Carta(10,"");
        ORDEN[10] = new Carta(7,"");
        ORDEN[11] = new Carta(6,"");
        ORDEN[12] = new Carta(5,"");
        ORDEN[13] = new Carta(4,"");
    }
	
    
    public int returnOrden(Carta c){
    	int i=0;
    	
        /*ANALIZO LAS CARTAS IMPORTANTES PRIMERO*/
        
        for(int j=0;j<4;j++) {
        	
            if(c.getNumero() == ORDEN[i].getNumero() && c.getPalo().equals(ORDEN[i].getPalo())) {
            	return i;
            }
            else {
            	i++;
            }
        	
        }
        for(int j=0;j<10;j++) {
        	if(c.getNumero() == ORDEN[i].getNumero()) {
            	return i;
            }
            else {
            	i++;
            }
        }
        
        return -1;
    
    }
    
    
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
        Carta auxx = builder(given);
        Carta auxmine = builder(this);
        if(ORDEN[0] == null)
            initOrden();
        indexOfGiven = indexOfMine = -1;
        /*le borro el palo ya que da lo mismo si lo tiene o no para el orden*/
        if(!auxx.equals(ORDEN[0]) && !auxx.equals(ORDEN[1]) && !auxx.equals(ORDEN[2]) && !auxx.equals(ORDEN[3]))
            auxx.setPalo("");
        if(!auxmine.equals(ORDEN[0]) && !auxmine.equals(ORDEN[1]) && !auxmine.equals(ORDEN[2]) && !auxmine.equals(ORDEN[3]))
            auxmine.setPalo("");
        for(int i=0;i<ORDEN.length;++i){
            if(ORDEN[i].equals(auxx))
                indexOfGiven = i;
            if(ORDEN[i].equals(auxmine))
                indexOfMine = i;
            if(indexOfMine > 0 && indexOfGiven > 0)
                break;
        }
        if(indexOfMine < indexOfGiven)
            return true;
        else if(indexOfMine == indexOfGiven)
            throw new PardaExeption(numero);
    return false;}

    public boolean isGood(){
        int index;
        Carta mine = new Carta();
        mine.setNumero(numero);
        mine.setPalo(palo);
        if(ORDEN[0] == null)
            initOrden();
        for(index = 0;index < ORDEN.length;++index)
            if(mine.equals(ORDEN[index]))
                break;
        if(index <= 5)                              //si mi carta es un 2 o mos grande
            return true;
    return false;}

    @Override
    public int hashCode(){return numero * (int)this.palo.charAt(0);}
}
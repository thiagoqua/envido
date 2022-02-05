import java.util.Arrays;

public class Jugador {
	
	protected int puntos;
	protected String tag;
	protected boolean soy_mano;						//si el mazo está a la izquierda del jugador
	protected boolean bandera;						//si es su turno durante la ronda
	protected boolean cantoPrimi;
	protected boolean truco;
	public Carta cartas[];
	public Carta copyCartas[];		//mantiene una copia de las cartas que no se anulan, como solución provisoria a la excepción
	
	/*MAS ADELANTE SE VERA SI EL NOMBRE SE INICIA DIRECTAMENTE EN EL CONSTRUCTOR, O LOS JUGADORES LO TIPEAN DESDE CONSOLA/INTERFAZ */
	
	public Jugador() {
		//this.tag = tag;
		this.puntos = 0;
		this.soy_mano = false;
		this.bandera = false;
		this.cantoPrimi = false;	
		this.truco = false;			//se debe activar cuando gano el truco
		this.cartas = new Carta[3];
		this.copyCartas = new Carta[3];
	}
	
	/* GETTERS */
	
	public int getPuntos() {return puntos;}
	
	public String getTag() {return tag;}
	
	public boolean getMano() {return soy_mano;}
	
	public boolean getBandera() {return bandera;}
	
	public boolean getCantoPrimi() {return cantoPrimi;}
	
	/* SETTERS */
	
	/*Una primera forma para inicializar las banderas es directamente pasarle un argumento*/
	/*Habria que ver mas adelante cual es la forma mas efectiva*/
	
	public void setTag(String tag) {this.tag = tag;}
	
	public void setBandera(boolean b) {this.bandera = b;}	
	
	public void setCantoPrimi(boolean m) {this.cantoPrimi = m;}
	
	public void setMano(boolean m) {this.soy_mano = m;}	

	public void setAmbasManos(boolean miMano,Jugador j2){
		setMano(miMano);
	}
	
	/* METODOS VARIOS */
	
	public boolean isWinning(Jugador j2){
		return puntos > j2.getPuntos();
	}

	public void addPuntos(int nuevos){
		if((this.puntos + nuevos) <= 30)
			this.puntos += nuevos;
		else 
			this.puntos = 30;
	}

	public void addPuntosF(Jugador j2){		//agrega puntos de la falta envido. el que la invoca debe ser el que la ganó.
		if(isWinning(j2))
			puntos = 30;
		else
			puntos += 30 - j2.getPuntos();
	}

	public Carta tirar(int index){
		Carta temp = Carta.builder(cartas[index]);
		cartas[index] = null;
	return temp;}

	public boolean envido(Jugador j2){				//retorna true si el que llama gana el envido
		if(getPuntosEnvido() == j2.getPuntosEnvido())
			return soy_mano;
	return getPuntosEnvido() > j2.getPuntosEnvido();}

	public int getPuntosEnvido(){
	//A LA HORA DE ENTREGAR VOLVER A PONER CARTAS EN VEZ DE COPYCARTAS
		int puntos;
		if(copyCartas[0].getPalo().equals(copyCartas[1].getPalo()) && copyCartas[1].getPalo().equals(copyCartas[2].getPalo())){	//caso flor
			int c1,c2,c3;
			c1 = copyCartas[0].getNumero(); c2 = copyCartas[1].getNumero(); c3 = copyCartas[2].getNumero();
			/*si alguna carta es 10, 11 o 12 no suma para los puntos*/
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			if(c3 >= 10 && c3 <= 12)
				c3 = 0;
			
			if(c1 >= c2 && c1 >= c3)				//la primer mas grande
				if(c2 >= c3)						//la segunda mas grande
					puntos = c1 + c2 + 20;
				else								//la segunda mas grande
					puntos = c1 + c3 + 20;
			else if(c2 >= c1 && c2 >= c3){			//la primer mas grande
				if(c1 >= c3)						//la segunda mas grande
					puntos = c1 + c2 + 20;
				else								//la segunda mas grande
					puntos = c2 + c3 + 20;
			}
			else{									//la primer carta mas grande es la tercera
				if(c1 >= c2)
					puntos = c1 + c3 + 20;
				else
					puntos = c2 + c3 + 20;
			}
		}
		else if(copyCartas[0].getPalo().equals(copyCartas[1].getPalo())){
			int c1,c2;
			c1 = copyCartas[0].getNumero(); c2 = copyCartas[1].getNumero();
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else if(copyCartas[0].getPalo().equals(copyCartas[2].getPalo())){
			int c1,c2;
			c1 = copyCartas[0].getNumero(); c2 = copyCartas[2].getNumero();
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else if(copyCartas[1].getPalo().equals(copyCartas[2].getPalo())){
			int c1,c2;
			c1 = copyCartas[1].getNumero(); c2 = copyCartas[2].getNumero();
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else{										//no tiene cartas del mismo palo
			int c1,c2,c3;
			c1 = copyCartas[0].getNumero(); c2 = copyCartas[1].getNumero(); c3 = copyCartas[2].getNumero();
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			if(c3 >= 10 && c3 <= 12)
				c3 = 0;
	
			if(c1 >= c2 && c1 >= c3)				//la primer carta es la mas grande
				puntos = c1;
			else if(c2 >= c1 && c2 >= c3)			//la segunda carta es la mas grande
				puntos = c2;	
			else									//la tercer carta es la más grande
				puntos = c3;
		}
	return puntos;}

	public void ganeTruco(){
		truco = true;
	}

	public void reset(){
		Arrays.fill(cartas,null);
		Arrays.fill(copyCartas,null);
		soy_mano = bandera = cantoPrimi = truco = false;
	}

	protected boolean isCantado(String quieroCantar,String cantos[]){
        for(String temp : cantos)
            if(temp != null && temp.equals(quieroCantar))
                return true;
    return false;}

	public void voyMazo(String cantos[],Jugador j2,boolean cantoEnvido){
		if(!cantoEnvido && (cantos[0] == "" || cantos[0] == null)){
			boolean tiroCartas = false;
			for(Carta temp : j2.cartas){
				if(temp == null){
					tiroCartas = true;
					break;
				}
			}
			if(!tiroCartas)
				j2.addPuntos(2);
			else
				j2.addPuntos(1);
		}
		else if(isCantado("truco",cantos)){
			if(isCantado("retruco",cantos)){
				if(isCantado("vale cuatro",cantos)){
					j2.addPuntos(4);
				}
				else{
					j2.addPuntos(3);
				}
			}
			else{
				j2.addPuntos(2);
			}
		}
		else{
			j2.addPuntos(1);
		}
	}
}
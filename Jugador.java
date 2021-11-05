//package truco
//FORCING
public class Jugador {

	private int puntos;
	private String tag;					//nombre del jugador
	private boolean soy_mano;
	private boolean bandera;
	private boolean cantoPrimi;
	public Carta cartas[];
	
	/*MAS ADELANTE SE VERA SI EL NOMBRE SE INICIA DIRECTAMENTE EN EL CONSTRUCTOR, O LOS JUGADORES LO TIPEAN DESDE CONSOLA/INTERFAZ */
	
	public Jugador(String tag) {
		this.tag = tag;
		this.puntos = 0;
		this.soy_mano = false;
		this.bandera = false;
		this.cantoPrimi = false;	/* cuando se inicialize el arreglo de cantos, el jugador que cante primero inicializa
		esta variable en true para poder sacar bien los puntos después */
		this.cartas = new Carta[3];
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
	
	public void setMano(boolean m) {this.soy_mano = m;}	
	
	public void setCantoPrimi(boolean m) {this.cantoPrimi = m;}

	public boolean isWinning(Jugador j2){
		return puntos > j2.getPuntos();
	}

	public void addPuntos(int nuevos){
		if((this.puntos + nuevos) <= 30)
			this.puntos += nuevos;
		else 
			this.puntos = 30;
	}

	public void addPuntosF(Jugador j2){		//agrega puntos si se canta la falta envido. el que la invoca es el que la ganó.
		if(isWinning(j2))
			puntos = 30;
		else
			puntos += 30 - j2.getPuntos();
	}
	
	/* POSIBLES FUNCIONES QUE SE LLEGUEN A NECESITAR */
	/* REPRESENTAN TODAS LAS ACCIONES QUE PUEDE REALIZAR UN JUGADOR EN UNA RONDA */
	/* ES NECESARIO CONECTAR EL CODIGO DE ESTAS FUNCIONES CON LOS BOTONES DE LA INTERFAZ */
	
	public void cantar(){
	}
	
	public void seleccionarCarta(){
	}
	
	public void irseAlMazo(){
	}
	
	public void rendirse(){
	}
	
	public void quieroNoquiero(){
	}

	public Carta tirar(int index){
		Carta temp = cartas[index];
		cartas[index] = null;
	return temp;}

	public boolean envido(Jugador j2){
		if(getPuntosEnvido() == j2.getPuntosEnvido())
			return soy_mano;				//si el jugador que invoca es mano gana el envido
		return getPuntosEnvido() > j2.getPuntosEnvido();
	}

	public int getPuntosEnvido(){
		int puntos;
		if(cartas[0].getPalo().equals(cartas[1].getPalo()) && cartas[1].getPalo().equals(cartas[2].getPalo())){	//caso flor
			int c1,c2,c3;
			c1 = cartas[0].getNumero(); c2 = cartas[1].getNumero(); c3 = cartas[2].getNumero();
			//si alguna carta es 10, 11 o 12 no suma para los puntos
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			if(c3 >= 10 && c3 <= 12)
				c3 = 0;
			
			if(c1 >= c2 && c1 >= c3)		//la primer mas grande
				if(c2 >= c3)				//la segunda mas grande
					puntos = c1 + c2 + 20;
				else						//la segunda mas grande
					puntos = c1 + c3 + 20;
			else if(c2 >= c1 && c2 >= c3){	//la primer mas grande
				if(c1 >= c3)				//la segunda mas grande
					puntos = c1 + c2 + 20;
				else						//la segunda mas grande
					puntos = c2 + c3 + 20;
			}
			else{							//la primer carta mas grande es la tercera
				if(c1 >= c2)
					puntos = c1 + c3 + 20;
				else
					puntos = c2 + c3 + 20;
			}
		}
		else if(cartas[0].getPalo().equals(cartas[1].getPalo())){
			int c1,c2;
			c1 = cartas[0].getNumero(); c2 = cartas[1].getNumero();
			//si alguna carta es 10, 11 o 12 no suma para los puntos
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else if(cartas[0].getPalo().equals(cartas[2].getPalo())){
			int c1,c2;
			c1 = cartas[0].getNumero(); c2 = cartas[2].getNumero();
			//si alguna carta es 10, 11 o 12 no suma para los puntos
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else if(cartas[1].getPalo().equals(cartas[2].getPalo())){
			int c1,c2;
			c1 = cartas[1].getNumero(); c2 = cartas[2].getNumero();
			//si alguna carta es 10, 11 o 12 no suma para los puntos
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			puntos = c1 + c2 + 20;
		}
		else{			//no tiene cartas del mismo palo
			int c1,c2,c3;
			c1 = cartas[0].getNumero(); c2 = cartas[1].getNumero(); c3 = cartas[2].getNumero();
			//si la carta es 10, 11 o 12 no suma para los puntos
			if(c1 >= 10 && c1 <= 12)
				c1 = 0;
			if(c2 >= 10 && c2 <= 12)
				c2 = 0;
			if(c3 >= 10 && c3 <= 12)
				c3 = 0;

			if(c1 >= c2 && c1 >= c3)		//la primer carta es la mas grande
				puntos = c1;
			else if(c2 >= c1 && c2 >= c3)	//la segunda carta es la mas grande
				puntos = c2;	
			else							//la tercer carta es la más grande
				puntos = c3;
		}
	return puntos;}
	
	public boolean truco(Jugador j2){
		return false;
	}
}
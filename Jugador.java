//package truco;

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
	
	public void setTag(String tag) {this.tag = tag;}
	
	/*Una primera forma para inicializar las banderas es directamente pasarle un argumento*/
	/*Habria que ver mas adelante cual es la forma mas efectiva*/
	
	public void setBandera(boolean b) {this.bandera = b;}	
	public void setMano(boolean m) {this.soy_mano = m;}	
	public void setCantoPrimi(boolean m) {this.cantoPrimi = m;}

	public void addPuntos(int nuevos){
		if((this.puntos + nuevos) <= 30)
			this.puntos+=nuevos;
		else 
			this.puntos = 30;
	}
	
	/* POSIBLES FUNCIONES QUE SE LLEGUEN A NECESITAR */
	/* REPRESENTAN TODAS LAS ACCIONES QUE PUEDE REALIZAR UN JUGADOR EN UNA RONDA */
	/* ES NECESARIO CONECTAR EL CODIGO DE ESTAS FUNCIONES CON LOS BOTONES DE LA INTERFAZ */
	
	public void cantar() {
	}
	
	public void seleccionarCarta() {
	}
	
	public void irseAlMazo() {
	}
	
	public void rendirse() {
	}
	
	public void quieroNoquiero() {
	}	

	public boolean envido(Jugador j2){
		//se verifica quien gana el envido
	return false;}
	
	public boolean truco(Jugador j2){
		//se verifica quien gana el truco
	return false;}
}

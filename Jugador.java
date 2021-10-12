package truco;

public class Jugador {

	private int puntos;
	private String tag;					//nombre del jugador
	private boolean soy_mano;
	private boolean bandera;
	
	/*MAS ADELANTE SE VERÁ SI EL NOMBRE SE INICIA DIRECTAMENTE EN EL CONSTRUCTOR, O LOS JUGADORES LO TIPEAN DESDE CONSOLA/INTERFAZ */
	
	public Jugador(String tag) {
		this.tag = tag;
		this.puntos = 0;
		this.soy_mano = false;
		this.bandera = false;
	}
	
	/* GETTERS */
	
	public int getPuntos() {return puntos;}
	public String getTag() {return tag;}
	public boolean getMano() {return soy_mano;}
	public boolean getBandera() {return bandera;}
	
	/* SETTERS */
	
	public void setPuntos(int puntos) {this.puntos = puntos;}
	public void setTag(String tag) {this.tag = tag;}
	
	/*Una primera forma para inicializar las banderas es directamente pasarle un argumento*/
	/*Habrá que ver mas adelante cual es la forma mas efectiva*/
	
	public void setBandera(boolean b) {this.bandera = b;}	
	public void setMano(boolean m) {this.soy_mano = m;}	
	
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

	
}

package truco;
public class Main{
 
    public static void main(String[] args){
    	
    	/* OBJETOS DE CLASES */
    	Jugador j1 = new Jugador("TIKI");
        Jugador j2 = new Jugador("ESTEBAN");
        Mazo m = new Mazo();
    	Carta cartasj1 [] = new Carta[3];
    	Carta cartasj2 [] = new Carta[3];	//arreglos que guardan las cartas de los jugadores (las que tendrian en sus manos)
        
        /* VARIABLES DE MAIN */
        int r = 0;	//reserva retorno de ronda() aunque no sirve pa' nada; lo pongo porque sino me tira error
        int i = 0;	//se utiliza para alternar el valor de la bandera y mano de los jugadores
        
        /* NOTA */
        /*Podemos hacer que el jugador 1 siempre empiece por defecto cualquier partida*/
        /*Al menos yo cuando juego al truco entre 2 o 4, arranca cualquiera; no se hace sorteo ni nada*/
        
        while(j1.getPuntos()!=30 || j2.getPuntos()!=30) {		//a partir de acá se desarrolla toda la partida
        	
        	if(i%2==0) {
                j1.setBandera(true);	j1.setMano(false);
                j2.setBandera(false);	j2.setMano(true);
        	}
        	else {
                j1.setBandera(false);	j1.setMano(true);
                j2.setBandera(true);	j2.setMano(false);
        	}
        	
        	r = ronda(j1, j2, m, cartasj1, cartasj2);		//todo lo que tenga que ver con la ronda se desarrolla en la funcion
        	m.reponer();				//se reponen las cartas
        	i++;
        }	
        
        /*
        j1.setPuntos = sistPuntuacion(j1.getPuntos,1);
        j2.setPuntos = sistPuntuacion(j2.getPuntos,10);
        */
        
        
    }
    
    
    public int[][] initPuntuacion(){
        
    	/* SE PUEDEN GUARDAR LOS PUNTOS EN UN ARREGLO, O SINO, EN VEZ DE CREAR MUCHAS VARIABLES, EL NOMBRE DE CADA VARIABLE...
    	 * ...PUEDE SER UN STRING GUARDADO EN EL ARREGLO 
    	 */
    	
    	/*CONSIDERAR CREAR UN ARREGLO PARA CADA TIPO: PARA LOS PUNTOS DE TRUCO, PARA LOS ENVIDOS Y PARA LOS NO QUERIDOS*/
    	
    	final int puntaje[] = new puntaje(4);
        puntaje[] = {2,4,5,7};
        final int e = 2;        //1
        final int r = 3;        //2
        final int ee = 4;       //3
        final int er = 5;       //4
        final int eer = 7;      //5

        final int t = 2;        //6
        final int ret = 3;      //7
        final int cuat = 4;     //8

        final int ne = 1;
        final int nee = 2;
        final int ner = 2;
        final int neer = 4;
        final int nr = 1;
        final int nf = 1;
        final int nef = 2;
        final int neef = 4;
        final int nerf = 5;
        final int neerf = 7;
        
        final int nt = 1;
        final int nret = 2;
        final int nquat = 3;
    }  
 
    public int sistPuntuacion(int p,int q){
        switch(q){
            case 1:
                return p - e;
            case 2:
                return p - ee;
            case 10:
                return 30 - p;
        }
    }
       
    public static int ronda(Jugador j1, Jugador j2, Mazo m, Carta [] cartasj1, Carta [] cartasj2) {		//	NOTA: si no la hago static, me salta un error
    	
    	/*Se mezcla el mazo*/
    	m.mezclar();
    	
    	/*Se reparten las cartas a los jugadores*/
    	/*Dependiendo de quien sea mano, se reparte primero a uno y luego a otro*/
    	if(j1.getMano()==true) {
        	for (int i = 0; i < 3; i++) {
    			cartasj1[i] = m.sacar();
    			cartasj2[i] = m.sacar();
    		}
    	}
    	else {
        	for (int i = 0; i < 3; i++) {
        		cartasj2[i] = m.sacar();
        		cartasj1[i] = m.sacar();
    		}
    	}

    	
    	/*Se confirma que el reparto estuvo bien hecho*/
    	for (Carta carta : cartasj1) {
			System.out.println(carta);
		}
    	System.out.println();
    	for (Carta carta : cartasj2) {
			System.out.println(carta);
		}
    	
    	/*Se desarrolla la acción; empieza el que tiene la bandera en TRUE */
    	
    	
    	//si quiero terminar una ronda utilizo returns en varias zonas del codigo, dependiendo lo que haya pasado
    	return 0;
    	
    }
    
    
    
    
}
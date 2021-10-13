//package truco;
public class Main{
    public static void main(String[] args){
    	/* OBJETOS DE CLASES */
    	Jugador j1 = new Jugador("TIKI");
        Jugador j2 = new Jugador("ESTEBAN");
        Mazo m = new Mazo();
        String cantos[] = new String[5];

        cantos[0] = "envido";
        cantos[1] = "envido";
        cantos[2] = "quiero";

        sistPuntuacion(cantos,j1,j2);

        System.out.println("puntos de j1\t" + j1.getPuntos());
        System.out.println("puntos de j2\t" + j2.getPuntos());
        
        /* VARIABLES DE MAIN */
        int r = 0;	//reserva retorno de ronda() aunque no sirve pa' nada; lo pongo porque sino me tira error
        int i = 0;	//se utiliza para alternar el valor de la bandera y mano de los jugadores

        r = ronda(j1,j2,m);
        
        /* NOTA */
        /*Podemos hacer que el jugador 1 siempre empiece por defecto cualquier partida*/
        /*Al menos yo cuando juego al truco entre 2 o 4, arranca cualquiera; no se hace sorteo ni nada*/
        

        /*
        while(j1.getPuntos()!=30 || j2.getPuntos()!=30) {		//a partir de ac� se desarrolla toda la partida
        	
        	if(i%2==0) {
                j1.setBandera(true);	j1.setMano(false);
                j2.setBandera(false);	j2.setMano(true);
        	}
        	else {
                j1.setBandera(false);	j1.setMano(true);
                j2.setBandera(true);	j2.setMano(false);
        	}
        	
        	r = ronda(j1,j2,m,j1.cartas,j2.cartas);		//todo lo que tenga que ver con la ronda se desarrolla en la funcion
        	m.reponer();				//se reponen las cartas
        	i++;
        }*/
        /*if(j1.getCantoPrimi())        //j1 en la funcion siempre va a ser el que canta primero
            sistPuntuacion(cantos,j1,j2);
        else
            sistPuntuacion(cantos,j2,j1);*/
    }
 
    public static void sistPuntuacion(String cantado[],Jugador j1,Jugador j2){
        int index = 0;
        if(cantado[index].equals("envido")){                    //canta j1
            if(cantado[index++].equals("quiero"))               //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            else if(cantado[index].equals("no quiero"))         //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("envido"))            //canta j2
                if(cantado[index++].equals("quiero"))           //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(4);
                    else
                        j2.addPuntos(4);
                else if(cantado[index].equals("no quiero"))     //canta j1
                    j2.addPuntos(1);
                else if(cantado[index].equals("real envido"))   //canta j1
                    if(cantado[index++].equals("quiero"))       //canta j2
                        if(j1.envido(j2))
                            j1.addPuntos(7);
                        else
                            j2.addPuntos(7);
                    else if(cantado[index].equals("no quiero")) //canta j2
                        j1.addPuntos(4);
                    else if(cantado[index].equals("falta envido"))//canta j2
                        if(cantado[index++].equals("quiero"))   //canta j1
                            if(j1.envido(j2))
                                j1.addPuntos(30);   //ver acá que pasa ocn la falta
                            else
                                j2.addPuntos(30);   //acá también
                        else if(cantado[index].equals("no quiero"))//canta j1
                            j2.addPuntos(7);
                else if(cantado[index].equals("falta envido"))//canta j2
                    if(cantado[index++].equals("quiero"))   //canta j1
                        if(j1.envido(j2))
                            j1.addPuntos(30);   //ver acá que pasa ocn la falta
                        else
                            j2.addPuntos(30);   //acá también
                    else if(cantado[index].equals("no quiero"))//canta j1
                        j2.addPuntos(7);
            else if(cantado[index].equals("real envido"))   //canta j1
                if(cantado[index++].equals("quiero"))       //canta j2
                    if(j1.envido(j2))
                        j1.addPuntos(7);
                    else
                        j2.addPuntos(7);
                else if(cantado[index].equals("no quiero")) //canta j2
                    j1.addPuntos(4);
                else if(cantado[index].equals("falta envido"))//canta j2
                    if(cantado[index++].equals("quiero"))   //canta j1
                        if(j1.envido(j2))
                            j1.addPuntos(30);   //ver acá que pasa ocn la falta
                        else
                            j2.addPuntos(30);   //acá también
                    else if(cantado[index].equals("no quiero"))//canta j1
                        j2.addPuntos(7);
            else if(cantado[index].equals("falta envido"))//canta j2
                if(cantado[index++].equals("quiero"))   //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(30);   //ver acá que pasa ocn la falta
                    else
                        j2.addPuntos(30);   //acá también
                else if(cantado[index].equals("no quiero"))//canta j1
                    j2.addPuntos(7);
        }
        else{               //se canto el truco

        }
    }
       
    public static int ronda(Jugador j1, Jugador j2, Mazo m) {		//	NOTA: si no la hago static, me salta un error
    	/*Se mezcla el mazo*/
    	m.mezclar();
    	
    	/*Se reparten las cartas a los jugadores*/
    	/*Dependiendo de quien sea mano, se reparte primero a uno y luego a otro*/
    	if(j1.getMano()){
        	for (int i = 0; i < 3; i++) {
    			j1.cartas[i] = m.sacar();
    			j2.cartas[i] = m.sacar();
    		}
    	}
    	else {
        	for (int i = 0; i < 3; i++) {
        		j2.cartas[i] = m.sacar();
        		j1.cartas[i] = m.sacar();
    		}
    	}
    	
    	/*Se confirma que el reparto estuvo bien hecho*/
    	for (Carta carta : j1.cartas) {
			System.out.println(carta);
		}
    	System.out.println();
    	for (Carta carta : j2.cartas) {
			System.out.println(carta);
		}
    	
    	/*Se desarrolla la accion; empieza el que tiene la bandera en TRUE */
    	
    	
    	//si quiero terminar una ronda utilizo returns en varias zonas del codigo, dependiendo lo que haya pasado
    	return 0;
    }
}
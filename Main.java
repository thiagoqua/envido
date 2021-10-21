//package truco;
public class Main{
    public static void main(String[] args){
    	Jugador j1 = new Jugador("TIKI");
        Jugador j2 = new Jugador("ESTEBAN");
        Mazo m = new Mazo();
        
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
        else{       //se canto el truco por j1
            if(cantado[index].equals("quiero")) //canta j2
                if(j1.truco(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            else if(cantado[index].equals("no quiero")) //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("retruco"))   //canta j2
                if(cantado[index].equals("quiero"))     //canta j1
                    if(j1.truco(j2))
                        j1.addPuntos(3);
                    else
                        j2.addPuntos(3);
                else if(cantado[index].equals("no quiero")) //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("vale cuatro"))   //canta j1
                    if(cantado[index].equals("quiero"))     //canta j2
                        if(j1.truco(j2))
                            j1.addPuntos(4);
                        else
                            j2.addPuntos(4);
                    else if(cantado[index].equals("no quiero")) //canta j2
                        j1.addPuntos(3);        
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
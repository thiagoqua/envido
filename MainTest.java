public class MainTest {

    public static void main(String[] args) {   

        Jugador j1 = new Jugador();
        IA j2 = new IA();
        Carta tiraj1 = new Carta();
        Carta tiraj2[] = new Carta[2];
        Mazo mazo = new Mazo();
        String cantado[] = new String[5];
        
        
        Interfaz pantalla = new Interfaz();
        
        // cantado[0] = CANTOS[0];     //j1
        // cantado[1] = CANTOS[0];     //j2
        // cantado[2] = CANTOS[1];     //j1
        // cantado[3] = CANTOS[6];     //j2
        // cantado[4] = CANTOS[6];     //j1
       
       
//       for(int i=0;i<3;++i){
//           j1.cartas[i] = mazo.sacar();
//           j1.copyCartas[i] = j1.cartas[i];
//           j2.cartas[i] = mazo.sacar();
//           j2.copyCartas[i] = j2.cartas[i];
//       }
//
//       System.out.println("cartas de j1\n");
//       for(Carta x : j1.cartas)
//           System.out.println(x);
//
//       System.out.println("\ncartas de IA\n");
//       for(Carta x : j2.cartas)
//           System.out.println(x);
//
//        	/*ACA ES DONDE ME QUEDE*/
//        
//       tiraj1 = j1.tirar((int)(Math.random() * 3));
//       j2.setBandera(true);
//       j2.activatePuedoCantarEnvido(true);
//       tiraj2 = j2.yourTurn(cantado,tiraj1);
//
//       System.out.println("\nj1 tiro" + tiraj1 + "\nIA tiro " + tiraj2[0] + " y " + tiraj2[1]);
//
//       System.out.println("\nSE CANTO\n");
//       
//       cantado[1] = "quiero";
//       for(int i = 0;cantado[i] != null;++i)
//           System.out.println(cantado[i]);
//        
//        sistPuntuacion(cantado,j2,j1);
//
//       System.out.println("puntos de j1:\t" + j1.getPuntos() + "\npuntos de IA:\t" + j2.getPuntos());

        /*
        // VARIABLES DE MAIN 
        int r = 0;	//reserva retorno de ronda() aunque no sirve pa' nada; lo pongo porque sino me tira error
        int i = 0;	//se utiliza para alternar el valor de la bandera y mano de los jugadores

        r = ronda(j1,j2,m);
        
        // NOTA
        //Podemos hacer que el jugador 1 siempre empiece por defecto cualquier partida
        //Al menos yo cuando juego al truco entre 2 o 4, arranca cualquiera; no se hace sorteo ni nada
        

        
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
    }

    /*
    
    public static void sistPuntuacion(String cantado[],Jugador j1,Jugador j2) throws IllegalArgumentException{ 
        //j1 siempre debe ser el que canta primero segun cantoPrimi
        int index = 0;
        if(cantado[index].equals("envido")){                        //canta j1       
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("envido")){               //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(4);
                    else
                        j2.addPuntos(4);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("real envido")){      //canta j1
    
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntos(7);
                        else
                            j2.addPuntos(7);
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(4);
                    else if(cantado[index].equals("falta envido")){ //canta j2
        
                        if(cantado[++index].equals("quiero")){      //canta j1
                            if(j1.envido(j2))
                                j1.addPuntosF(j2);   
                            else
                                j2.addPuntosF(j1);   
                        }
                        else if(cantado[index].equals("no quiero")) //canta j1
                            j2.addPuntos(7);
                        else 
                            throw new IllegalArgumentException(cantado[index] + " no matcheado");
                    }
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else if(cantado[index].equals("falta envido")){     //canta j1
    
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   
                        else
                            j2.addPuntosF(j1);   
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(4);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("real envido")){          //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(5);
                    else
                        j2.addPuntos(5);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("falta envido")){     //canta j1
    
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   
                        else
                            j2.addPuntosF(j1);   
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j2.addPuntos(5);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("falta envido")){         //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("real envido")){              //canta j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(3);
                else
                    j2.addPuntos(3);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("falta envido")){         //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(3);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("falta envido")){             //canta j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   
                    else
                        j2.addPuntosF(j1);   
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                    j1.addPuntos(1);
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("truco")){                    //se canto el truco por j1
            if(cantado[++index].equals("quiero")){                  //canta j2
                if(j1.truco(j2))        
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero"))             //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("retruco")){              //canta j2

                if(cantado[++index].equals("quiero")){              //canta j1
                    if(j1.truco(j2))
                        j1.addPuntos(3);
                    else
                        j2.addPuntos(3);
                }
                else if(cantado[index].equals("no quiero"))         //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("vale cuatro")){      //canta j1
    
                    if(cantado[++index].equals("quiero")){          //canta j2
                        if(j1.truco(j2))
                            j1.addPuntos(4);
                        else
                            j2.addPuntos(4);
                    }
                    else if(cantado[index].equals("no quiero"))     //canta j2
                        j1.addPuntos(3);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else 
            throw new IllegalArgumentException(cantado[index] + " no matcheado");
    }
    
    
*/
    
    public static int ronda(Jugador j1, Jugador j2, Mazo m) {
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
    return 0;}

    public static final String CANTOS[] = new String[8];

    public static void initCANTOS(){
        CANTOS[0] = "envido";   CANTOS[1] = "real envido";  CANTOS[2] = "falta envido";
        CANTOS[3] = "truco";    CANTOS[4] = "retruco";      CANTOS[5] = "vale cuatro";
        CANTOS[6] = "quiero";   CANTOS[7] = "no quiero";
    }
}
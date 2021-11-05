public class Main {
    public static void main(String[] args) {        
        Jugador j1 = new Jugador("TIKI");
        Jugador j2 = new Jugador("ESTEBAN");
        Mazo mazo = new Mazo();
        //Interfaz pantalla = new Interfaz();
        String cantos[] = new String[5];

        cantos[0] = "envido";           //j1
        cantos[1] = "envido";           //j2
        cantos[2] = "real envido";      //j1
        cantos[3] = "quiero";           //j2
        //cantos[4] = "quiero";           //j1

        j1.setCantoPrimi(true);

        for(int i=0;i<3;++i){
            j1.cartas[i] = mazo.sacar();
            j2.cartas[i] = mazo.sacar();
        }

        try{
            sistPuntuacion(cantos,j1,j2);
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }

        System.out.println("cartas de j1:");
        for(Carta temp : j1.cartas)
            System.out.println(temp);
            
        System.out.println("cartas de j2:");
        for(Carta temp : j2.cartas)
            System.out.println(temp);

        System.out.println("\npuntos de j1 " + j1.getPuntos() + "\npuntos de j2 " + j2.getPuntos());
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
        /*if(j1.getCantoPrimi())        //j1 en la funcion siempre va a ser el que canta primero
            sistPuntuacion(cantos,j1,j2);
        else
            sistPuntuacion(cantos,j2,j1);*/
    }

    public static void sistPuntuacion(String cantado[],Jugador j1,Jugador j2) throws IllegalArgumentException{ 
        //j1 siempre debe ser el que canta primero
        int index = 0;
        if(cantado[index].equals("envido")){                    //canta j1
            index++;
            if(cantado[index].equals("quiero")){               //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero"))         //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("envido")){            //canta j2
                index++;
                if(cantado[index].equals("quiero")){           //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(4);
                    else
                        j2.addPuntos(4);
                }
                else if(cantado[index].equals("no quiero"))     //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("real envido")){   //canta j1
                    index++;
                    if(cantado[index].equals("quiero")){       //canta j2
                        if(j1.envido(j2))
                            j1.addPuntos(7);
                        else
                            j2.addPuntos(7);
                    }
                    else if(cantado[index].equals("no quiero")) //canta j2
                        j1.addPuntos(4);
                    else if(cantado[index].equals("falta envido")){//canta j2
                        index++;
                        if(cantado[index].equals("quiero")){   //canta j1
                            if(j1.envido(j2))
                                j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                            else
                                j2.addPuntosF(j1);   //acá también
                        }
                        else if(cantado[index].equals("no quiero"))//canta j1
                            j2.addPuntos(7);
                        else 
                            throw new IllegalArgumentException(cantado[index] + " no matcheado");
                    }
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else if(cantado[index].equals("falta envido")){//canta j2
                    index++;
                    if(cantado[index].equals("quiero")){   //canta j1
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                        else
                            j2.addPuntosF(j1);   //acá también
                    }
                    else if(cantado[index].equals("no quiero"))//canta j1
                        j1.addPuntos(4);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("real envido")){   //canta j2
                index++;
                if(cantado[index].equals("quiero")){       //canta j1
                    if(j1.envido(j2))
                        j1.addPuntos(5);
                    else
                        j2.addPuntos(5);
                }
                else if(cantado[index].equals("no quiero")) //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("falta envido")){//canta j1
                    index++;
                    if(cantado[index].equals("quiero")){   //canta j2
                        if(j1.envido(j2))
                            j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                        else
                            j2.addPuntosF(j1);   //acá también
                    }
                    else if(cantado[index].equals("no quiero"))//canta j2
                        j2.addPuntos(5);
                    else 
                        throw new IllegalArgumentException(cantado[index] + " no matcheado");
                }
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else if(cantado[index].equals("falta envido")){//canta j2
                index++;
                if(cantado[index].equals("quiero")){   //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                    else
                        j2.addPuntosF(j1);   //acá también
                }
                else if(cantado[index].equals("no quiero"))//canta j1
                    j2.addPuntos(2);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("real envido")){  //canta j1
            index++;
            if(cantado[index].equals("quiero")){       //canta j2
                if(j1.envido(j2))
                    j1.addPuntos(3);
                else
                    j2.addPuntos(3);
            }
            else if(cantado[index].equals("no quiero")) //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("falta envido")){//canta j2
                index++;
                if(cantado[index].equals("quiero")){   //canta j1
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                    else
                        j2.addPuntosF(j1);   //acá también
                }
                else if(cantado[index].equals("no quiero"))//canta j1
                    j2.addPuntos(3);
                else 
                    throw new IllegalArgumentException(cantado[index] + " no matcheado");
            }
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("falta envido")){ //canta j1
            index++;
            if(cantado[index].equals("quiero")){       //canta j2
                    if(j1.envido(j2))
                        j1.addPuntosF(j2);   //ver acá que pasa ocn la falta
                    else
                        j2.addPuntosF(j1);   //acá también
            }
            else if(cantado[index].equals("no quiero"))//canta j2
                    j1.addPuntos(1);
            else 
                throw new IllegalArgumentException(cantado[index] + " no matcheado");
        }
        else if(cantado[index].equals("truco")){               //se canto el truco por j1
            index++;
            if(cantado[index].equals("quiero")){ //canta j2
                if(j1.truco(j2))
                    j1.addPuntos(2);
                else
                    j2.addPuntos(2);
            }
            else if(cantado[index].equals("no quiero")) //canta j2
                j1.addPuntos(1);
            else if(cantado[index].equals("retruco")){   //canta j2
                index++;
                if(cantado[index].equals("quiero")){     //canta j1
                    if(j1.truco(j2))
                        j1.addPuntos(3);
                    else
                        j2.addPuntos(3);
                }
                else if(cantado[index].equals("no quiero")) //canta j1
                    j2.addPuntos(2);
                else if(cantado[index].equals("vale cuatro")){   //canta j1
                    index++;
                    if(cantado[index].equals("quiero")){     //canta j2
                        if(j1.truco(j2))
                            j1.addPuntos(4);
                        else
                            j2.addPuntos(4);
                    }
                    else if(cantado[index].equals("no quiero")) //canta j2
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
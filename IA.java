public class IA extends Jugador{

    private boolean puedoCantarEnvido;                  //la habilito desde main cuando estoy en la primer mano

    public IA(String tag){
        super(tag);
    }
	
    public Carta[] yourTurn(String cantos[],Carta tirada){      //metodo que se invocaría cada vez que le toca jugar a la IA
        int mato;
        Carta tiro[] = new Carta[2];
        if(puedoCantarEnvido){    
            int puntosEnvido = super.getPuntosEnvido();
            if(super.bandera){
                if(puntosEnvido > 22 && puntosEnvido < 29){
                    cantar(0,cantos);
                    super.cantoPrimi = true;
                }
                else if(puntosEnvido >= 28 && puntosEnvido < 32){
                    cantar(1,cantos);
                    super.cantoPrimi = true;
                }
                else if(puntosEnvido >= 32){
                    cantar(2,cantos);
                    super.cantoPrimi = true;
                }
            }
        }
        if(tirada == null){                              //si todavía no se tiraron cartas, tiro una baja
            tiro[0] = tirarBajo();
            return tiro;
        }
        else{                                           
            mato = someKillsIt(tirada);
            if(mato == -1){                             //si no la mato tiro una baja
                tiro[0] = tirarBajo();
                return tiro;
            }
            else{
                tiro[0] = super.tirar(mato);            //sino la mato
                tiro[1] = tirarBajo();                  //y tiro baja
                return tiro;               
            }
        }
    }

    public boolean yourTurnAccept(int queCanto){            //metodo que se invocaria cada vez que tiene que aceptar o rechazar la IA

    return false;}

	public void cantar(int queCanto,String cantos[]){       //queCanto hace referencia al arreglo de cantos constantes
        switch(queCanto){
            case 0:
                cantos[0] = "envido";
                break;
            case 1:
                cantos[0] = "real envido";
                break;
            case 2:
                cantos[0] = "falta envido";
                break;
            case 3:
                cantos[0] = "truco";
        }
	}
	
    private int someKillsIt(Carta aMatar){                  //retorna el indice de mi carta que matan a la del j2
        //MODIFICAR PARA QUE SI LA MATAN MAS DE 1 CARTA QUE LA MATE CON LA MAS BAJA
        for(int index = 0;index < 3;++index){
            try{
                if(super.cartas[index] != null && super.cartas[index].mata(aMatar))
                    return index;
            }
            catch(PardaExeption pe){
                continue;
            }
        }
    return -1;}                                             //si no la mato retorna -1

    private Carta tirarBajo(){                              //tira la carta mas baja que tiene
        int notNull,manyNulls;
        notNull = manyNulls = 0;
        for(int i = 0;i < 3;++i){
            if(super.cartas[i] == null)
                ++manyNulls;
            else
                notNull = i;
        }
        try{
            if(manyNulls == 0){
                if(!super.cartas[0].mata(super.cartas[1]) && !super.cartas[0].mata(super.cartas[2]))
                    return super.tirar(0);
                else if(!super.cartas[1].mata(super.cartas[0]) && !super.cartas[1].mata(super.cartas[2]))
                    return super.tirar(1);
                else 
                    return super.tirar(2);
            }
            else if(manyNulls == 1){
                if(super.cartas[0] == null){
                    if(!super.cartas[1].mata(super.cartas[2]))
                        return super.tirar(1);
                    else
                        return super.tirar(2);
                }
                else if(super.cartas[1] == null){
                    if(!super.cartas[0].mata(super.cartas[2]))
                        return super.tirar(0);
                    else
                        return super.tirar(2);            
                }
                else if(super.cartas[2] == null){
                    if(!super.cartas[0].mata(super.cartas[1]))
                        return super.tirar(0);
                    else
                        return super.tirar(1);
                }
            }
            else
                return super.tirar(notNull);
        } catch(PardaExeption pe){
            for(Carta temp : super.cartas)
                if(temp.getNumero() == pe.numeroCarta)
                    return temp;
        }
    return null;}

	public void seleccionarCarta(){
	}
	
	public void irseAlMazo(){
	}
	
	public void rendirse(){
	}

    public void setPuedoCantarEnvido(boolean b){this.puedoCantarEnvido = b;}
}
import javax.lang.model.util.ElementScanner14;

public class IA extends Jugador{

    private boolean puedoCantarEnvido;                  //la habilito desde main cuando estoy en la primer mano

    public IA(String tag){
        super(tag);
    }
	
    public void activatePuedoCantarEnvido(){puedoCantarEnvido = true;}

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
            puedoCantarEnvido = false;
        }
        //FALTA VER CUANDO CANTO EL TRUCO
        if(tirada == null){                              //si todavía no se tiraron cartas, tiro una baja
            tiro[0] = tirarBajo();
            return tiro;
        }
        else{     
            mato = someKillsIt(tirada);
            if(mato == -1){                             //si no la mato ni empardo tiro una baja
                tiro[0] = tirarBajo();
                return tiro;
            }
            else if(isParda(super.cartas[mato],tirada)){//si hago parda
                tiro[0] = super.tirar(mato);            //la empardo
                tiro[1] = tirarAlto();                  //y tiro la mas alta
                return tiro;
            }
            else{
                tiro[0] = super.tirar(mato);            //la mato
                tiro[1] = tirarBajo();                  //y tiro baja
                return tiro;               
            }
        }
    }

    public void yourTurnAccept(String cantos[]){             //metodo que se invocaria cada vez que tiene que aceptar, rechazar o revirar la IA
        int end = 0,puntosEnvido,manyGoods = 0;
        puntosEnvido = getPuntosEnvido();
        while(cantos[end+1] != null){++end;}                    //accedo a lo ultimo que se canto
        for(Carta temp : super.cartas)                          //me fijo cuantas cartas buenas tengo (para el truco)
                if(temp.isGood())
                    ++manyGoods;
        if(cantos[end].equals("envido")){
            if(puntosEnvido > 22 && puntosEnvido < 27)
                cantar(6,cantos);           //quiero
            else if(puntosEnvido >= 27 && puntosEnvido <= 29)
                cantar(1,cantos);           //le canto real envido
            else if(puntosEnvido > 29)
                cantar(2,cantos);           //le canto falta envido
            else
                cantar(7,cantos);           //no quiero
        }
        else if(cantos[end].equals("real envido")){
            if(puntosEnvido >= 27 && puntosEnvido <= 29)
                cantar(6,cantos);           //quiero
            else if(puntosEnvido > 29)
                cantar(2,cantos);           //le canto falta envido
            else
                cantar(7,cantos);           //no quiero
        }
        else if(cantos[end].equals("falta envido")){
            if(puntosEnvido > 29)
                cantar(6,cantos);           //quiero
            else
                cantar(7,cantos);           //no quiero
        }
        else if(cantos[end].equals("truco")){
            if(manyGoods == 1)
                cantar(6,cantos);           //quiero
            else if(manyGoods >= 2)
                cantar(4,cantos);           //le canto retruco
            else
                cantar(7,cantos);           //no quiero
        }
        else if(cantos[end].equals("retruco")){
            if(manyGoods == 2)
                cantar(6,cantos);           //quiero
            else if(manyGoods == 3)
                cantar(5,cantos);           //le canto vale cuatro
            else
                cantar(7,cantos);           //no quiero
        }
        else if(cantos[end].equals("vale cuatro")){
            if(manyGoods == 3)
                cantar(6,cantos);           //quiero
            else
                cantar(7,cantos);           //no quiero
        }
    }

	private void cantar(int queCanto,String cantos[]){       //queCanto hace referencia al arreglo de cantos constantes
        int end = 0;
        while(cantos[end] != null){++end;}                    //accedo a lo ultimo que se canto
        switch(queCanto){
            case 0:
                cantos[end] = "envido";
                break;
            case 1:
                cantos[end] = "real envido";
                break;
            case 2:
                cantos[end] = "falta envido";
                break;
            case 3:
                cantos[end] = "truco";
                break;
            case 4:
                cantos[end] = "retruco";
                break;
            case 5:
                cantos[end] = "vale cuatro";
                break;
            case 6:
                cantos[end] = "quiero";
                break;
            case 7:
                cantos[end] = "no quiero";
                break;
        }
	}
	
    private int someKillsIt(Carta aMatar){                  //retorna el indice de mi carta que mata a la del j2
        int manyKillsIt = 0,index;
        for(Carta temp : super.cartas){                     //me fijo cuantas de mis cartas matan a la que tiro j2
            try{
                if(temp != null && temp.mata(aMatar))
                    ++manyKillsIt;
            } catch(PardaExeption pe){
                continue;
            }
        }
        if(manyKillsIt == 1){                               //si solamente una de mis cartas la mata
            for(index = 0;index < 3;++index){               //la busco
                try{
                    if(super.cartas[index] != null && super.cartas[index].mata(aMatar))
                        return index;
                } catch(PardaExeption pe){
                    continue;
                }
            }
        }
        else if(manyKillsIt == 2){                          //si dos de mis cartas la matan
            int indexes[] = new int[2],i = 0;
            for(index = 0;index < 3;++index){               //las busco
                try{
                    if(super.cartas[index] != null && super.cartas[index].mata(aMatar)){
                        indexes[i] = index;
                        ++i;    
                    }
                } catch(PardaExeption pe){
                    continue;
                }
            }
            try{
                if(!super.cartas[indexes[0]].mata(super.cartas[indexes[1]]))    //devuelvo la mas chica de las dos
                    return indexes[0];
                else
                    return indexes[1];
            } catch(PardaExeption pe){                                          //si son iguales devuelvo cualquiera
                return indexes[0];
            }
        }
        else if(manyKillsIt == 3){                          //si las 3 cartas la matan, retorno la mas baja
            try{
                if(!super.cartas[0].mata(super.cartas[1]) && !super.cartas[0].mata(super.cartas[2]))
                    return 0;
                else if(!super.cartas[1].mata(super.cartas[0]) && !super.cartas[1].mata(super.cartas[2]))
                    return 1;
                else 
                    return 2;
            } catch(PardaExeption pe){                      //si algunas de las cartas que las matan son iguales
                if(super.cartas[0].getNumero() == pe.numeroCarta){
                    try{
                        if(!super.cartas[0].mata(super.cartas[1]))
                            return 0;
                    } catch(PardaExeption pe2){
                        return 2;
                    }
                }
                else if(super.cartas[1].getNumero() == pe.numeroCarta){
                    try{
                        if(!super.cartas[1].mata(super.cartas[2]))
                            return 1;
                    } catch(PardaExeption pe2){
                        return 0;
                    }
                }
                else if(super.cartas[0].getNumero() == super.cartas[1].getNumero() && super.cartas[0].getNumero() == super.cartas[2].getNumero()){
                    //si son las 3 iguales
                    return 0;                               //devuelvo cualquiera
                }
            }
        }
    return someEmpardaIt(aMatar);}                          //si no la mato retorna -1

    private int someEmpardaIt(Carta aEmpardar){             //retorna el indice de la carta que emparda a la de j2
        for(int index = 0;index < 3;++index){
            try{
                super.cartas[index].mata(aEmpardar);        //invoco a la funcion solamente esperando a la exepcion
            } catch(PardaExeption pe){
                return index;                               //si la emparda, retorna el indice
            }
        }
    return -1;}                                             //si no la mata ni emparda, devuelve -1

    private boolean isParda(Carta mine,Carta enemy){       //devuelve true si se hizo parda
        try{
            mine.mata(enemy);
        } catch(PardaExeption pe){
            return true;                                    
        }
    return false;}

    private Carta tirarBajo(){                              //tira la carta mas baja que tiene
        int notNull,manyNulls;
        notNull = manyNulls = 0;
        for(int i = 0;i < 3;++i){                           //me fijo cuantas cartas tengo actualmente no tiradas
            if(super.cartas[i] == null)
                ++manyNulls;
            else
                notNull = i;                                //me guardo la que no es nula para tirarla si es la unica que me queda
        }
        try{
            if(manyNulls == 0){                             //si todavia no tire ninguna carta
                if(!super.cartas[0].mata(super.cartas[1]) && !super.cartas[0].mata(super.cartas[2]))
                    return super.tirar(0);
                else if(!super.cartas[1].mata(super.cartas[0]) && !super.cartas[1].mata(super.cartas[2]))
                    return super.tirar(1);
                else 
                    return super.tirar(2);
            }
            else if(manyNulls == 1){                        //si ya tire 1 carta y me quedan 2
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
            else                                            //si ya tire 2 cartas y me queda 1
                return super.tirar(notNull);
        } catch(PardaExeption pe){
            for(Carta temp : super.cartas)
                if(temp != null && temp.getNumero() == pe.numeroCarta)
                    return temp;
        }
    return null;}

    private Carta tirarAlto(){
        int notNull,manyNulls;
        notNull = manyNulls = 0;
        for(int i = 0;i < 3;++i){                           //me fijo cuantas cartas tengo actualmente no tiradas
            if(super.cartas[i] == null)
                ++manyNulls;
            else
                notNull = i;                                //me guardo la que no es nula para tirarla si es la unica que me queda
        }
        try{
            if(manyNulls == 0){                             //si no tire ninguna carta
                if(super.cartas[0].mata(super.cartas[1]) && super.cartas[0].mata(super.cartas[2]))
                    return super.tirar(0);
                else if(super.cartas[1].mata(super.cartas[0]) && super.cartas[1].mata(super.cartas[2]))
                    return super.tirar(1);
                else 
                    return super.tirar(2);
            }
            else if(manyNulls == 1){                        //si tire 1 carta y me quedan 2
                if(super.cartas[0] == null){
                    if(super.cartas[1].mata(super.cartas[2]))
                        return super.tirar(1);
                    else
                        return super.tirar(2);
                }
                else if(super.cartas[1] == null){
                    if(super.cartas[0].mata(super.cartas[2]))
                        return super.tirar(0);
                    else
                        return super.tirar(2);            
                }
                else if(super.cartas[2] == null){
                    if(super.cartas[0].mata(super.cartas[1]))
                        return super.tirar(0);
                    else
                        return super.tirar(1);
                }
            }
            else                                            //si tire 2 cartas y me queda 1
                return super.tirar(notNull);
        } catch(PardaExeption pe){
            for(Carta temp : super.cartas)
                if(temp != null && temp.getNumero() == pe.numeroCarta)
                    return temp;
        }
    return null;}
	
	private void irseAlMazo(){
	}
	
	private void rendirse(){
	}
}
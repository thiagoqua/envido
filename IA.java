import java.util.Arrays;

public class IA extends Jugador{

    private short nroMano;                          //si se está jugando primera, segunda o tercera
    private boolean puedoCantarEnvido;
    private boolean mentimos;

    public IA(){
        nroMano = 1;
        mentimos = false;
        //super(tag);
    }
	
    public void setPuedoCantarEnvido(boolean cond){puedoCantarEnvido = cond;}

    public void reset(){
        nroMano = 1;
        puedoCantarEnvido = mentimos = false;
        super.reset();
    }

    public Carta[] yourTurn(String cantos[],Carta tirada){      //turno de la IA de jugar
        super.bandera = true;
        yourTurnEnvido(cantos);
        super.bandera = false;
    return yourTurnTruco(cantos,tirada);}

    public void yourTurnEnvido(String cantos[]){
        if(puedoCantarEnvido){
            nroMano = 1;                                        //si puedo cantar envido es porque estoy en la primer mano
            int puntosEnvido = super.getPuntosEnvido();
            checkIfMentimos();
            if(puntosEnvido <= 24 && mentimos){                 //si no tengo puntos y puedo mentir
                super.cantoPrimi = true;
                cantar(0,cantos);                   //canto envido
            }
            else{
                if(puntosEnvido > 24 && puntosEnvido < 29){
                    super.cantoPrimi = true;
                    cantar(0,cantos);               //canto envido
                }
                else if(puntosEnvido >= 28 && puntosEnvido < 32){
                    super.cantoPrimi = true;
                    cantar(1,cantos);               //canto real envido
                }
                else if(puntosEnvido >= 32){
                    super.cantoPrimi = true;
                    cantar(2,cantos);               //canto falta envido
                }
                puedoCantarEnvido = false;
            }
        }
    }

    public Carta[] yourTurnTruco(String cantos[],Carta tirada){
        int mato,cartasGood,manyNulls;
        manyNulls = cartasNull();
        Carta tiro[] = new Carta[2];
        if(tirada == null){                         //si todavía no se tiraron cartas
            if(nroMano == 1){
                if(Math.random() > 0.5)             //va a ir alternando entre tirar alto y bajo en la primer mano
                    tiro[0] = tirarBajo();
                else
                    tiro[0] = tirarAlto();
                ++nroMano;
                return tiro;
            }
            else{
                checkIfMentimos();    
                if((cartasGood = currentGoods()) > 0){
                    if(!super.isCantado("truco",cantos)){
                        super.cantoPrimi = true;
                        cantar(3,cantos);           //canto truco
                    }
                } else if(mentimos) {               //si no tengo cartas buenas pero puedo mentir
                    super.cantoPrimi = true;
                    cantar(3,cantos);               //canto truco
                }
                tiro[0] = tirarAlto();                      
                ++nroMano;
                return tiro;
            }
        }
        else{                                       //si el jugador ya tiro una carta
            mato = someKillsIt(tirada);
            checkIfMentimos();
            if(mato == -1){                                     //si no la mato ni empardo tiro una baja
                tiro[0] = tirarBajo();
                ++nroMano;
                return tiro;
            }
            else if(isParda(super.cartas[mato],tirada)){        //si la empardo
                tiro[0] = super.tirar(mato);
                ++nroMano;
                return tiro;
            }
            else{                                               //si la mato
                tiro[0] = super.tirar(mato);
                manyNulls = cartasNull();           //me vuelvo a fijar cuantas cartas me quedan una vez ya tirada una carta
                cartasGood = currentGoods();        //me fijo cuantas cartas buenas tengo despues de matar
                checkIfMentimos();
                if(cartasGood > 0){                             //si tengo cartas buenas
                    if(!super.isCantado("truco",cantos)){
                        super.cantoPrimi = true;
                        cantar(3,cantos);                       //canto truco
                    }
                    if(manyNulls != 3){                         //si todavía tengo cartas una vez que tire
                        tiro[1] = tirarAlto();
                        ++nroMano;
                    }
                    /*si no tengo mas cartas es porque maté en 3ra, y por ende retorno 1 sola*/
                    return tiro;
                }
                else if(mentimos){                              //si no tengo cartas buenas pero puedo mentir
                    if(!super.isCantado("truco",cantos)){
                        super.cantoPrimi = true;
                        cantar(3,cantos);
                    }
                    if(manyNulls != 3){
                        tiro[1] = tirarBajo();
                        ++nroMano;
                    }
                    return tiro;
                }
                else{                                           //si no tengo cartas buenas y no puedo mentir
                    if(manyNulls != 3){
                        tiro[1] = tirarBajo();
                        ++nroMano;
                    }
                    return tiro;
                }
            }
        }
    }

    public void yourTurnAccept(String cantos[],Jugador j){                //turno de la IA de aceptar, rechazar o revirar la IA
    	int end = 0,puntosEnvido,cartasGood,manyNulls,tiradasGood;
        manyNulls = cartasNull();
        puntosEnvido = getPuntosEnvido();
        cartasGood = currentGoods();                            //por si el jugador me canta truco una vez que yo haya jugado todas mis cartas
        tiradasGood = tiradasGood();
        checkIfMentimos();
        while(cantos[end+1] != null && cantos[end+1] != ""){++end;}
        if(j.getPuntos() >= 28){                                //a todo lo que cante le digo que si
        	 cantar(6,cantos);  
        }
        else if(end > 0 && cantos[end-1].equals("envido") && cantos[end].equals("envido")){
            if(puntosEnvido > 26 && puntosEnvido <= 28){
                cantar(6,cantos);                   //quiero
            }
            else if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(1,cantos);                   //real envido
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);                   //falta envido
            }
            else if(puntosEnvido < 25 && mentimos){
                cantar(1,cantos);                   //real envido
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("envido")){
            if(puntosEnvido > 24 && puntosEnvido <= 26){
                cantar(6,cantos);                   //quiero
            }
            else if(puntosEnvido > 26 && puntosEnvido <= 28){
                cantar(6,cantos);                   //quiero
            }
            else if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(1,cantos);                   //real envido
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);                   //falta envido
            }
            else if(puntosEnvido <= 24 && mentimos){
                cantar(1,cantos);                   //real envido
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("real envido")){
            if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(6,cantos);                   //quiero
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);                   //falta envido
            }
            else if(puntosEnvido <= 27 && mentimos){
                cantar(2,cantos);                   //falta envido
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("falta envido")){
            if(puntosEnvido >= 30){
                cantar(6,cantos);                   //quiero
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("truco")){
            if(nroMano == 1 && puedoCantarEnvido){  //puedo cantar el envido antes ya que el jugador me canto truco apenas empieza
                if(puntosEnvido >= 25){
                    cantos[end] = null;             //para que funcione sist puntuacion
                    cantar(0,cantos);               //canto envido
                }
            }
            else if((cartasGood >= 1 && tiradasGood >= 1) || (manyNulls == 3 && tiradasGood >= 2)){
            //(si me queda más de una carta buena y tuve buenas cartas)
                cantar(4,cantos);                   //retruco
            }
            else if(cartasGood == 1 || tiradasGood >= 1){
                cantar(6,cantos);                   //quiero
            }
            else if(tiradasGood == 0 && cartasGood == 0 && mentimos){
                cantar(4,cantos);                   //retruco
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("retruco")){
            if(cartasGood >= 2 || tiradasGood >= 2){
                cantar(6,cantos);                   //quiero
            }
            else if((cartasGood >= 2 || tiradasGood >= 1) || (manyNulls == 3 && tiradasGood >= 2)){
                cantar(5,cantos);                   //vale cuatro
            }
            else if(cartasGood < 2 && mentimos){
                cantar(5,cantos);                   //vale cuatro
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
        else if(cantos[end].equals("vale cuatro")){
            if((cartasGood >= 2 || tiradasGood >= 1) || (manyNulls == 3 && tiradasGood >= 2)){
                cantar(6,cantos);                   //quiero
            }
            else{
                cantar(7,cantos);                   //no quiero
            }
        }
    }

	private void cantar(int queCanto,String cantos[]){          //queCanto hace referencia al indice del arreglo de cantos constantes
        int end = 0;
        while(cantos[end] != null && cantos[end] != ""){++end;}
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
	
    private int someKillsIt(Carta aMatar){                      //retorna el indice de mis cartas que mata a la del j2
        int manyKillsIt = 0,index;
        for(Carta temp : super.cartas){
            try{
                if(temp != null && temp.mata(aMatar))
                    ++manyKillsIt;
            } catch(PardaExeption pe){
                continue;
            }
        }
        if(manyKillsIt == 1){
            for(index = 0;index < 3;++index){                   //busco a la unica carta que la mata
                try{
                    if(super.cartas[index] != null && super.cartas[index].mata(aMatar))
                        return index;
                } catch(PardaExeption pe){
                    continue;
                }
            }
        }
        else if(manyKillsIt == 2){
            int indexes[] = new int[2],i = 0;
            for(index = 0;index < 3;++index){
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
        else if(manyKillsIt == 3){
            try{
                if(!super.cartas[0].mata(super.cartas[1]) && !super.cartas[0].mata(super.cartas[2]))
                    return 0;
                else if(!super.cartas[1].mata(super.cartas[0]) && !super.cartas[1].mata(super.cartas[2]))
                    return 1;
                else 
                    return 2;
            } catch(PardaExeption pe){                          //si algunas de las cartas que las matan son iguales
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
                    return 0;                                   //devuelvo cualquiera
                }
            }
        }
    return someEmpardaIt(aMatar);}                              //si llegue hasta aca es porque ninguna la mata

    private int someEmpardaIt(Carta aEmpardar){
        for(int index = 0;index < 3;++index){
            try{
                if(super.cartas[index] != null) {
                	super.cartas[index].mata(aEmpardar);
                }
            } catch(PardaExeption pe){
                return index;
            }
        }
    return -1;}                                                 //si no la mata ni emparda, devuelve -1

    private boolean isParda(Carta mine,Carta enemy){
        try{
            mine.mata(enemy);
        } catch(PardaExeption pe){
            return true;                                    
        }
    return false;}

    private Carta tirarBajo(){
        int notNull,manyNulls;
        notNull = 0;
        manyNulls = cartasNull();
        for(int i = 0;i < 3;++i){
            if(super.cartas[i] != null)
                notNull = i;                                    //me guardo la que no es nula para tirarla si es la unica que me queda
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
        } catch(PardaExeption pe){                  //si hay cartas iguales, tiro una de esas
            for(int i=0;i<3;++i)
                if(super.cartas[i] != null && super.cartas[i].getNumero() == pe.numeroCarta)
                    return super.tirar(i);
        }
    return null;}

    private Carta tirarAlto(){
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
                if(super.cartas[0].mata(super.cartas[1]) && super.cartas[0].mata(super.cartas[2]))
                    return super.tirar(0);
                else if(super.cartas[1].mata(super.cartas[0]) && super.cartas[1].mata(super.cartas[2]))
                    return super.tirar(1);
                else 
                    return super.tirar(2);
            }
            else if(manyNulls == 1){
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
            else
                return super.tirar(notNull);
        } catch(PardaExeption pe){
            for(int i=0;i<3;++i)
                if(super.cartas[i] != null && super.cartas[i].getNumero() == pe.numeroCarta)
                    return super.tirar(i);
        }
    return null;}

    private int currentGoods(){
        int many = 0;
        for(Carta temp : super.cartas)
                if(temp != null && temp.isGood())
                    ++many;
    return many;}

    private void checkIfMentimos(){
        /*si se quiere que la IA mienta menos o más hay que modificar el valor con el que se compara aVer*/
        int aVer = (int)(Math.random() * 20);
        if(aVer <= 10)
            mentimos = true;
        else
            mentimos = false;
    }

    private int tiradasGood(){                       //me dice cuantas cartas buenas tengo/tuve en total
        int many = 0;
        for (int i = 0;i < 3;++i){
            if(super.cartas[i] == null)
                if(super.copyCartas[i].isGood())
                    ++many;
        }
    return many;}

    private int cartasNull(){
        int many = 0;
        for(Carta temp : super.cartas)
            if(temp == null)
                ++many;
    return many;}
}

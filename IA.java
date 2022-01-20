import java.util.Arrays;

public class IA extends Jugador{

    private short nroMano;                              //si se está jugando primera, segunda o tercera
    private boolean puedoCantarEnvido;                  //la habilito desde main cuando estoy en la primer mano
    private boolean mentimos;

    public IA(){
        nroMano = 1;
        mentimos = false;
        //super(tag);
    }
	
    public void activatePuedoCantarEnvido(boolean cond){puedoCantarEnvido = cond;}

    public void reset(){                                        //lo invoco al finalizar cada ronda
        nroMano = 1;
        puedoCantarEnvido = mentimos = false;
        super.reset();
    }

    public Carta[] yourTurn(String cantos[],Carta tirada){      //metodo que se invocaría cada vez que le toca jugar a la IA
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
                System.out.println("SE MIENTE PERRO");
                super.cantoPrimi = true;
                cantar(0,cantos);           //canto envido
            }
            else{
                if(puntosEnvido > 24 && puntosEnvido < 29){
                    super.cantoPrimi = true;
                    cantar(0,cantos);      //canto envido
                }
                else if(puntosEnvido >= 28 && puntosEnvido < 32){
                    super.cantoPrimi = true;
                    cantar(1,cantos);       //canto real envido
                }
                else if(puntosEnvido >= 32){
                    super.cantoPrimi = true;
                    cantar(2,cantos);       //canto falta envido
                }
                puedoCantarEnvido = false;
            }
        }
    }

    public Carta[] yourTurnTruco(String cantos[],Carta tirada){
        int mato,cartasGood,manyNulls;
        manyNulls = cartasNull();
        Carta tiro[] = new Carta[2];
        if(tirada == null){                             //si todavía no se tiraron cartas
            if(nroMano == 1){                           //si estoy en la primer mano
                if(Math.random() > 0.5)                 //va a ir alternando entre tirar alto y bajo en la primer mano cuando tiene que jugar
                    tiro[0] = tirarBajo();
                else
                    tiro[0] = tirarAlto();
                ++nroMano;
                return tiro;
            }
            else{
                checkIfMentimos();    
                if((cartasGood = currentGoods()) > 0){     //si tengo cartas buenas
                    if(!super.isCantado("truco",cantos)){     //si no esta cantado el truco
                        super.cantoPrimi = true;
                        cantar(3,cantos);                   //canto truco
                    }
                } else if(mentimos) {                    //si no tengo cartas buenas pero puedo mentir
                    super.cantoPrimi = true;
                    cantar(3,cantos);                   //canto truco
                }
                tiro[0] = tirarAlto();                      
                ++nroMano;
                return tiro;
            }
        }
        else{                                           //si el jugador ya tiro una carta
            mato = someKillsIt(tirada);
            checkIfMentimos();
            if(mato == -1){                                 //si no la mato ni empardo tiro una baja
                tiro[0] = tirarBajo();
                ++nroMano;
                return tiro;
            }
            else if(isParda(super.cartas[mato],tirada)){    //si la empardo
                tiro[0] = super.tirar(mato);                //la empardo
                ++nroMano;
                return tiro;
            }
            else{                                           //si la mato
                tiro[0] = super.tirar(mato);                //la mato
                manyNulls = cartasNull();                   //me vuelvo a fijar las nulas una vez ya tirada una carta
                cartasGood = currentGoods();                //me fijo cuantas cartas buenas tengo despues de matar
                checkIfMentimos();
                if(cartasGood > 0){                         //si tengo cartas buenas
                    if(!super.isCantado("truco",cantos)){   //si no esta cantado el truco
                        super.cantoPrimi = true;
                        cantar(3,cantos);                   //canto truco
                    }
                    if(manyNulls != 3){                     //si todavía tengo cartas una vez que tire
                        tiro[1] = tirarAlto();              //reviento
                        ++nroMano;
                    }
                    //si no tengo mas cartas es porque maté en 3ra, y por ende retorno 1 sola
                    return tiro;
                }
                else if(mentimos){                          //si no tengo cartas buenas pero puedo mentir
                    if(!super.isCantado("truco",cantos)){   //si no esta cantado el truco
                        super.cantoPrimi = true;
                        cantar(3,cantos);                   //canto truco
                    }
                    if(manyNulls != 3){                     //si todavía tengo cartas una vez que tire
                        tiro[1] = tirarBajo();              //tiro baja
                        ++nroMano;
                    }
                    //si no tengo mas cartas es porque maté en 3ra, y por ende retorno 1 sola
                    return tiro;
                }
                else{                                       //si no tengo cartas buenas y no puedo mentir
                    if(manyNulls != 3){                     //si todavía tengo cartas una vez que tire
                        tiro[1] = tirarBajo();              //tiro baja
                        ++nroMano;
                    }
                    //si no tengo mas cartas es porque maté en 3ra, y por ende retorno 1 sola
                    return tiro;
                }
            }
        }
    }

    public void yourTurnAccept(String cantos[]){             //metodo que se invocaria cada vez que tiene que aceptar, rechazar o revirar la IA
        
    	int end = 0,puntosEnvido,cartasGood,manyNulls,totalCartasGood;
        manyNulls = cartasNull();
        puntosEnvido = getPuntosEnvido();
        cartasGood = currentGoods();                             //por si el jugador me canta truco una vez que yo haya jugado todas mis cartas
        totalCartasGood = totalGoods();
        checkIfMentimos();
        while(cantos[end+1] != null && cantos[end+1] != ""){++end;}                    //accedo a lo ultimo que se canto
        if(end > 0 && cantos[end-1].equals("envido") && cantos[end].equals("envido")){//si se canta envido envido
            if(puntosEnvido > 26 && puntosEnvido <= 28){
                cantar(6,cantos);           //quiero
            }
            else if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(1,cantos);           //real envido
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);           //falta envido
            }
            else if(puntosEnvido < 25 && mentimos){
                System.out.println("SE MIENTE PERRO.");
                cantar(1,cantos);           //real envido
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("envido")){
            if(puntosEnvido > 24 && puntosEnvido <= 26){
                cantar(6,cantos);           //quiero
            }
            else if(puntosEnvido > 26 && puntosEnvido <= 28){
                cantar(6,cantos);           //quiero
            }
            else if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(1,cantos);           //real envido
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);           //falta envido
            }
            else if(puntosEnvido <= 24 && mentimos){            //si no tengo puntos y puedo mentir
                System.out.println("SE MIENTE PERRO.");
                cantar(1,cantos);           //real envido                  //canto el real envido
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("real envido")){
            if(puntosEnvido > 28 && puntosEnvido <= 31){
                cantar(6,cantos);           //quiero
            }
            else if(puntosEnvido > 31){
                cantar(2,cantos);           //falta envido
            }
            else if(puntosEnvido <= 27 && mentimos){            //si no tengo puntos y puedo mentir
                System.out.println("SE MIENTE PERRO.");
                cantar(2,cantos);           //falta envido                 //canto la falta envido
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("falta envido")){
            if(puntosEnvido > 31){
                cantar(6,cantos);           //quiero
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("truco")){
            if(nroMano == 1 && puedoCantarEnvido){  //puedo cantar el envido antes ya que el jugador me canto truco apenas empieza
                if(puntosEnvido >= 25){
                    cantos[end] = null;         //vaceo la posicion para que funcione sist puntuacion
                    cantar(0,cantos);           //canto envido
                }
            }
            else if(cartasGood == 1 || totalCartasGood > 1){
                cantar(6,cantos);           //quiero
            }
            else if(cartasGood >= 2){
                cantar(4,cantos);           //retruco
            }
            else if(manyNulls == 3 && totalCartasGood > 1){  //si ya tire todas, mis cartas fueron buenas y me cantaron truco
                cantar(3,cantos);
            }
            else if(totalCartasGood < 1 && mentimos){                //si no tengo cartas buenas y puedo mentir
                System.out.println("SE MIENTE PERRO");
                cantar(4,cantos);           //retruco                  //canto el retruco
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("retruco")){
            if(cartasGood == 2 || totalCartasGood >= 2){
                cantar(6,cantos);           //quiero
            }
            else if(cartasGood == 3 || totalCartasGood >= 2){
                cantar(5,cantos);           //vale cuatro
            }
            else if(cartasGood < 2 && mentimos){                //si no tengo cartas buenas y puedo mentir
                System.out.println("SE MIENTE PERRO");
                cantar(5,cantos);           //vale cuatro                  //canto el vale cuatro
            }
            else if(manyNulls == 3 && totalCartasGood > 1){  //si ya tire todas, mis cartas fueron buenas y me cantaron truco
                cantar(3,cantos);
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
        else if(cantos[end].equals("vale cuatro")){
            if(cartasGood == 3 || totalCartasGood >= 2){
                cantar(6,cantos);           //quiero
            }
            else{
                cantar(7,cantos);           //no quiero
            }
        }
    }

	private void cantar(int queCanto,String cantos[]){       //queCanto hace referencia al arreglo de cantos constantes
        int end = 0;
        while(cantos[end] != null && cantos[end] != ""){++end;}                    //accedo a lo ultimo que se canto
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
                if(super.cartas[index] != null) {
                	super.cartas[index].mata(aEmpardar);        //invoco a la funcion solamente esperando a la exepcion
                }
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
        notNull = 0;
        manyNulls = cartasNull();
        for(int i = 0;i < 3;++i){                           //me fijo cuantas cartas tengo actualmente no tiradas
            if(super.cartas[i] != null)
                notNull = i;                            //me guardo la que no es nula para tirarla si es la unica que me queda
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
            for(int i=0;i<3;++i)
                if(super.cartas[i] != null && super.cartas[i].getNumero() == pe.numeroCarta)
                    return super.tirar(i);
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
            for(int i=0;i<3;++i)
                if(super.cartas[i] != null && super.cartas[i].getNumero() == pe.numeroCarta)
                    return super.tirar(i);
        }
    return null;}

    private int currentGoods(){                         //me dice cuantoas cartas buenas tengo que todavía no tiré
        int many = 0;
        for(Carta temp : super.cartas)                          //me fijo cuantas cartas buenas tengo (para el truco)
                if(temp != null && temp.isGood())
                    ++many;
    return many;}

    private void checkIfMentimos(){
        int aVer = (int)(Math.random() * 20);
        //si se quiere que la IA mienta menos o más hay que modificar el valor con el que se compara aVer
        if(aVer <= 15)
            mentimos = true;
        else
            mentimos = false;
    }

    private int totalGoods(){   //me dice cuantas cartas buenas tengo/tuve en total
        int many = 0;
        for(Carta temp : super.copyCartas)
            if(temp.isGood())
                ++many;
    return many;}

    private int cartasNull(){
        int many = 0;
        for(Carta temp : super.cartas)
            if(temp == null)
                ++many;
    return many;}
}

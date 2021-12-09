public class Mazo{
    
    private Carta cartas[];
    
    public Mazo(){
        int indice = 0;
        final String palos[] = new String[4];
        cartas = new Carta[40];
        palos[0] = "basto"; palos[1] = "copa"; palos[2] = "espada"; palos[3] = "oro";
        for(String palo : palos){                               //genero las 48 cartas con sus números y palos
            for(int i=1;i<=10;++i){
                if(i>7){
                    cartas[indice] = new Carta(i+2,palo);
                    ++indice;
                }
                else{
                    cartas[indice] = new Carta(i,palo);
                    ++indice;
                }
            }
        }
        mezclar(); mezclar();                                   //mando a mezclar
    }

    public void mezclar(){
        Carta low[] = new Carta[20];                            //almacena las 20 primeras cartas del mazo
        Carta high[] = new Carta[20];                           //almacena las 20 segundas cartas del mazo
        int i,j,times;
        double rand = Math.floor(Math.random() * 349 + 500);    //genero cantidad aleatoria de veces que se mezcla
        for(times=0;times<rand;++times){                        
            for(i=0;i<40;++i){                                  //lleno los arreglos de las mitades del mazo
                if(i<20)
                    low[i] = cartas[i];
                else
                    high[i-20] = cartas[i];
            }
            if(times%2 == 0){                                   //se hace solamente para mejorar el mezclado
                Carta swap[] = new Carta[20];
                swap = low;                                     //intercambio el contenido de los mazos
                low = high;
                high = swap;
            }
            for(i=j=0;j<20;++j,i+=2){                           //voy mezclando
                cartas[i] = low[j];
                cartas[i+1] = high[j];
            }
        }
    }

    public Carta sacar(){
        int i;
        Carta temp = new Carta();
        for(i=0;i<40 && cartas[i] == null;++i){}                //seteo al índice en la primer carta del mazo
        temp = Carta.builder(cartas[i]);                        //le asigno el valor a la temporal
        cartas[i] = null;                                       //le asigno 0 al número de la carta que saqué para informar que justamente la carta fue sacada
    return temp;}                                               

    public void reponer(){
        Mazo aux = new Mazo();                                  
        cartas = aux.cartas;                                    
    }
}
public class Mazo{
    
    private Carta cartas[];
    
    public Mazo(){
        int indice = 0;
        final String palos[] = new String[4];
        cartas = new Carta[40];
        palos[0] = "basto"; palos[1] = "copa"; palos[2] = "espada"; palos[3] = "oro";
        for(String palo : palos){
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
        mezclar(); mezclar();
    }

    public void mezclar(){
        int i,j,times,rand;
        Carta low[] = new Carta[20];                //las 20 primeras cartas del mazo
        Carta high[] = new Carta[20];               //las 20 segundas cartas del mazo
        rand = (int)(Math.random() * 349 + 500);    //cantidad aleatoria de veces que se mezcla
        for(times=0;times<rand;++times){                        
            for(i=0;i<40;++i){
                if(i<20)
                    low[i] = cartas[i];
                else
                    high[i-20] = cartas[i];
            }
            if(times%2 == 0){                       //se hace solamente para mejorar el mezclado
                Carta swap[] = new Carta[20];
                swap = low;
                low = high;
                high = swap;
            }
            for(i=j=0;j<20;++j,i+=2){               //simulo una baraja por hojeo
                cartas[i] = low[j];
                cartas[i+1] = high[j];
            }
        }
    }

    public Carta sacar(){
        int i;
        Carta temp = new Carta();
        for(i=0;i<40 && cartas[i] == null;++i){}                //seteo al índice en la primer carta del mazo
        temp = Carta.builder(cartas[i]);
        cartas[i] = null;
    return temp;}                                               

    public void reponer(){
        Mazo aux = new Mazo();                                  
        cartas = aux.cartas;                                    
    }

    public Carta search(int hcode){                 //se le pasa un hashcode y devuelve la carta correspondiente
        Mazo auxiliar = new Mazo();
        for(Carta tmp : auxiliar.cartas){
            if(tmp != null){
                if(tmp.hashCode() == hcode)
                    return tmp;
            }
        }
    return null;}
}
public class PardaExeption extends Exception{
    
    public int numeroCarta;                     //contiene el numero de la carta que ocasiono la parda

    public PardaExeption(){}
    public PardaExeption(int nro){this.numeroCarta = nro;}
}
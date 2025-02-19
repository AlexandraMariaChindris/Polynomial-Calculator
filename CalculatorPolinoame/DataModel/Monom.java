package DataModel;

public class Monom {
    private int degree;
    private Number coef;

    public Monom(int d, Number c){
        degree = d;
        coef = c;
    }

    public Number getCoef(){
        return coef;
    }

    public int getDegree() {
        return degree;
    }
}

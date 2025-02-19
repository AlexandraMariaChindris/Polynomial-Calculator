package DataModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Polinom {
    private Map<Integer, Monom> monomi; //<grad_monom, monom>

    public Polinom(){
        monomi = new HashMap<Integer, Monom>();
    }

    public void addMonom(Monom monom){
        monomi.put(monom.getDegree(), monom);
    }

    public Map<Integer, Monom> getMonomi() {
        return monomi;
    }

    public String toString() {
        String rez = "";
        for (Map.Entry<Integer, Monom> entry : monomi.entrySet()) {
            int g = entry.getKey();
            Number n = entry.getValue().getCoef();
            if (g == 0 && n.doubleValue() != 0) {
                rez = n + rez;
                if (n.doubleValue() > 0)
                    rez = " + " + rez;
                else
                    rez = " " + rez;
            } else {
                if (n.doubleValue() != 0) {
                    if (g != 1)
                        rez = "^" + g + rez;
                    rez = "X" + rez;
                    if (n.doubleValue() != 1 && n.doubleValue() != -1) {
                        rez =  n + " * " + rez;
                        if (n.doubleValue() > 0)
                            rez = " + " + rez;
                        else
                            rez = " " + rez;
                    } else if (n.doubleValue() > 0)
                        rez = " + " + rez;
                    else
                        rez = " - " + rez;
                }
            }
        }
        if(rez.startsWith(" +"))
            rez = rez.substring(3);
        if (rez == "")
            rez += 0;
        return rez;
    }

}

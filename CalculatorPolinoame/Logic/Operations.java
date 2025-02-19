package Logic;

import DataModel.Monom;
import DataModel.Polinom;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public static Polinom addPolinom(Polinom p, Polinom q){
        Polinom r = new Polinom();
        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet()){
            if(q.getMonomi().containsKey(mp.getKey()))
            {
                int c1 = mp.getValue().getCoef().intValue();
                int c2 = q.getMonomi().get(mp.getKey()).getCoef().intValue();
                r.addMonom(new Monom(mp.getKey(), c1 + c2));
            }
            else
                r.addMonom(new Monom(mp.getKey(), mp.getValue().getCoef()));
        }
        for(Map.Entry<Integer, Monom> mq : q.getMonomi().entrySet())
            if(!p.getMonomi().containsKey(mq.getKey()))
                r.addMonom(new Monom(mq.getKey(), mq.getValue().getCoef()));
        return r;
    }
    public static Polinom subPolinom(Polinom p, Polinom q){
        Polinom r = new Polinom();
        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet()){
            if(q.getMonomi().containsKey(mp.getKey()))
            {
                int c1 = mp.getValue().getCoef().intValue();
                int c2 = q.getMonomi().get(mp.getKey()).getCoef().intValue();
                r.addMonom(new Monom(mp.getKey(), c1 - c2));
            }
            else
                r.addMonom(new Monom(mp.getKey(), mp.getValue().getCoef()));
        }
        for(Map.Entry<Integer, Monom> mq : q.getMonomi().entrySet())
            if(!p.getMonomi().containsKey(mq.getKey()))
                r.addMonom(new Monom(mq.getKey(), -mq.getValue().getCoef().intValue()));
        return r;
    }
    public static Polinom mulPolinom(Polinom p, Polinom q){
        Polinom r = new Polinom();
        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet()){
            for(Map.Entry<Integer, Monom> mq : q.getMonomi().entrySet()){
                int dp = mp.getKey();
                int dq = mq.getKey();
                int cp = mp.getValue().getCoef().intValue();
                int cq = mq.getValue().getCoef().intValue();
                int d = dp + dq;
                int c = cp * cq;
                if(r.getMonomi().containsKey(d))
                {
                    int coef = r.getMonomi().get(d).getCoef().intValue();
                    r.getMonomi().put(d, new Monom(d, c + coef));
                }
                else
                    r.addMonom(new Monom(dp + dq, cp * cq));
            }
        }
        return r;
    }
    //o poti face private
    public static Polinom mulMonomPolinom(Monom t, Polinom p){
        Polinom r = new Polinom();

        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet())
            r.addMonom(new Monom(t.getDegree() + mp.getKey(), t.getCoef().doubleValue() * mp.getValue().getCoef().doubleValue()));

        return r;
    }
    //o poti face private
    public static Polinom divPolinomConst(Polinom n, Polinom d){
        Polinom r = new Polinom();
        int c = Integer.parseInt(d.toString());

        for(Map.Entry<Integer, Monom> mp : n.getMonomi().entrySet())
        {
            r.addMonom(new Monom(mp.getKey(), mp.getValue().getCoef().doubleValue() / c));
        }

        return r;
    }
    public static Polinom[] divPolinom(Polinom n, Polinom d) throws Exception{
        Polinom[] rez = new Polinom[2];
        rez[0] = new Polinom();//catul impartirii
        rez[1] = new Polinom();//restul impartirii
        if(n.toString().equals("0") && !d.toString().equals("0")){
            rez[0].addMonom(new Monom(0, 0));
            rez[1].addMonom(new Monom(0, 0));
            return rez;
        }else{
            if(d.toString().equals("0"))
                throw new Exception("Impartitorul nu poate sa fie 0 !");
            else if(!d.toString().contains("X")) {
                rez[0] = Operations.divPolinomConst(n, d);
                rez[1].addMonom(new Monom(0, 0));
                return rez;
            }
        }
        rez[1].getMonomi().putAll(n.getMonomi());
        TreeMap<Integer, Monom> r1 = new TreeMap<Integer, Monom>(new SortByDegree());
        r1.putAll(n.getMonomi());
        TreeMap<Integer, Monom> d1 = new TreeMap<Integer, Monom>(new SortByDegree());
        d1.putAll(d.getMonomi());
        if(r1.firstKey() < d1.firstKey())
            throw new Exception("Grad P < Grad Q !");
        while(!r1.isEmpty() && r1.firstKey() >= d1.firstKey())
        {
            int c = r1.get(r1.firstKey()).getCoef().intValue() / d1.get(d1.firstKey()).getCoef().intValue();
            int deg = r1.firstKey() - d1.firstKey();
            Monom t = new Monom(deg, c);
            rez[0].addMonom(t);
            rez[1] = Operations.subPolinom(rez[1], Operations.mulMonomPolinom(t, d));
            r1.clear();
            r1.putAll(rez[1].getMonomi());
            r1.entrySet().removeIf(entry -> entry.getValue().getCoef().doubleValue() == 0);
        }
        return rez;
    }
    public static Polinom derivPolinom(Polinom p){
        Polinom r = new Polinom();
        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet()) {
            if(mp.getKey() != 0)
                r.addMonom(new Monom( mp.getKey() - 1, mp.getKey() * mp.getValue().getCoef().doubleValue()));
            else
                r.addMonom(new Monom(0, 0));
        }
        return r;
    }
    public static Polinom integrPolinom(Polinom p) throws Exception{
        Polinom r = new Polinom();
        if(!p.toString().contains("X")){
            throw new Exception("C - constanta");
        }

        for(Map.Entry<Integer, Monom> mp : p.getMonomi().entrySet()) {
            r.addMonom(new Monom( mp.getKey() + 1, mp.getValue().getCoef().doubleValue() / (mp.getKey() + 1)));
        }
        return r;
    }
    public static Polinom fromStringToPolinom(String s){
        Polinom r = new Polinom();
        String monomFormat = "((^|[+-])\\s?\\d*\\s?\\*?\\s?X?\\^?\\d*)";
        String monomPartFormat = "([+-]?\\s?\\d*)\\s?\\*?\\s?(X?)\\^?(\\d*)?";
        Pattern p_monom = Pattern.compile(monomFormat);
        Matcher m_monom = p_monom.matcher(s);
        while (m_monom.find()) {
            Pattern p_part_monom = Pattern.compile(monomPartFormat);
            Matcher m_part_monom = p_part_monom.matcher(m_monom.group(1));
            if(m_part_monom.find()) {
                Pattern pcoef = Pattern.compile("([+-])?\\s?(\\d*)");
                Matcher mcoef = pcoef.matcher(m_part_monom.group(1));
                int coef = 0;
                if(mcoef.find())
                {
                    if(!Objects.equals(mcoef.group(2), ""))
                        coef = Integer.parseInt(mcoef.group(2));
                    else
                        coef = 1;
                    if(!Objects.equals(mcoef.group(1), "") && Objects.equals(mcoef.group(1), "-"))
                        coef = -coef;
                }
                int exp = 0;
                if(!Objects.equals(m_part_monom.group(3), ""))
                    exp = Integer.parseInt(m_part_monom.group(3));
                else
                    if(!Objects.equals(m_part_monom.group(2), ""))
                        exp = 1;
                Monom xy = new Monom(exp, coef);
                r.addMonom(xy);
            }
        }
        return r;
    }
}

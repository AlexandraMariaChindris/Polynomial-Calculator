package Logic;
import java.util.*;

public class SortByDegree implements Comparator{


    @Override
    public int compare(Object o1, Object o2) {
        return (int)o2 - (int)o1;
    }
}

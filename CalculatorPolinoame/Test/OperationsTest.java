import DataModel.Polinom;
import Logic.Operations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

    @Test
    public void addTest(){
        Polinom p1 = Operations.fromStringToPolinom("4 * X^5 -3 * X^4 + X^2 -8 * X + 1");
        Polinom p2 = Operations.fromStringToPolinom("3 * X^4 - X^3 + X^2 + 2 * X -1");

        assertEquals(Operations.addPolinom(p1, p2).toString(), "4 * X^5 - X^3 + 2 * X^2 -6 * X");
    }

    @Test
    public void subTest(){
        Polinom p1 = Operations.fromStringToPolinom("4 * X^5 -3 * X^4 + X^2 -8 * X + 1");
        Polinom p2 = Operations.fromStringToPolinom("3 * X^4 - X^3 + X^2 + 2 * X -1");

        assertEquals(Operations.subPolinom(p1, p2).toString(), "4 * X^5 -6 * X^4 + X^3 -10 * X + 2");
    }

    @Test
    public void mulTest(){
        Polinom p1 = Operations.fromStringToPolinom("3 * X^2 - X + 1");
        Polinom p2 = Operations.fromStringToPolinom("X -2");

        assertEquals(Operations.mulPolinom(p1, p2).toString(), "3 * X^3 -7 * X^2 + 3 * X -2");
    }

    @Test
    public void divTest() throws Exception {
        Polinom p1 = Operations.fromStringToPolinom("X^3 -2 * X^2 + 6 * X -5");
        Polinom p2 = Operations.fromStringToPolinom("X^2 -1");

        try {
            assertEquals(Operations.divPolinom(p1, p2)[0].toString(), "X -2");
            assertEquals(Operations.divPolinom(p1, p2)[1].toString(), "7 * X -7");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void derivTest(){
        Polinom p = Operations.fromStringToPolinom("X^3 -2 * X^2 + 6 * X -5");

        assertEquals(Operations.derivPolinom(p).toString(), "3.0 * X^2 -4.0 * X + 6.0");
    }

    @Test
    public void integrTest()throws Exception{
        Polinom p = Operations.fromStringToPolinom("3 * X^2 -4 * X + 6");

        assertEquals(Operations.integrPolinom(p).toString(), "X^3 -2.0 * X^2 + 6.0 * X");
    }
}

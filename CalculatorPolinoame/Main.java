package ro.tuc;

import DataModel.Monom;
import DataModel.Polinom;
import GUI.View;
import Logic.Operations;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

        JFrame frame = new View("POLYNOMIAL CALCULATOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}

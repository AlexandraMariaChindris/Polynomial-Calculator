package GUI;

import Logic.Operations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener {

    private View view;

    public Controller(View v){
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "COMPUTE"){
            String firstPolinom = view.getFirstPolinomTextField().getText();
            String secondPolinom = view.getSecondPolinomTextField().getText();
            String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());
            String result = "";
            if(!Objects.equals(firstPolinom, "") && !Objects.equals(secondPolinom, "")){
                switch(operation) {
                    case "Adunare":
                        result = (Operations.addPolinom(Operations.fromStringToPolinom(firstPolinom), Operations.fromStringToPolinom(secondPolinom))).toString();
                        break;
                    case "Scadere":
                        result = (Operations.subPolinom(Operations.fromStringToPolinom(firstPolinom), Operations.fromStringToPolinom(secondPolinom))).toString();
                        break;
                    case "Inmultire":
                        result = (Operations.mulPolinom(Operations.fromStringToPolinom(firstPolinom), Operations.fromStringToPolinom(secondPolinom))).toString();
                        break;
                    case "Impartire":
                        try {
                            result = "Catul: " + (Operations.divPolinom(Operations.fromStringToPolinom(firstPolinom), Operations.fromStringToPolinom(secondPolinom)))[0].toString() + "; Restul: " +
                                    (Operations.divPolinom(Operations.fromStringToPolinom(firstPolinom), Operations.fromStringToPolinom(secondPolinom)))[1].toString();
                        } catch (Exception m) {
                            result = m.getMessage();
                        } finally {
                            break;
                        }
                }
            }
            else
                if(!Objects.equals(firstPolinom, "")){
                    switch(operation){
                        case "Derivare": result = (Operations.derivPolinom(Operations.fromStringToPolinom(firstPolinom))).toString();
                            break;
                        case "Integrare":
                            try {
                                result = (Operations.integrPolinom(Operations.fromStringToPolinom(firstPolinom))).toString();
                            } catch (Exception ex) {
                                result = ex.getMessage();
                            }
                            break;
                    }
                }

            view.getResultTextField().setText(String.valueOf(result));
        }
    }

}

package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
public class View extends JFrame {
    private JPanel contentPanel;
    private JPanel numbersPanel;
    private JPanel operationsPanel;
    private JPanel resultPanel;
    private JLabel firstPolinomLabel;
    private JTextField firstPolinomTextField;
    private JLabel secondPolinomLabel;
    private JTextField secondPolinomTextField;
    private JLabel operationsLabel;
    private JComboBox operationsComboBox;
    private JButton computeButton;
    private JLabel resultLabel;
    private JTextField resultTextField;

    private Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void prepareGui(){
       // this.setSize(550, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.contentPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(this.contentPanel, BoxLayout.Y_AXIS);
        this.contentPanel.setLayout(boxlayout);
        this.contentPanel.setBorder(new EmptyBorder(new Insets(30, 50, 50, 50)));
        this.contentPanel.setBackground(Color.yellow);

        JLabel titleLabel = new JLabel("Calculator Polinoame");
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.contentPanel.add(titleLabel);

        this.preparePolinomPanel();
        this.prepareOperationsPanel();
        this.prepareResultPanel();


        this.setContentPane(this.contentPanel);
    }

    private void prepareResultPanel() {

        this.resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.resultPanel.setBackground(Color.yellow);

        this.resultLabel = new JLabel("Rezultat:   ", JLabel.CENTER);
        this.resultLabel.setFont( this.resultLabel.getFont().deriveFont(16.0f));
        this.resultTextField = new JTextField();
        this.resultTextField.setEditable(false);
        this.resultTextField.setBackground(Color.PINK);
        this.resultTextField.setBorder(new LineBorder(Color.magenta, 2));
        this.resultTextField.setPreferredSize(new Dimension(200, 30));

        this.resultPanel.add(this.resultLabel);
        this.resultPanel.add(this.resultTextField);

        this.contentPanel.add(this.resultPanel);
    }

    private void preparePolinomPanel() {

        this.numbersPanel = new JPanel(new GridLayout(2, 1, 2, 10));
        this.numbersPanel.setBackground(Color.yellow);

        this.firstPolinomLabel = new JLabel("Polinom P: ", JLabel.CENTER);
        this.firstPolinomLabel.setFont( this.firstPolinomLabel.getFont().deriveFont(16.0f));
        this.firstPolinomTextField = new JTextField();
        this.firstPolinomTextField.setBackground(Color.PINK);
        this.firstPolinomTextField.setBorder(new LineBorder(Color.magenta, 2));

        this.secondPolinomLabel = new JLabel("Polinom Q: ", JLabel.CENTER);
        this.secondPolinomLabel.setFont( this.secondPolinomLabel.getFont().deriveFont(16.0f));
        this.secondPolinomTextField = new JTextField();
        this.secondPolinomTextField.setBackground(Color.PINK);
        this.secondPolinomTextField.setBorder(new LineBorder(Color.magenta, 2));

        this.numbersPanel.add(this.firstPolinomLabel);
        this.numbersPanel.add(this.firstPolinomTextField);
        this.numbersPanel.add(this.secondPolinomLabel);
        this.numbersPanel.add(this.secondPolinomTextField);

        this.contentPanel.add(this.numbersPanel);
    }

    private void prepareOperationsPanel(){
        this.operationsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.operationsPanel.setBackground(Color.yellow);

        this.operationsLabel = new JLabel("Selectati operatia: ", JLabel.CENTER);
        this.operationsLabel.setFont( this.operationsLabel.getFont().deriveFont(16.0f));
        String[] operations = new String[]{"Adunare", "Scadere", "Inmultire", "Impartire", "Derivare", "Integrare"};
        this.operationsComboBox = new JComboBox(operations);
        this.operationsComboBox.setBackground(Color.PINK);
        this.operationsComboBox.setBorder(new LineBorder(Color.magenta, 2));
        this.operationsComboBox.setFont(this.operationsComboBox.getFont().deriveFont(15.0f));

        this.computeButton = new JButton("Afiseaza rezultat");
        this.computeButton.setPreferredSize(new Dimension(150, 30));
        this.computeButton.setBorder(new LineBorder(Color.magenta, 2));
        this.computeButton.setFont(this.computeButton.getFont().deriveFont(13.0f));
        this.computeButton.setBackground(Color.PINK);
        this.computeButton.setActionCommand("COMPUTE");
        this.computeButton.addActionListener(this.controller);

        this.operationsPanel.add(this.operationsLabel);
        this.operationsPanel.add(this.operationsComboBox);
        this.operationsPanel.add(this.computeButton);

        this.contentPanel.add(this.operationsPanel);

    }
    public JTextField getFirstPolinomTextField() {
        return firstPolinomTextField;
    }

    public JTextField getSecondPolinomTextField() {
        return secondPolinomTextField;
    }

    public JComboBox getOperationsComboBox() {
        return operationsComboBox;
    }

    public JTextField getResultTextField() {
        return resultTextField;
    }
}


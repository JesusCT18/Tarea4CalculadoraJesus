package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private String operando1 = "";
    private String operando2 = "";
    private String operador = "";
    private boolean nuevoNumero = true;

    // Constructor
    public PanelPrincipal() {
        initComponents();
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton botonPulsado = (JButton) ae.getSource();
        String textoBoton = botonPulsado.getText();

        if (textoBoton.matches("[0-9]")) {
            if (nuevoNumero) {
                areaTexto.setText(textoBoton);
                nuevoNumero = false;
            } else {
                areaTexto.append(textoBoton);
            }
        } else if (textoBoton.matches("[+\\-*/]")) {
            operando1 = areaTexto.getText();
            operador = textoBoton;
            nuevoNumero = true;
        } else if (textoBoton.equals("=")) {
            operando2 = areaTexto.getText();
            double resultado = calcularResultado();
            areaTexto.setText(Double.toString(resultado));
            nuevoNumero = true;
        } else if (textoBoton.equals("C")) {
            areaTexto.setText("");
            operando1 = "";
            operando2 = "";
            operador = "";
            nuevoNumero = true;
        }
    }

    private double calcularResultado() {
        try {
            double op1 = Double.parseDouble(operando1);
            double op2 = Double.parseDouble(operando2);
            return switch (operador) {
                case "+" ->
                    op1 + op2;
                case "-" ->
                    op1 - op2;
                case "*" ->
                    op1 * op2;
                case "/" ->
                    (op2 != 0) ? op1 / op2 : 0;
                default ->
                    0;
            };
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

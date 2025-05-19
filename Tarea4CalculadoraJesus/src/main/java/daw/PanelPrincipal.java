package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel implements ActionListener {

    private PanelBotones botonera;
    private JTextArea areaTexto;
    private String operando1 = "";
    private String operando2 = "";
    private String operador = "";
    private boolean nuevoNumero = true;

    public PanelPrincipal() {
        initComponents();
    }

    private void initComponents() {
        botonera = new PanelBotones();
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        this.setLayout(new BorderLayout());
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

        if ("0123456789".contains(textoBoton)) {
            // Números siempre se agregan al final
            areaTexto.append(textoBoton);
            nuevoNumero = false;

        } else if ("+-*/".contains(textoBoton)) {
            String textoCompleto = areaTexto.getText();
            int ultimoSalto = textoCompleto.lastIndexOf('\n');
            String ultimaLinea = (ultimoSalto == -1) ? textoCompleto : textoCompleto.substring(ultimoSalto + 1);

            // Si el botón es "-" y:
            // - la línea está vacía (inicio del número) o
            // - la última línea termina en operador
            // Entonces es signo negativo, no operador
            if (textoBoton.equals("-")) {
                if (ultimaLinea.isEmpty() || ultimaLinea.endsWith("+") || ultimaLinea.endsWith("-")
                        || ultimaLinea.endsWith("*") || ultimaLinea.endsWith("/")) {
                    areaTexto.append("-");
                    nuevoNumero = false;
                    return;
                }
            }

            // Si ya hay resultado (con '=') en la última línea, iniciamos nueva operación
            if (ultimaLinea.contains("=")) {
                areaTexto.append("\n");
                ultimaLinea = "";  // Empezamos de nuevo en nueva línea
            }

            // Añadimos operador con un espacio antes y después para legibilidad
            areaTexto.append(" " + textoBoton + " ");
            nuevoNumero = false;

        } else if (textoBoton.equals("=")) {
            String textoCompleto = areaTexto.getText();
            int ultimoSalto = textoCompleto.lastIndexOf('\n');
            String ultimaLinea = (ultimoSalto == -1) ? textoCompleto : textoCompleto.substring(ultimoSalto + 1);

            // Parsear operandos y operador con posible número negativo
            String[] partes = ultimaLinea.split(" ");

            if (partes.length >= 3) {
                operando1 = partes[0];
                operador = partes[1];
                operando2 = partes[2];
            } else {
                // Si no hay suficientes partes, no hacemos nada
                return;
            }

            double resultado = calcularResultado();
            areaTexto.append(" = " + resultado + "\n");
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

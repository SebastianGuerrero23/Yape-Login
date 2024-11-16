import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Calculadora");
        frame.setSize(400, 700); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextField display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 72)); 
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(30, 30, 30)); 
        display.setForeground(Color.WHITE); 
        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5)); 
        panel.setBackground(new Color(30, 30, 30));
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5)); 

        String[] botones = {
            "MC", "MR", "M+", "M-",
            "CE", "C", "⌫", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        final String[] operador = {""};
        final double[] num1 = {0};

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Segoe UI", Font.PLAIN, 18)); 
            
            // Configuración de colores
            if (texto.equals("=")) {
                boton.setBackground(new Color(0, 191, 255)); 
                boton.setForeground(Color.WHITE);
            } else {
                boton.setBackground(new Color(50, 50, 50));
                boton.setForeground(Color.WHITE);
            }

            boton.setPreferredSize(new Dimension(70, 70)); 
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(boton);

            
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comando = e.getActionCommand();

                    if ("0123456789".contains(comando)) {
                        
                        display.setText(display.getText() + comando);
                    } else if ("+-×÷".contains(comando)) {
                       
                        num1[0] = Double.parseDouble(display.getText());
                        operador[0] = comando;
                        display.setText("");
                    } else if (comando.equals("=")) {
                       
                        double num2 = Double.parseDouble(display.getText());
                        double resultado = 0;
                        switch (operador[0]) {
                            case "+": resultado = num1[0] + num2; break;
                            case "-": resultado = num1[0] - num2; break;
                            case "×": resultado = num1[0] * num2; break;
                            case "÷":
                                if (num2 != 0) {
                                    resultado = num1[0] / num2;
                                } else {
                                    display.setText("Error");
                                    return;
                                }
                                break;
                        }
                        display.setText(String.valueOf(resultado));
                        operador[0] = "";
                    } else if (comando.equals("C")) {
                       
                        display.setText("");
                        operador[0] = "";
                        num1[0] = 0;
                    } else if (comando.equals("←")) {
                        
                        String textoActual = display.getText();
                        if (textoActual.length() > 0) {
                            display.setText(textoActual.substring(0, textoActual.length() - 1));
                        }
                    } else if (comando.equals("±")) {
                   
                        if (!display.getText().isEmpty()) {
                            double valor = Double.parseDouble(display.getText());
                            valor *= -1;
                            display.setText(String.valueOf(valor));
                        }
                    } else if (comando.equals(".")) {
                    

                        if (!display.getText().contains(".")) {
                            display.setText(display.getText() + ".");
                        }
                    }
                }
            });
        }


        frame.setVisible(true);
    }
}



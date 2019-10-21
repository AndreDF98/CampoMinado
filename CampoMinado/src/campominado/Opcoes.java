
package campominado;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Opcoes{
    
    public Opcoes(int Tamanho, int Bombas){
        JFrame janela = new JFrame("Opcoes");
        janela.setSize(300,130);
        janela.setResizable(false);
        
        JPanel top = new JPanel();
        janela.add(top, BorderLayout.NORTH);
        
        JTextField tamanho = new JTextField(3);
        tamanho.setText("" + Tamanho);
        JLabel dimensao = new JLabel("Tamanho do Tabuleiro: ");
        top.add(dimensao);
        top.add(tamanho);
        
        //-----------------------------
        
        JPanel mid = new JPanel();
        janela.add(mid, BorderLayout.CENTER);
        
        JTextField bombas = new JTextField(4);
        bombas.setText("" + Bombas);
        JLabel Nbombas = new JLabel("Numero de Bombas: ");
        mid.add(Nbombas);
        mid.add(bombas);
        
        //------------------------------
        
        JPanel bot = new JPanel();
        janela.add(bot, BorderLayout.SOUTH);
        
        JButton aplicar = new JButton("Aplicar");
        JButton cancelar = new JButton("Cancelar");
        
        bot.add(aplicar);
        bot.add(cancelar);
        
        //------------------------------
        
        aplicar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int bomba;
                int tam;
                try{
                    bomba = Integer.valueOf(bombas.getText());
                    tam = Integer.valueOf(tamanho.getText());
                    if(bomba>0){
                        if(bomba<tam*tam){
                            if(tam>0){
                                new CampoMinado(bomba, tam);
                                janela.dispose();
                            }else JOptionPane.showMessageDialog(null, "Tabuleiro muito pequeno! Por favor tente novamente.", "ERRO!", 0);
                        }else JOptionPane.showMessageDialog(null, "Bombas demais! Por favor tente novamente.", "ERRO!", 0);
                    }else JOptionPane.showMessageDialog(null, "Coloque pelo menos uma bomba! Por favor tente novamente.", "ERRO!", 0);
                }
                catch(NumberFormatException exp){
                    System.err.printf("\nException: %s\n", exp);
                    JOptionPane.showMessageDialog(null, "Use numeros inteiros! Por favor tente novamente.", "ERRO!", 0);
                }
                catch(NegativeArraySizeException exp2){
                    System.err.printf("\nException: %s\n", exp2);
                    JOptionPane.showMessageDialog(null, "Use numeros positivos! Por favor tente novamente.", "ERRO!", 0);
                }
            }
        });
        
        cancelar.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               janela.dispose();
           } 
        });
        
        //------------------------------
        
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    
}

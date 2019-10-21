
package campominado;

import javax.swing.JButton;

public class Botao extends JButton{
    int temBomba;//se tem bomba = 1, senao = 0
    
    boolean clicado = false;
    
    int cordX, cordY;
    
    int norte;
    int sul;
    int leste;
    int oeste;
    int nordeste;
    int sudeste;
    int sudoeste;
    int noroeste;
    
    public Botao(int a, int x, int y){
        temBomba = a;
        cordX = x;
        cordY = y;
    }
    
    int bombasAdj(){
        return norte+sul+leste+oeste+nordeste+sudeste+sudoeste+noroeste;
    }
    
    void reseta(int x, int y){
        setIcon(null);
        setText("");
        setBackground(null);
        temBomba = 0;
        cordX = x;
        cordY = y;
        clicado = false;
    }
    
}
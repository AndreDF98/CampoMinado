
package campominado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Temporizador extends JLabel{
    int cont = 0;
    int mm; //minuto
    int ss; //segundo
    DecimalFormat formato; //*
    Timer time;
  
    public Temporizador(){
 
        //colocando o intervalo de um segundo "1s"
        time = new Timer(1000, ativar);//a cada 1 segundo realiza a acao "ativar"
        //inicia o relógio
        time.start();
    }
    
    //metodo que irá formatar em duas casas decimais
    private String formatar(int num) {
        formato = new DecimalFormat("00");
        return formato.format(num);
    }
    
    public void parar(){
        time.stop();
    }
    
    public void reinicia(){
        cont = 0;
        setText("00:00");
        time.restart();
    }
 
    ActionListener ativar = (new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            cont++;
            mm = cont / 60;
            ss = cont % 60;
            //aqui montamos o modelo do relógio
            setText(formatar(mm) + ":" + formatar(ss));
        }
 
    });
 
}
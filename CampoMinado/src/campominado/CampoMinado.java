
package campominado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class CampoMinado {
    int flags; //numero de bandeiras
    boolean trava = false;//trava o tabuleiro no fim do jogo
    JFrame janela = new JFrame ("Campo Minado");
    
    public CampoMinado(int bIni, int tamTab){
        
        flags = bIni;
        
        //------------------------------------------------
        
        janela.setSize(800,800);
        
        Container conteudo = janela.getContentPane();
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon icone = new ImageIcon(getClass().getResource("icone.png"));
        janela.setIconImage(icone.getImage());
        
        JPanel cabeca = new JPanel();
        
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(tamTab,tamTab));
        
        conteudo.add(cabeca, BorderLayout.NORTH);
        conteudo.add(painel, BorderLayout.CENTER);
        
        //------------------------------------------------
        
        JMenu info = new JMenu("Info");
        JMenuItem tutorial = new JMenuItem("Como Jogar");
        info.add(tutorial);
        JMenuItem creditos = new JMenuItem("Creditos");
        info.add(creditos);
        
        JMenu opcoes = new JMenu("Opcoes");
        
        JMenuBar menu = new JMenuBar();
        menu.add(opcoes);
        menu.add(info);
        janela.setJMenuBar(menu);
        
        //-------------------------------------------------
        
        JLabel bombas = new JLabel("" + flags);
        bombas.setOpaque(true);
        bombas.setPreferredSize(new Dimension(80,50));
        bombas.setFont(new Font("DS-Digital", Font.BOLD, 36));
        bombas.setHorizontalAlignment(SwingConstants.CENTER);
        bombas.setForeground(Color.red);
        bombas.setBackground(Color.black);
        
        JButton reset = new JButton(new ImageIcon(getClass().getResource("reset.png")));
        reset.setPreferredSize(new Dimension(50,50));
        
        Temporizador tempo = new Temporizador();
        tempo.setOpaque(true);
        tempo.setPreferredSize(new Dimension(150,50));
        tempo.setFont(new Font("DS-Digital", Font.BOLD, 36));
        tempo.setHorizontalAlignment(SwingConstants.CENTER);
        tempo.setForeground(Color.red);
        tempo.setBackground(Color.black);
        
        cabeca.add(bombas);
        cabeca.add(reset);
        cabeca.add(tempo);
        
        //-------------------------------------------------
        
        int i, j;
        Botao matriz[][] = new Botao[tamTab][tamTab];
        
        for(i=0;i<matriz.length;i++)
            for(j=0;j<matriz.length;j++){
                matriz[i][j] = new Botao(0, i, j);
                painel.add(matriz[i][j]);
            }
        
        Random gerador = new Random();
        int cont = 0;
        while(cont<flags){
            int a = gerador.nextInt(tamTab), d = gerador.nextInt(tamTab);
            if(matriz[a][d].temBomba == 0){
            matriz[a][d].temBomba = 1;
            cont++;
            }
        }
        
        cria_tabuleiro(matriz, tamTab);
        
        //-------------------------------------------------
        
        //clique na matriz com o botao esquerdo
        
        ActionListener clicou = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //jogo em si
                if(trava == false){
                    Botao botao = (Botao) e.getSource();
                
                    ImageIcon perde = new ImageIcon(getClass().getResource("resetLose.png"));
                    ImageIcon ganha = new ImageIcon(getClass().getResource("resetWin.png"));
                    
                    if(botao.getIcon() == null && botao.temBomba == 0){//se não tem bomba
                        int i = botao.cordX,j = botao.cordY;
                        int adj = botao.bombasAdj();
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i>0 && i<tamTab-1 && j>0 && j<tamTab-1){//trata botoes do centro
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i-1][j-1].clicado == false){
                                matriz[i-1][j-1].clicado = true;
                                matriz[i-1][j-1].doClick(0);
                            }
                            if(matriz[i+1][j-1].clicado == false){
                                matriz[i+1][j-1].clicado = true;
                                matriz[i+1][j-1].doClick(0);
                            }
                            if(matriz[i-1][j+1].clicado == false){
                                matriz[i-1][j+1].clicado = true;
                                matriz[i-1][j+1].doClick(0);
                            }
                            if(matriz[i+1][j+1].clicado == false){
                                matriz[i+1][j+1].clicado = true;
                                matriz[i+1][j+1].doClick(0);
                            }
                        }
                            
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==0 && j>0 && j<tamTab-1){//trata a borda de cima
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i+1][j+1].clicado == false){
                                matriz[i+1][j+1].clicado = true;
                                matriz[i+1][j+1].doClick(0);
                            }
                            if(matriz[i+1][j-1].clicado == false){
                                matriz[i+1][j-1].clicado = true;
                                matriz[i+1][j-1].doClick(0);
                            }
                        }
                            
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==tamTab-1 && j>0 && j<tamTab-1){//trata borda de baixo
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i-1][j-1].clicado == false){
                                matriz[i-1][j-1].clicado = true;
                                matriz[i-1][j-1].doClick(0);
                            }
                            if(matriz[i-1][j+1].clicado == false){
                                matriz[i-1][j+1].clicado = true;
                                matriz[i-1][j+1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i>0 && i<tamTab-1 && j==0){//trata borda da esquerda
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i-1][j+1].clicado == false){
                                matriz[i-1][j+1].clicado = true;
                                matriz[i-1][j+1].doClick(0);
                            }
                            if(matriz[i+1][j+1].clicado == false){
                                matriz[i+1][j+1].clicado = true;
                                matriz[i+1][j+1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i>0 && i<tamTab-1 && j==tamTab-1){//trata borda da direita
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i-1][j-1].clicado == false){
                                matriz[i-1][j-1].clicado = true;
                                matriz[i-1][j-1].doClick(0);
                            }
                            if(matriz[i+1][j-1].clicado == false){
                                matriz[i+1][j-1].clicado = true;
                                matriz[i+1][j-1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==0 && j==0){//trata canto superior esquerdo
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i+1][j+1].clicado == false){
                                matriz[i+1][j+1].clicado = true;
                                matriz[i+1][j+1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==0 && j==tamTab-1){//trata canto superior direito
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i+1][j].clicado == false){
                                matriz[i+1][j].clicado = true;
                                matriz[i+1][j].doClick(0);
                            }
                            if(matriz[i+1][j-1].clicado == false){
                                matriz[i+1][j-1].clicado = true;
                                matriz[i+1][j-1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==tamTab-1 && j==0){//trata canto inferior esquerdo
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j+1].clicado == false){
                                matriz[i][j+1].clicado = true;
                                matriz[i][j+1].doClick(0);
                            }
                            if(matriz[i-1][j+1].clicado == false){
                                matriz[i-1][j+1].clicado = true;
                                matriz[i-1][j+1].doClick(0);
                            }
                        }
                        
                        if(adj == 0 && botao.getBackground() != Color.LIGHT_GRAY && i==tamTab-1 && j==tamTab-1){//trata canto inferior direito
                            if(matriz[i-1][j].clicado == false){
                                matriz[i-1][j].clicado = true;
                                matriz[i-1][j].doClick(0);
                            }
                            if(matriz[i][j-1].clicado == false){
                                matriz[i][j-1].clicado = true;
                                matriz[i][j-1].doClick(0);
                            }
                            if(matriz[i-1][j-1].clicado == false){
                                matriz[i-1][j-1].clicado = true;
                                matriz[i-1][j-1].doClick(0);
                            }
                        }
                        
                        botao.setBackground(Color.LIGHT_GRAY);
                        
                        if(adj == 1){
                            botao.setText("1");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.blue);
                        }
                        if(adj == 2){
                            botao.setText("2");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.green.darker());
                        }
                        if(adj == 3){
                            botao.setText("3");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.red);
                        }
                        if(adj == 4){
                            botao.setText("4");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.blue.darker());
                        }
                        if(adj == 5){
                            botao.setText("5");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.red.darker());
                        }
                        if(adj == 6){
                            botao.setText("6");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.blue.brighter());
                        }
                        if(adj == 7){
                            botao.setText("7");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.black);
                        }
                        if(adj == 8){
                            botao.setText("8");
                            //botao.setFont(new Font("Arial", Font.BOLD, 15));
                            botao.setForeground(Color.darkGray);
                        }
                            
                    }
                        
                    if(botao.getIcon() == null && botao.temBomba == 1){//se tem bomba
                        reset.setIcon(perde);
                        tempo.parar();
                        
                        int i,j;
                        for(i=0;i<tamTab;i++)
                            for(j=0;j<tamTab;j++){
                                if(matriz[i][j].temBomba == 1){
                                    matriz[i][j].setBackground(Color.GRAY);
                                    matriz[i][j].setIcon(new ImageIcon(getClass().getResource("mine.png")));
                                }
                                botao.setBackground(Color.red);
                                trava = true;
                            }
                    }
                    
                    //Ganhar o jogo
                    int i, j, c=0;
                    for(i=0;i<tamTab;i++)
                        for(j=0;j<tamTab;j++){
                            if(matriz[i][j].temBomba == 0 && matriz[i][j].getBackground() == Color.LIGHT_GRAY) c++;
                        }
                    if(c == (tamTab*tamTab)-bIni){
                        reset.setIcon(ganha);
                        tempo.parar();
                        trava = true;
                    }
                }  
            }
        };
        
        for(i=0;i<matriz.length;i++)
            for(j=0;j<matriz.length;j++)
                matriz[i][j].addActionListener(clicou);
       
        //mouseListeners para a matriz
        for(i=0;i<matriz.length;i++)
            for(j=0;j<matriz.length;j++)
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(trava == false){
                            Botao botao = (Botao) e.getSource();

                            ImageIcon flag = new ImageIcon(getClass().getResource("flag.png"));

                            //marcar bandeiras
                            if(e.getButton() == MouseEvent.BUTTON3){
                                if(botao.getIcon() == null && botao.getBackground() != Color.LIGHT_GRAY){
                                    bombas.setText("" + --flags);
                                    botao.setIcon(flag);
                                } else if(botao.getBackground() != Color.LIGHT_GRAY){
                                    bombas.setText("" + ++flags);
                                    botao.setIcon(null);
                                }

                            }
                        }
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e){
                        if(trava==false){
                            ImageIcon clique = new ImageIcon(getClass().getResource("resetClick.png"));
                            reset.setIcon(clique);
                        }
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e){
                        Botao b = (Botao) e.getSource();
                        if(b.temBomba == 0 && trava==false){
                            ImageIcon reseta = new ImageIcon(getClass().getResource("reset.png"));
                            reset.setIcon(reseta);
                        }
                    }
        });
        
        //clique no reset
        reset.addActionListener (new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                flags = bIni;
                bombas.setText("" + flags);
                tempo.reinicia();
                reset.setIcon(new ImageIcon(getClass().getResource("reset.png")));
                
                int i, j;
                for(i=0;i<matriz.length;i++)
                    for(j=0;j<matriz.length;j++){
                        matriz[i][j].reseta(i, j);
                        trava = false;
                    }
                
                Random gerador = new Random();
                int cont = 0;
                while(cont<flags){
                    int a = gerador.nextInt(tamTab), b = gerador.nextInt(tamTab);
                    if(matriz[a][b].temBomba == 0){
                        matriz[a][b].temBomba = 1;
                        cont++;
                    }
                }
                
                System.out.println("\nNovo Jogo:\n");
                cria_tabuleiro(matriz, tamTab);
            }
        });
        
        //clique nos menus
        
        opcoes.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e){
               Opcoes op = new Opcoes(tamTab, bIni);
           }
        });
        
        tutorial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Clique nos botoes e torca pra nao pisar em uma mina!", "Como Jogar", -1);
            }
        });
        
        creditos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Andre Dias Freitas\nSalomao Oliveira Alves", "Creditos", -1);
            }
        });
        
        //-------------------------------------------------
        
        janela.setLocationRelativeTo(null); //centraliza a janela
        janela.setVisible(true);
    }
    
    void cria_tabuleiro(Botao matriz[][], int tamTab){
        int i, j;
        for(i=0;i<tamTab;i++){
            for(j=0;j<tamTab;j++){
                if(matriz[i][j].temBomba == 0){
                    if((i > 0 && i < tamTab-1) && (j > 0 && j < tamTab-1)){//trata o botoes do centro
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = matriz[i-1][j+1].temBomba;
                        matriz[i][j].noroeste = matriz[i-1][j-1].temBomba;
                        matriz[i][j].sudeste = matriz[i+1][j+1].temBomba;
                        matriz[i][j].sudoeste = matriz[i+1][j-1].temBomba;
                    }

                    if(i==0 && (j>0 && j<tamTab-1)){//borda de cima
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = 0;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = 0;
                        matriz[i][j].noroeste = 0;
                        matriz[i][j].sudeste = matriz[i+1][j+1].temBomba;
                        matriz[i][j].sudoeste = matriz[i+1][j-1].temBomba;
                    }

                    if(i==tamTab-1 && (j>0 && j<tamTab-1)){//borda de baixo
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = 0;

                        matriz[i][j].nordeste = matriz[i-1][j+1].temBomba;
                        matriz[i][j].noroeste = matriz[i-1][j-1].temBomba;
                        matriz[i][j].sudeste = 0;
                        matriz[i][j].sudoeste = 0;
                    }

                    if(j==0 && (i>0 && i<tamTab-1)){//borda da esquerda
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = 0;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = matriz[i-1][j+1].temBomba;
                        matriz[i][j].noroeste = 0;
                        matriz[i][j].sudeste = matriz[i+1][j+1].temBomba;
                        matriz[i][j].sudoeste = 0;
                    }

                    if(j==tamTab-1 && (i>0 && i<tamTab-1)){//borda de direita
                        matriz[i][j].leste = 0;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = 0;
                        matriz[i][j].noroeste = matriz[i-1][j-1].temBomba;
                        matriz[i][j].sudeste = 0;
                        matriz[i][j].sudoeste = matriz[i+1][j-1].temBomba;
                    }

                    if(i==0 && j==0){//canto superior esquerdo
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = 0;
                        matriz[i][j].norte = 0;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = 0;
                        matriz[i][j].noroeste = 0;
                        matriz[i][j].sudeste = matriz[i+1][j+1].temBomba;
                        matriz[i][j].sudoeste = 0;
                    }

                    if(i==0 && j==tamTab-1){//canto superior direito
                        matriz[i][j].leste = 0;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = 0;
                        matriz[i][j].sul = matriz[i+1][j].temBomba;

                        matriz[i][j].nordeste = 0;
                        matriz[i][j].noroeste = 0;
                        matriz[i][j].sudeste = 0;
                        matriz[i][j].sudoeste = matriz[i+1][j-1].temBomba;
                    }

                    if(i==tamTab-1 && j==0){//canto inferior esquerdo
                        matriz[i][j].leste = matriz[i][j+1].temBomba;
                        matriz[i][j].oeste = 0;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = 0;

                        matriz[i][j].nordeste = matriz[i-1][j+1].temBomba;
                        matriz[i][j].noroeste = 0;
                        matriz[i][j].sudeste = 0;
                        matriz[i][j].sudoeste = 0;
                    }

                    if(i==tamTab-1 && j==tamTab-1){//canto inferior direito
                        matriz[i][j].leste = 0;
                        matriz[i][j].oeste = matriz[i][j-1].temBomba;
                        matriz[i][j].norte = matriz[i-1][j].temBomba;
                        matriz[i][j].sul = 0;

                        matriz[i][j].nordeste = 0;
                        matriz[i][j].noroeste = matriz[i-1][j-1].temBomba;
                        matriz[i][j].sudeste = 0;
                        matriz[i][j].sudoeste = 0;
                    }

                }
                System.out.print(matriz[i][j].temBomba + "  ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        new CampoMinado(10, 10);
    }
}
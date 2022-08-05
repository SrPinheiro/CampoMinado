package br.com.leo.cm.visao;

import br.com.leo.cm.modelo.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    TelaPrincipal(){
        PainelTabuleiro pt = new PainelTabuleiro(new Tabuleiro(16,30, 50));

        setVisible(true);

        setTitle("CampoMinado!");
        setSize(690,438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(pt);

    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }

}

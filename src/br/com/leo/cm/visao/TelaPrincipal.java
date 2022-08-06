package br.com.leo.cm.visao;

import br.com.leo.cm.modelo.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal(String titulo){
        PainelTabuleiro pt = new PainelTabuleiro(new Tabuleiro(16,30, 50));

        setVisible(true);

        setTitle(titulo);
        setSize(690,438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(pt);
    }
}
/*
 * Codigo feito por Leonardo Pinheiro
 * IDE: Intellij IDEA — JetBrains
 * Turma: Info 0121
 * IFNMG — Campus Almenara
 * GitHub: https://github.com/SrPinheiro
 * Data: 04/08/2022
 */

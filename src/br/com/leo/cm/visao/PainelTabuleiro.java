package br.com.leo.cm.visao;

import br.com.leo.cm.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    PainelTabuleiro(Tabuleiro tb){
        setLayout(new GridLayout(tb.getLinhas(), tb.getColunas()));

        tb.paraCada(c -> add(new BotaoCampo(c)));

        tb.registrarObservador(c -> SwingUtilities.invokeLater(() -> {
            if(c.isGanhou()){
                JOptionPane.showMessageDialog(null, "Você Ganhou!");

            }else{
                JOptionPane.showMessageDialog(null,"Você perdeu!");
            }
            tb.reiniciar();
        }));
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
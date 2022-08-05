package br.com.leo.cm.visao;

import br.com.leo.cm.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    PainelTabuleiro(Tabuleiro tb){
        setLayout(new GridLayout(tb.getLinhas(), tb.getColunas()));

        tb.paraCada(c ->{
            add(new BotaoCampo(c));
        });

        tb.registrarObservador(c ->{
            //TODO Mostrar resultado para o usuario


        });
    }
}

package br.com.leo.cm;

import br.com.leo.cm.modelo.Tabuleiro;
import br.com.leo.cm.visao.MotorGraficoTerminal;

public class Game {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(9, 9, 15);
        new MotorGraficoTerminal(tabuleiro);
    }
}

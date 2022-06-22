package br.com.leo.cm;

import br.com.leo.cm.modelo.Tabuleiro;
import br.com.leo.cm.visao.MotorGrafico;

public class Game {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(9, 9, 81);
        new MotorGrafico(tabuleiro);
    }
}

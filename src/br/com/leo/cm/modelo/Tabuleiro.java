package br.com.leo.cm.modelo;

import java.util.ArrayList;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final ArrayList<Campo> arena = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    private void gerarCampos() {
        for (int linha = 0; linha < this.linhas; linha++) {
            for (int coluna = 0; coluna < this.colunas; coluna++) {
                    arena.add(new Campo(linha, coluna));
            }
        }

    }

    private void associarVizinhos() {
        for (var k1 : arena) {
            for (var k2 : arena) {
                k1.adicionarVizinho(k2);
            }
        }
    }

    private void sortearMinas() {
        byte armadas = 0;
        while(armadas < this.minas){
            for (var k1 : arena) {
                int aleatorio = (int) (1 + Math.random() * 4);

                if(aleatorio == 1){
                    k1.minar();
                    armadas++;
                }
            }
        }
    }
}

package br.com.leo.cm.modelo;

import br.com.leo.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tabuleiro {

    private final int linhas;
    private final int colunas;
    private final int minas;

    private final ArrayList<Campo> arena = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void abrir(int linha, int coluna){
        try{
            arena.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(Campo::abrir);

        }catch (ExplosaoException e){
            //arena.stream().findAny().ifPresent(Campo::setAberto);
            arena.forEach(c ->{
                if(c.isMinado()){
                    c.setAberto();
                }
            });
            throw e;
        }
    }

    public void marcarCampo(int linha, int coluna){
        arena.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(Campo::alternarMarcacao);
    }

    public boolean ObjetivoAlcancado() {
        AtomicBoolean objetivo = new AtomicBoolean(true);
        arena.forEach(c ->{
            if(!c.isMinado() && !c.isAberto()){
                objetivo.set(false);
            }
        });
        return Boolean.parseBoolean(String.valueOf(objetivo));
    }

    public void reiniciar(){
        arena.forEach(Campo::reiniciar);
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
       externo: while(armadas < this.minas){
            for (var k1 : arena) {
                if(!(armadas < this.minas)){
                    break externo;

                }else {
                    int aleatorio = (int) (1 + Math.random() * 50);

                    if (aleatorio == 1) {
                        if (k1.minar()) {
                            armadas++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        for (int i = 1; i <= colunas; i++) {
            System.out.printf("  %d", i);
        }
        System.out.println();
        StringBuilder sb = new StringBuilder();
        int x = 0;
        int y = 1;
        for (int i = 0; i < linhas; i++) {
            sb.append(String.format("%d", y++));
            for (int j = 0; j < colunas; j++) {
                sb.append(" ");
                sb.append(arena.get(x));
                sb.append(" ");
                x++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

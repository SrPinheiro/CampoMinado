package br.com.leo.cm.modelo;

import br.com.leo.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Tabuleiro implements CampoObserver {

    private final int linhas;
    private final int colunas;
    private final int minas;

    private final ArrayList<Campo> arena = new ArrayList<>();
    private final List<Consumer<ResultadoEvent>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
        arena.forEach(Campo::procurarBombas);
    }

    public void registrarObservador(Consumer<ResultadoEvent> observador){
        this.observadores.add(observador);

    }
    private void notificarObservadores(boolean result){
        this.observadores.stream().forEach(e -> e.accept(new ResultadoEvent(result)));

    }
    public void abrir(int linha, int coluna){
        arena.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(Campo::abrir);

    }


    public void marcarCampo(int linha, int coluna){
        arena.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(Campo::alternarMarcacao);
    }

    public boolean objetivoAlcancado() {
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
                Campo campo = new Campo(linha,coluna);
                campo.registrarObservador(this);
                arena.add(campo);
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

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
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
            System.out.printf("  \u001B[37m%d", i);
        }
        System.out.println();
        StringBuilder sb = new StringBuilder();
        int x = 0;
        int y = 1;
        for (int i = 0; i < linhas; i++) {
            sb.append(String.format("\u001B[37m%d", y++));
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
    public void paraCada(Consumer<Campo> func){
        arena.forEach(func);
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvent event) {
        if(event == CampoEvent.EXPLODIR){
            this.mostrarMinas();
            notificarObservadores(false);

        }else if(this.objetivoAlcancado()){
            System.out.println("Você ganhou!");
            notificarObservadores(true);
        }

    }

    private void mostrarMinas(){
        arena.stream().filter( c -> c.isMinado()).forEach(c-> c.setAberto());

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
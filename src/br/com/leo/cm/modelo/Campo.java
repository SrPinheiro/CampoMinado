package br.com.leo.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int linha;
    private final int coluna;
    private int bombas;
    private boolean seguro = true;
    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private final List<Campo> vizinhos = new ArrayList<>();
    private final List<CampoObserver> observers = new ArrayList<>();


    /**
     * Classe Campo, esta classe é responsavel por criar
     * cade bloco do campo minado, é ela que ira criar campos minado,
     * e também é responsavel pela marcacao do bloco!.
     */


    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(CampoObserver observer){
        observers.add(observer);

    }
    private void notificarObservadores(CampoEvent event){
        observers.forEach(obs -> obs.eventoOcorreu(this, event));
    }

    void setAberto() {
        this.aberto = true;
        this.notificarObservadores(CampoEvent.ABRIR);
    }

    void adicionarVizinho(Campo vizinho){
        /*
         * Esse método seleciona todos os blocos
         * que estão ao lado, fazendo assim com que seja possível
         * a verificação dos campos minados!
         */
        boolean linhaDiferente = this.linha != vizinho.linha;
        boolean colunaDiferente = this.coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(this.linha - vizinho.linha);
        int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
        int delta = deltaLinha + deltaColuna;

        if((delta == 1 && !diagonal) || (delta == 2 && diagonal)){
            this.vizinhos.add(vizinho);

        }
    }

    public void alternarMarcacao(){
        /*
         * Esse método é utilizado para realizar a marcação
         * dos blocos desejados pelo usuário
         */
        if(!this.aberto){
            this.marcado = !marcado;
            if(this.marcado){
                notificarObservadores(CampoEvent.MARCAR);

            }else{
                notificarObservadores(CampoEvent.DESMARCAR);

            }
        }
    }

    public int getBombas() {
        return bombas;
    }

    public void abrir(){
        /*
         * Esse método é responsavel por abrir os blocos,
         * para que um bloco seja aberto é necessario que ele não
         * esteja marcado e não tenha sido aberto anteriormente,
         * quando aberto, se for uma vizinhança segura todos os blocos
         * ao seu redor também serão abertos, se o campo aberto esteja
         * minado ele ira lançar a exceção ExplosaoException
         */
        if(!this.aberto && !this.marcado){

            if(this.minado){
                notificarObservadores(CampoEvent.EXPLODIR);
            }else{
                this.setAberto();
                if(this.seguro){
                    vizinhos.forEach(Campo::abrir);
                }
            }
        }
    }

    public boolean minar(){
        if(!this.aberto && !this.minado){
            this.minado = true;
            return true;
        }
        return false;
    }
    void procurarBombas(){
        this.bombas = 0;

        for (var k1 : vizinhos){
            if(k1.minado){
                this.bombas++;
                this.seguro = false;
            }
        }
    }
    public boolean isMarcado() {
        return marcado;
    }

    public boolean isMinado() {
        return minado;
    }

    public void reiniciar(){
        this.aberto = false;
        this.marcado = false;
        this.minado = false;
        this.seguro = true;
        this.notificarObservadores(CampoEvent.REINICIAR);

    }
    @Override
    public String toString(){
        if(this.marcado){
            return "\u001B[35mX";

        }else if(this.aberto && this.minado){
            return "\u001B[31m*";

        }else if(this.aberto && this.bombas > 0){
            return String.format("\u001B[34m%d",this.bombas);

        }else if(this.aberto){
            return " ";

        }else{
            return "\u001B[37m?";
        }

    }
    public boolean isAberto() {
        return aberto;
    }

    public boolean isSeguro() {
        return seguro;
    }
}

/*
 * Codigo feito por Leonardo Pinheiro
 * IDE: Intellij IDEA — JetBrains
 * Turma: Info 0121
 * IFNMG — Campus Almenara
 * GitHub: https://github.com/SrPinheiro
 * Data: 20/06/2022
 */

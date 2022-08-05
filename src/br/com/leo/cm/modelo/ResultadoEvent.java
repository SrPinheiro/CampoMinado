package br.com.leo.cm.modelo;

public class ResultadoEvent {
    private final boolean ganhou;

    ResultadoEvent(boolean result){
        this.ganhou = result;

    }

    public boolean isGanhou() {
        return ganhou;
    }
}

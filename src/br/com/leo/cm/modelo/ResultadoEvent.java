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
/*
 * Codigo feito por Leonardo Pinheiro
 * IDE: Intellij IDEA — JetBrains
 * Turma: Info 0121
 * IFNMG — Campus Almenara
 * GitHub: https://github.com/SrPinheiro
 * Data: 24/07/2022
 */
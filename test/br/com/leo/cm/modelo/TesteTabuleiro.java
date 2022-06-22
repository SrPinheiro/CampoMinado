package br.com.leo.cm.modelo;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class TesteTabuleiro {


    @Test
    void testarTabuleiro(){
        Tabuleiro teste = new Tabuleiro(3,3,4);
    }

    @Test
    void testarJogo(){
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println(tabuleiro);
            System.out.println("Digite qual bloco abrir");
            int a = 5;
            int b = 3;
            tabuleiro.abrir(a, b);
        }
    }
}

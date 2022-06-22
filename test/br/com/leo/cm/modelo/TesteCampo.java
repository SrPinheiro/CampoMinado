/*package br.com.leo.cm.modelo;

import br.com.leo.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TesteCampo {
    private Campo campo;

    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3, 3);
    }


    @Test
    void testeVizinho(){ // funcao aprovada
        Campo vizinho = new Campo(4,3);
        assertTrue(campo.adicionarVizinho(vizinho));

    }

    @Test
    void testeAbrir(){
        System.out.println(campo.isAberto());
        campo.abrir();
        System.out.println(campo.isAberto());

    }

    @Test
    void testeAbrirCampoMinado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> { campo.abrir(); } );
    }

    @Test
    void testeAbrirVizinhos(){
        Campo vizinho1 = new Campo(4,3);
        Campo vizinho2 = new Campo(2,3); vizinho2.minar();
        Campo vizinho3 = new Campo(3,2);

        campo.adicionarVizinho(vizinho1);
        campo.adicionarVizinho(vizinho2);
        campo.adicionarVizinho(vizinho3);

        campo.abrir();
        System.out.println(campo.getBombas());
        System.out.println(vizinho1.isAberto());
        System.out.println(vizinho2.isAberto());
        System.out.println(vizinho3.isAberto());
        System.out.println(vizinho1.getBombas());


    }
    @Test
    void testarMarcacao(){
        System.out.println(campo.isMarcado());
        campo.alternarMarcacao();
        System.out.println(campo.isMarcado());
        campo.alternarMarcacao();
        System.out.println(campo.isMarcado());
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
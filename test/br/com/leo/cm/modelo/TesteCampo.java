package br.com.leo.cm.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
}
/*
 * Codigo feito por Leonardo Pinheiro
 * IDE: Intellij IDEA — JetBrains
 * Turma: Info 0121
 * IFNMG — Campus Almenara
 * GitHub: https://github.com/SrPinheiro
 * Data: 20/06/2022
 */
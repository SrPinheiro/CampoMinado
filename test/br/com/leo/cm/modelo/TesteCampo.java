package br.com.leo.cm.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCampo {
    private Campo campo = new Campo(3, 3);

    @Test
    void testeVizinho(){ // funcao aprovada
        Campo vizinho = new Campo(4,3);

        assertTrue(campo.adicionarVizinho(vizinho));

    }
}

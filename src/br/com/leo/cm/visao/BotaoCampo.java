package br.com.leo.cm.visao;

import br.com.leo.cm.modelo.Campo;
import br.com.leo.cm.modelo.CampoEvent;
import br.com.leo.cm.modelo.CampoObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton implements CampoObserver, MouseListener {
    private final Color BG_PADRAO = new Color(184,184,184);
    private final Color BG_MARCADO = new Color(8,179,247);
    private final Color BG_EXPLOSAO= new Color(189,66,68);
    private final Color TEXTO_VERDE = new Color(0,100,0);
    private Campo campo;
    BotaoCampo(Campo campo){
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        campo.registrarObservador(this);
        addMouseListener(this);
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvent event){
        switch (event){
            case ABRIR -> aplicarEstiloAbrir();
            case MARCAR -> aplicarEstiloMarcar();
            case EXPLODIR -> aplicarEstiloExplodir();
            default -> aplicarEstiloPadrao();
            
        }

    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setText("");
    }

    private void aplicarEstiloExplodir() {
        System.out.println("Explodiu");
    }

    private void aplicarEstiloMarcar() {
        if(campo.isMarcado()){
            setBackground(BG_MARCADO);
            setText("X");
        }else{
            this.aplicarEstiloPadrao();
        }

    }

    private void aplicarEstiloAbrir() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createLineBorder(Color.gray));
        System.out.println(campo.getBombas());
        switch (campo.getBombas()){
            case 1 -> {
                setForeground(TEXTO_VERDE);
            }
            case 2 -> setForeground(Color.BLUE);
            case 3 -> setForeground(Color.YELLOW);
        }
        setText(!campo.isSeguro() ? campo.getBombas() + "" : "");
    }






    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            campo.abrir();

        }else if (mouseEvent.getButton() == 3){
            campo.alternarMarcacao();
        }
    }

    //ignore estes metodos, nao serao utilizados mas precisam estar aqui para que a implementacao seja feita
    public void mouseClicked(MouseEvent mouseEvent) {}
    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
}

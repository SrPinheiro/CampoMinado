package br.com.leo.cm.visao;

import br.com.leo.cm.excecao.ExplosaoException;
import br.com.leo.cm.excecao.SairException;
import br.com.leo.cm.modelo.Tabuleiro;

import java.util.Scanner;

public class MotorGraficoTerminal {
    private final Tabuleiro estadio;
    private final Scanner scan = new Scanner(System.in);
    int op;

    public MotorGraficoTerminal(Tabuleiro tb){
        this.estadio = tb;
        iniciarGame();
    }

    private void iniciarGame() {
        try{
            boolean time = true;

            while(time){
                ciclo();
                System.out.println("outra Partida? (S/n)");
                String resp = scan.nextLine();

                if("n".equalsIgnoreCase(resp)){
                    time = false;

                }else{
                    estadio.reiniciar();
                }
            }

        }catch(SairException e){
            System.out.println("---------------------------------------------");
            System.out.println("Game Finalizado!");
            System.out.println("---------------------------------------------");

        }finally {
            scan.close();
        }
    }

    private void ciclo() {
        try{
            while(!this.estadio.objetivoAlcancado()){
                System.out.println(this.estadio);

                String valor = capturarValores();
                String[] cordenadas = valor.split(",");
                System.out.println("1- Abrir 2- Marcar");
                op = scan.nextInt();
                scan.nextLine();
                try {
                    switch (op) {
                        case 1 -> this.estadio.abrir(Integer.parseInt(cordenadas[0]) - 1, Integer.parseInt(cordenadas[1]) - 1);
                        case 2 -> this.estadio.marcarCampo(Integer.parseInt(cordenadas[0]) - 1, Integer.parseInt(cordenadas[1]) - 1);
                    }
                }catch(NumberFormatException e ){
                    //
                }


            }
            System.out.println(this.estadio);
            System.out.println("\u001B[32m---------------------------------------------");
            System.out.println("Você ganhou!!");
            System.out.println("---------------------------------------------");
        }catch(ExplosaoException e){
            System.out.println(this.estadio);
            System.out.println("\u001B[31m---------------------------------------------");
            System.out.println("Você perdeu!!");
            System.out.println("---------------------------------------------");
        }
    }

    private String capturarValores(){
        System.out.println("\u001B[37mDigite as cordenadas (X:Y)");
        String opcao = scan.nextLine();
        if ("SAIR".equalsIgnoreCase(opcao)) {
            throw new SairException();
        }
        return opcao;
    }
}

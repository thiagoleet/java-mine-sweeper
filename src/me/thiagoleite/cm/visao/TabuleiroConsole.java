package me.thiagoleite.cm.visao;

import me.thiagoleite.cm.excecao.ExplosaoException;
import me.thiagoleite.cm.excecao.SairException;
import me.thiagoleite.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloDoJogo();

                System.out.println(Textos.CONTINUE);
                String resposta = entrada.nextLine();

                if (Textos.NO.equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }

            }
        } catch (SairException e) {
            System.out.println(Textos.GOODBYE);
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado(Textos.INSERT_COORDINATES);
                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();

                digitado = capturarValorDigitado(Textos.OPTIONS);
                
                if(Textos.OPTION_1.equals(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if(Textos.OPTION_2.equals(digitado)) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println(Textos.GAME_WON);
        } catch (ExplosaoException e) {
            System.out.println(Textos.GAME_OVER);
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if (Textos.EXIT.equalsIgnoreCase(digitado)) {
            throw new SairException();
        }

        return digitado;
    }
}

package me.thiagoleite.cm;

import me.thiagoleite.cm.modelo.Tabuleiro;
import me.thiagoleite.cm.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);
    }
}

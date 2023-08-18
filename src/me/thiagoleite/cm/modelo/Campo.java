package me.thiagoleite.cm.modelo;

import me.thiagoleite.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    // Flags
    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    // Coordenadas
    private final int linha;
    private final int coluna;

    // Interação com arredores
    private List<Campo> vizinhos = new ArrayList<Campo>();

    Campo(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }

    }

    boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                // recursividade
                vizinhos.forEach(vizinho -> vizinho.abrir());

                // Pode ser usado também por Method Reference
                // vizinhos.forEach(Campo::abrir);
            }

            return true;
        } else {
            return false;
        }

    }

    boolean vizinhancaSegura() {
        return vizinhos.stream()
                .noneMatch(vizinho -> vizinho.minado);
    }
}

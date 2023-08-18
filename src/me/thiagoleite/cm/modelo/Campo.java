package me.thiagoleite.cm.modelo;

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
}

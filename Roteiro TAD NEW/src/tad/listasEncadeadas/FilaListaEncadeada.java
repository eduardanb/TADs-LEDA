package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

public class FilaListaEncadeada implements FilaIF<NodoListaEncadeada<Integer>> {
    
    private ListaEncadeadaIF<Integer> fila;

    public FilaListaEncadeada() {
        this.fila = new ListaEncadeadaImpl<>();
    }

    @Override
    public void enfileirar(NodoListaEncadeada<Integer> item) throws FilaCheiaException {
        if (item == null || item.getChave() == null) {
            throw new IllegalArgumentException("Item não pode ser null");
        }
        this.fila.insert(item.getChave());
    }

    @Override
    public NodoListaEncadeada<Integer> desenfileirar() throws FilaVaziaException {
        if (this.fila.isEmpty()) {
            throw new FilaVaziaException();
        }
        NodoListaEncadeada<Integer> cabeca = ((ListaEncadeadaImpl<Integer>) this.fila).verificarCabeca();
        if (cabeca == null) throw new FilaVaziaException();
        NodoListaEncadeada<Integer> removido = this.fila.remove(cabeca.getChave());
        if (removido == null) throw new FilaVaziaException();
        return removido;
    }

    @Override
    public NodoListaEncadeada<Integer> verificarCauda() {
        if (this.fila.isEmpty()) {
            return null;
        }
        return ((ListaEncadeadaImpl<Integer>) this.fila).verificarCauda();
    }

    @Override
    public NodoListaEncadeada<Integer> verificarCabeca() {
        if (this.fila.isEmpty()) {
            return null;
        }
        return ((ListaEncadeadaImpl<Integer>) this.fila).verificarCabeca();
    }

    @Override
    public boolean isEmpty() {
        return this.fila.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int capacidade() {
        return -1;  // Sem limite de capacidade
    }

    @Override
    public int tamanho() {
        return this.fila.size();
    }
}

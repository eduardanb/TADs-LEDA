package tad.listasEncadeadas;

import java.lang.reflect.Array;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

    private NodoListaDuplamenteEncadeada<T> cabeca;
    private NodoListaDuplamenteEncadeada<T> cauda;

    public ListaDuplamenteEncadeadaImpl() {
        cabeca = new NodoListaDuplamenteEncadeada<>();
        cauda = new NodoListaDuplamenteEncadeada<>();
        cabeca.setProximo(cauda);
        cabeca.setAnterior(null);
        cauda.setAnterior(cabeca);
        cauda.setProximo(null);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo() == cauda;
    }

    @Override
    public int size() {
        int count = 0;
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            count++;
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return count;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> search(T chave) {
        if (chave == null) return null;
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            if (chave.equals(atual.getChave())) {
                return atual;
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        if (chave == null) throw new IllegalArgumentException("Elemento não pode ser null");

        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(chave);
        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();

        novo.setProximo(cauda);
        novo.setAnterior(ultimo);
        ultimo.setProximo(novo);
        cauda.setAnterior(novo);
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> remove(T chave) {
        if (isEmpty() || chave == null) return null;
        NodoListaDuplamenteEncadeada<T> atual = search(chave);
        if (atual != null) {
            atual.getAnterior().setProximo(atual.getProximo());
            ((NodoListaDuplamenteEncadeada<T>) atual.getProximo()).setAnterior(atual.getAnterior());
            atual.setProximo(null);
            atual.setAnterior(null);
            return atual;
        }
        return null;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (atual != cauda) {
            sb.append(atual.getChave());
            if (atual.getProximo() != cauda) {
                sb.append(", ");
            }
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return sb.toString();
    }

    @Override
    public String imprimeInverso() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
        while (atual != cabeca) {
            sb.append(atual.getChave());
            if (atual.getAnterior() != cabeca) {
                sb.append(", ");
            }
            atual = atual.getAnterior();
        }
        return sb.toString();
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> sucessor(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = search(chave);
        if (atual != null && atual.getProximo() != cauda) {
            return (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> predecessor(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = search(chave);
        if (atual != null && atual.getAnterior() != cabeca) {
            return atual.getAnterior();
        }
        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        int size = size();
        if (size == 0) return null;
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, size);
        NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        int i = 0;
        while (atual != cauda) {
            array[i++] = atual.getChave();
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }
        return array;
    }

    @Override
    public void inserePrimeiro(T elemento) {
        if (elemento == null) throw new IllegalArgumentException("Elemento não pode ser null");
        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(elemento);
        NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();

        novo.setProximo(primeiro);
        novo.setAnterior(cabeca);
        cabeca.setProximo(novo);
        primeiro.setAnterior(novo);
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removeUltimo() {
        if (isEmpty()) return null;
        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
        NodoListaDuplamenteEncadeada<T> penultimo = ultimo.getAnterior();

        penultimo.setProximo(cauda);
        cauda.setAnterior(penultimo);

        ultimo.setAnterior(null);
        ultimo.setProximo(null);

        return ultimo;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
        if (isEmpty()) return null;
        NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        NodoListaDuplamenteEncadeada<T> segundo = (NodoListaDuplamenteEncadeada<T>) primeiro.getProximo();

        cabeca.setProximo(segundo);
        segundo.setAnterior(cabeca);

        primeiro.setAnterior(null);
        primeiro.setProximo(null);

        return primeiro;
    }

    @Override
    public void insert(T chave, int index) {
        if (chave == null) throw new IllegalArgumentException("Elemento não pode ser null");
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        NodoListaDuplamenteEncadeada<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        }

        NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
        NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(chave);

        novo.setAnterior(atual);
        novo.setProximo(proximo);
        atual.setProximo(novo);
        proximo.setAnterior(novo);
    }
}

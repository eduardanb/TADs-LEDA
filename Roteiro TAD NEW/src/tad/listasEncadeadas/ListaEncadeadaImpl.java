package tad.listasEncadeadas;

import java.lang.reflect.Array;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    private NodoListaEncadeada<T> cabeca = null;

    public ListaEncadeadaImpl() {
        cabeca = null;
    }

    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    @Override
    public int size() {
        int count = 0;
        NodoListaEncadeada<T> atual = cabeca;
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> atual = cabeca;
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        if (chave == null) throw new IllegalArgumentException("Elemento não pode ser null");
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            NodoListaEncadeada<T> atual = cabeca;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) {
        if (cabeca == null) return null;
        NodoListaEncadeada<T> atual = cabeca;
        NodoListaEncadeada<T> anterior = null;
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                if (anterior == null) {
                    cabeca = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                atual.setProximo(null);
                return atual;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        int tam = size();
        if (tam == 0) return null;
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, tam);
        NodoListaEncadeada<T> atual = cabeca;
        int i = 0;
        while (atual != null) {
            array[i++] = atual.getChave();
            atual = atual.getProximo();
        }
        return array;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder sb = new StringBuilder();
        NodoListaEncadeada<T> atual = cabeca;
        while (atual != null) {
            sb.append(atual.getChave());
            if (atual.getProximo() != null) sb.append(", ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    @Override
    public String imprimeInverso() {
        return imprimeInversoRec(cabeca).toString();
    }

    private StringBuilder imprimeInversoRec(NodoListaEncadeada<T> no) {
        if (no == null) return new StringBuilder();
        StringBuilder sb = imprimeInversoRec(no.getProximo());
        if (sb.length() > 0) sb.append(", ");
        sb.append(no.getChave());
        return sb;
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaEncadeada<T> atual = cabeca;
        while (atual != null && atual.getProximo() != null) {
            if (atual.getChave().equals(chave)) {
                return atual.getProximo();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaEncadeada<T> atual = cabeca;
        NodoListaEncadeada<T> anterior = null;
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                return anterior;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave, int index) {
        if (chave == null) throw new IllegalArgumentException("Elemento não pode ser null");
        if (index < 0) throw new IndexOutOfBoundsException();
        NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
        if (index == 0) {
            novo.setProximo(cabeca);
            cabeca = novo;
            return;
        }
        NodoListaEncadeada<T> atual = cabeca;
        int i = 0;
        while (atual != null && i < index - 1) {
            atual = atual.getProximo();
            i++;
        }
        if (atual == null) throw new IndexOutOfBoundsException();
        novo.setProximo(atual.getProximo());
        atual.setProximo(novo);
    }

    // Métodos auxiliares para fila
    // Retorna o primeiro elemento da lista (cabeça)
    public NodoListaEncadeada<T> verificarCabeca() {
        return cabeca;
    }

    // Retorna o último elemento da lista (cauda)
    public NodoListaEncadeada<T> verificarCauda() {
        if (cabeca == null) return null;
        NodoListaEncadeada<T> atual = cabeca;
        while (atual.getProximo() != null) {
            atual = atual.getProximo();
        }
        return atual;
    }
}

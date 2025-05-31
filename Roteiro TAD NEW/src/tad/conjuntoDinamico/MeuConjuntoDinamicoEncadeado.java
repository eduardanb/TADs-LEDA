package tad.conjuntoDinamico;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private class Node {
        Integer valor;
        Node prox;

        Node(Integer valor) {
            this.valor = valor;
            this.prox = null;
        }
    }

    private Node head;
    private int tamanho;

    public MeuConjuntoDinamicoEncadeado() {
        head = null;
        tamanho = 0;
    }

    @Override
    public void inserir(Integer item) {
        Node novo = new Node(item);
        novo.prox = head;
        head = novo;
        tamanho++;
    }

    @Override
    public Integer remover(Integer item) {
        if (head == null) throw new RuntimeException("Conjunto vazio");
        Node atual = head, anterior = null;
        while (atual != null) {
            if (atual.valor.equals(item)) {
                if (anterior == null) {
                    head = atual.prox;
                } else {
                    anterior.prox = atual.prox;
                }
                tamanho--;
                return atual.valor;
            }
            anterior = atual;
            atual = atual.prox;
        }
        throw new RuntimeException("Elemento não encontrado");
    }

    @Override
    public Integer predecessor(Integer item) {
        if (head == null) throw new RuntimeException("Conjunto vazio");
        Node atual = head, anterior = null;
        while (atual != null) {
            if (atual.valor.equals(item)) {
                return anterior == null ? null : anterior.valor;
            }
            anterior = atual;
            atual = atual.prox;
        }
        throw new RuntimeException("Elemento não encontrado");
    }

    @Override
    public Integer sucessor(Integer item) {
        if (head == null) throw new RuntimeException("Conjunto vazio");
        Node atual = head;
        while (atual != null) {
            if (atual.valor.equals(item)) {
                return atual.prox == null ? null : atual.prox.valor;
            }
            atual = atual.prox;
        }
        throw new RuntimeException("Elemento não encontrado");
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public Integer buscar(Integer item) {
        Node atual = head;
        while (atual != null) {
            if (atual.valor.equals(item)) return atual.valor;
            atual = atual.prox;
        }
        return null;
    }

    @Override
    public Integer minimum() {
        if (head == null) throw new RuntimeException("Conjunto vazio");
        Node atual = head;
        int min = atual.valor;
        while (atual != null) {
            if (atual.valor < min) min = atual.valor;
            atual = atual.prox;
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if (head == null) throw new RuntimeException("Conjunto vazio");
        Node atual = head;
        int max = atual.valor;
        while (atual != null) {
            if (atual.valor > max) max = atual.valor;
            atual = atual.prox;
        }
        return max;
    }
}

package tad.pilha;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

    private class Node {
        Integer valor;
        Node prox;

        Node(Integer valor) {
            this.valor = valor;
            this.prox = null;
        }
    }

    private Node topo = null;

    @Override
    public void empilhar(Integer item) {
        Node novo = new Node(item);
        novo.prox = topo;
        topo = novo;
    }

    @Override
    public Integer desempilhar() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }
        Integer valor = topo.valor;
        topo = topo.prox;
        return valor;
    }

    @Override
    public Integer topo() {
        if (isEmpty()) {
            return null;
        }
        return topo.valor;
    }

    @Override
    public PilhaIF<Integer> multitop(int k) {
        MinhaPilhaEncadeada nova = new MinhaPilhaEncadeada();
        Node atual = topo;
        int count = 0;
        while (atual != null && count < k) {
            nova.empilhar(atual.valor);
            atual = atual.prox;
            count++;
        }
        return nova;
    }

    @Override
    public boolean isEmpty() {
        return topo == null;
    }

    @Override
    public int capacidade() {
        // For a linked stack, capacity is typically considered unlimited (Integer.MAX_VALUE)
        return Integer.MAX_VALUE;
    }

    @Override
    public int tamanho() {
        int count = 0;
        Node atual = topo;
        while (atual != null) {
            count++;
            atual = atual.prox;
        }
        return count;
    }
}
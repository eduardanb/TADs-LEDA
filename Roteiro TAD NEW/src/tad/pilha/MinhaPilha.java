package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
    
    private int tamanho = 10;
    private Integer[] meusDados;
    private int topo = -1;

    public MinhaPilha(int tamanho) {
        this.tamanho = tamanho;
        this.meusDados = new Integer[tamanho];
    }
    
    public MinhaPilha() {
        this.meusDados = new Integer[tamanho];
    }

    @Override
    public void empilhar(Integer item) throws PilhaCheiaException {
        if (isFull()) {
            throw new PilhaCheiaException();
        }
        meusDados[++topo] = item;
    }

    @Override
    public Integer desempilhar() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException();
        }
        Integer valor = meusDados[topo];
        meusDados[topo--] = null;
        return valor;
    }

    @Override
    public Integer topo() {
        if (isEmpty()) {
            return null;
        }
        return meusDados[topo];
    }

    @Override
    public PilhaIF<Integer> multitop(int k) {
        if (k > topo + 1) throw new RuntimeException(new PilhaVaziaException());
        MinhaPilha nova = new MinhaPilha(k);
        // Empilha do fundo para o topo para manter a ordem correta
        for (int i = topo - k + 1; i <= topo; i++) {
            try {
                nova.empilhar(meusDados[i]);
            } catch (PilhaCheiaException e) {
                throw new RuntimeException(e);
            }
        }
        return nova;
    }

    @Override
    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == tamanho - 1;
    }

    // Retorna a capacidade máxima da pilha
    @Override
    public int capacidade() {
        return tamanho;
    }

    // Retorna o número de elementos atualmente na pilha
    @Override
    public int tamanho() {
        return topo + 1;
    }

    // Opcional: facilita comparação em testes
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MinhaPilha)) return false;
        MinhaPilha outra = (MinhaPilha) obj;
        if (this.topo != outra.topo) return false;
        for (int i = 0; i <= topo; i++) {
            if (!this.meusDados[i].equals(outra.meusDados[i])) return false;
        }
        return true;
    }
}
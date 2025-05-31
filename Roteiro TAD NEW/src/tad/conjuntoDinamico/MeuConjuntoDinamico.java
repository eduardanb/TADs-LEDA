package tad.conjuntoDinamico;

import java.util.NoSuchElementException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados = new Integer[10];
    private int posInsercao = 0;

    @Override
    public void inserir(Integer item) {
        if (item == null) return;
        if (posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao++] = item;
    }

    private Integer[] aumentarArray() {
        int novoTamanho = meusDados.length * 2;
        Integer[] arrayMaior = new Integer[novoTamanho];
        System.arraycopy(meusDados, 0, arrayMaior, 0, meusDados.length);
        return arrayMaior;
    }

    @Override
    public Integer remover(Integer item) {
        if (item == null) {
            throw new NoSuchElementException("Elemento não pode ser nulo");
        }
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removido = meusDados[i];
                for (int j = i; j < posInsercao - 1; j++) {
                    meusDados[j] = meusDados[j + 1];
                }
                meusDados[--posInsercao] = null;
                return removido;
            }
        }
        throw new NoSuchElementException("Elemento não encontrado");
    }

    @Override
    public Integer predecessor(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (posInsercao == 0) throw new NoSuchElementException("Conjunto vazio");
        boolean existe = false;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                existe = true;
                break;
            }
        }
        if (!existe) throw new NoSuchElementException("Elemento não existe");
        Integer pred = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i] < item) {
                if (pred == null || meusDados[i] > pred) {
                    pred = meusDados[i];
                }
            }
        }
        if (pred == null) throw new NoSuchElementException("Sem predecessor");
        return pred;
    }

    @Override
    public Integer sucessor(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (posInsercao == 0) throw new NoSuchElementException("Conjunto vazio");
        Integer suc = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i] > item) {
                if (suc == null || meusDados[i] < suc) {
                    suc = meusDados[i];
                }
            }
        }
        if (suc == null) throw new NoSuchElementException("Sem sucessor");
        return suc;
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) {
        if (item == null) {
            throw new NoSuchElementException("Elemento não encontrado");
        }
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        throw new NoSuchElementException("Elemento não encontrado");
    }

    @Override
    public Integer minimum() {
        if (posInsercao == 0) throw new NoSuchElementException("Conjunto vazio");
        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if (posInsercao == 0) throw new NoSuchElementException("Conjunto vazio");
        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max) {
                max = meusDados[i];
            }
        }
        return max;
    }
}
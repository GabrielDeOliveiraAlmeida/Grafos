/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.EstruturaDados;

import grafostp2.EstruturaAuxiliares.Aresta;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Heap {

    private ArrayList<Aresta> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public Aresta get(int i) {
        return heap.get(i);
    }

    public int size() {
        return heap.size();
    }

    public void inserir(Aresta ver) {
        heap.add(ver);
        //System.out.print(heap.size());
        bottomup(heap.size() - 1);
    }

    private void bottomup(int k) {
        if (k == 0) {
            return;
        }
        while (k > 0 && menor(k / 2, k)) {
            troca(k, k / 2);
            k = k / 2;
        }
    }

    private void troca(int a, int b) {
        Aresta aux;
        aux = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, aux);
    }

    private boolean menor(int a, int b) {
        if (heap.get(a).getPeso() > heap.get(b).getPeso()) {
            return true;
        } else {
            return false;
        }
    }

    private void topdown(int k) {
        int j;
        int tam = heap.size() - 1;
        while (2 * k <= tam) {
            j = 2 * k;
            if (j < tam && menor(j, j + 1)) {
                j++;
            }
            if (!(menor(k, j))) {
                break;
            }
            troca(k, j);
            k = j;
        }
    }

    public Aresta remover() {
        int tam = heap.size() - 1;
        troca(0, tam);
        Aresta aux = heap.get(tam);
        heap.remove(tam);
        topdown(0);
        return aux;
    }

    public void imprimir() {
        int cont;
        int tam = heap.size();
        for (cont = 0; cont < tam; cont++) {
            System.out.printf("%d %d %d\n", heap.get(cont).getVer1(), heap.get(cont).getVer2(),
                    heap.get(cont).getPeso());
        }
    }

    public Boolean verificarHeap(Aresta ar) {
        int tam = heap.size();
        Aresta aux;
        for (int i = 0; i < tam; i++) {
            aux = heap.get(i);
            if (aux.getPeso() == ar.getPeso() && aux.getVer1() == ar.getVer2()
                    && aux.getVer2() == ar.getVer1()) {
                return true;
            }
        }
        return false;
    }


}

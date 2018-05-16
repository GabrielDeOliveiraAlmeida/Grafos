/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.EstruturaDados;

import grafostp2.EstruturaAuxiliares.VerticeL;

/**
 *
 * @author gabriel
 */
public class Fila {

    private No inicio;
    private No fim;

    public Fila() {
        this.inicio = null;
        this.fim = null;
    }

    private class No {

        private VerticeL ver;
        private No prox;

        public No() {

        }

        public No(VerticeL ver) {
            this.ver = ver;
            this.prox = null;
        }

        public No getProx() {
            return prox;
        }

        public VerticeL getVer() {
            return ver;
        }

        public void setProx(No prox) {
            this.prox = prox;
        }

    }

    public void inserirFila(Fila fila, VerticeL ver) {
        No aux = new No(ver);
        if (fila.inicio == null) {
            fila.inicio = aux;
            fila.fim = fila.inicio;
            return;
        }
        fila.fim.setProx(aux);
        fila.fim = aux;
        return;
    }

    public int removerFila(Fila fila) {
        if (fila.inicio == null) {
            return -1;
        }
        No aux;// = new No();
        int re;
        aux = fila.inicio;
        fila.inicio = aux.getProx();
        re = aux.getVer().getPos();
        aux = null;
        return re;
    }

    public void imprimirFila(Fila fila) {
        if (fila.inicio == null) {
            return;
        }
        No aux;
        aux = fila.inicio;
        while (aux != null) {
            System.out.printf("%c ", aux.getVer().getCor());
            aux = aux.getProx();
        }
    }

    public boolean vazia(Fila fila) {
        if (fila.inicio == null) {
            return true;
        } else {
            return false;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.EstruturaDados;

import grafostp2.EstruturaAuxiliares.Vertice;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Lista {

    private ArrayList<Vertice> lista;

    public Lista() {
        this.lista = new ArrayList<>();
    }

    public void add(Vertice num) {
        lista.add(num);
    }

    public int getVer(int i) {
        return lista.get(i).getVer();
    }

    public int getPeso(int i) {
        return lista.get(i).getPeso();
    }

    public void serLista(ArrayList<Vertice> lista){
        this.lista = lista;
    }
    public String imprimir() {
        String impressao = " ";
        int tam = lista.size();
        for (int i = 0; i < tam; i++) {
            impressao += String.valueOf(lista.get(i).getVer()) + "("
                    + String.valueOf(lista.get(i).getPeso()) + ") > ";
        }
        impressao += "/";
        return impressao;

    }

    public int size() {
        return lista.size();
    }

}

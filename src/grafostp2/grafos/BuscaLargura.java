/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.VerticeL;
import grafostp2.EstruturaDados.Fila;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class BuscaLargura {
    
    public static void buscaLargura(VerticeL[] verL, int inicial, Boolean rep) {
        String impressao = "Vértice (Distância, Ancestral)\n";

        Fila fila = new Fila();

        for (int i = 0; i < Grafos.tam; i++) {
            verL[i] = new VerticeL('b', -1, -1, i, 0);
        }
        if (rep) {
            buscaLarguraLista(verL, inicial, fila);
        } else {
            buscaLarguraMatriz(verL, inicial, fila);
        }

    }
    
    private static void buscaLarguraLista(VerticeL[] verL, int inicial, Fila fila) {
        int k, tamLista, indice;
        if (verL[inicial].getCor() == 'b') {
            verL[inicial].setCor('c');
            verL[inicial].setDist(0);
            verL[inicial].setPai(-1);
            fila.inserirFila(fila, verL[inicial]);
            while (!fila.vazia(fila)) {
                k = fila.removerFila(fila);
                tamLista = Grafos.lista[k].size();
                for (int i = 0; i < tamLista; i++) {
                    indice = Grafos.lista[k].getVer(i);
                    if (verL[indice].getCor() == 'b') {
                        verL[indice].setCor('c');
                        verL[indice].incDist(verL[k].getDist());
                        verL[indice].setPai(k);
                        fila.inserirFila(fila, verL[indice]);
                    }
                }
                verL[k].setCor('p');

            }
        }
    }

    private static void buscaLarguraMatriz(VerticeL[] verL, int inicial, Fila fila) {
        int k, tamLista, indice;
        if (verL[inicial].getCor() == 'b') {
            verL[inicial].setCor('c');
            verL[inicial].setDist(0);
            verL[inicial].setPai(-1);
            fila.inserirFila(fila, verL[inicial]);
            while (!fila.vazia(fila)) {
                k = fila.removerFila(fila);
                for (indice = 0; indice < Grafos.tam; indice++) {
                    if (Grafos.matriz[k][indice] != null) {
                        if (verL[indice].getCor() == 'b') {
                            verL[indice].setCor('c');
                            verL[indice].incDist(verL[k].getDist());
                            verL[indice].setPai(k);
                            fila.inserirFila(fila, verL[indice]);
                        }
                    }
                }
                verL[k].setCor('p');
            }
        }
    }

    public static boolean caminhoLargura(VerticeL[] ver, int inicio, int fim, ArrayList<String> cami,
            Boolean troca) {

        boolean caminho = false;
        if (!troca) {
            cami.add(String.valueOf(inicio));
        }
        if ((ver[inicio].getPai() == fim) || (inicio == ver[fim].getPai())) {
            if (troca) {
                cami.add(String.valueOf(inicio));
            } else {
                cami.add(String.valueOf(fim));
            }
            return true;
        } else if (ver[inicio] == ver[fim]) {
            return true;
        } else if (ver[inicio].getPai() == -1 && ver[fim].getPai() == -1) {
            return false;
        } else {
            if (ver[inicio].getPai() == -1) {
                troca = true;
                caminho = caminhoLargura(ver, fim, inicio, cami, troca);
            } else {
                caminho = caminhoLargura(ver, ver[inicio].getPai(), fim, cami, troca);
                if (troca) {
                    cami.add(String.valueOf(inicio));
                }
            }
            //return caminho;
        }

        return caminho;

    }
    
    
    public String buscaLarguraString(int inicial, Boolean rep) {
        VerticeL[] verL = new VerticeL[Grafos.tam];
        buscaLargura(verL, inicial, rep);
        String impressao = "Raíz Busca = " + String.valueOf(inicial);
        impressao += "\nVertice (Distância, Ancestral)\n";
        for (int i = 0; i < Grafos.tam; i++) {
            if (verL[i].getDist() == -1) {
                impressao += String.valueOf(i) + " (" + "Não Alcançável, "
                        + String.valueOf(verL[i].getPai()) + ")\n";
            } else {
                impressao += String.valueOf(i) + " (" + String.valueOf(verL[i].getDist())
                        + ", " + String.valueOf(verL[i].getPai()) + ")\n";
            }
        }
        impressao += "\n-1 = NULL";
        return impressao;
    }

}

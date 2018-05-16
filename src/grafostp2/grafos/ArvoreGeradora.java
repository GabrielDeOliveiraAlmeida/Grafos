/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.Vertice;
import grafostp2.EstruturaAuxiliares.Aresta;
import grafostp2.EstruturaAuxiliares.VerticeAGM;
import grafostp2.EstruturaDados.Heap;
import grafostp2.EstruturaDados.Lista;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class ArvoreGeradora {
    
    public String agmKruskal(Boolean rep) {
        Heap arestas = criarArestas(rep);
        String impressao = "";
        if (rep) {
            impressao = agmKruskalLista(arestas);
        } else {
            impressao = agmKruskalMatriz(arestas);
        }
        return impressao;
    }

    private String agmKruskalMatriz(Heap arestas) {
        int i, j;
        int[] comp = new int[Grafos.tam];
        Integer[][] arv = new Integer[Grafos.tam][Grafos.tam];
        for (i = 0; i < Grafos.tam; i++) {
            comp[i] = i;
            for (j = 0; j < Grafos.tam; j++) {
                arv[i][j] = null;
            }
        }
        Aresta ar;
        int ver1, ver2, peso;
        while (arestas.size() != 0) {
            ar = arestas.remover();
            ver1 = ar.getVer1();
            ver2 = ar.getVer2();
            peso = ar.getPeso();
            if (comp[ver1] != comp[ver2]) {
                for (i = 0; i < Grafos.tam; i++) {
                    if (comp[i] == ver2) {
                        comp[i] = comp[ver1];
                    }
                }
                arv[ver1][ver2] = peso;
                arv[ver2][ver1] = peso;
            }
        }
        String impressao = "";
        for (i = 0; i < Grafos.tam; i++) {
            for (j = 0; j < Grafos.tam; j++) {
                impressao += String.format("%d\t", arv[i][j]);
            }
            impressao += "\n";
        }
        return impressao;
    }

    private String agmKruskalLista(Heap arestas) {

        int i;
        Lista[] arv = new Lista[Grafos.tam];

        int comp[] = new int[Grafos.tam];
        for (i = 0; i < Grafos.tam; i++) {
            arv[i] = new Lista();
            comp[i] = i;

        }
        Vertice ver;
        Aresta ar;
        arestas.imprimir();
        int ver1, ver2, peso, compaux;
        while (arestas.size() != 0) {
            ar = arestas.remover();
            ver1 = ar.getVer1();
            ver2 = ar.getVer2();
            peso = ar.getPeso();
            
            if (comp[ver1] != comp[ver2]) {
                compaux = comp[ver2];
                for (i = 0; i < Grafos.tam; i++) {
                    if (comp[i] == compaux) {
                        comp[i] = comp[ver1];
                    }
                }
                ver = new Vertice(ver2, peso);
                arv[ver1].add(ver);
                ver = new Vertice(ver1, peso);
                arv[ver2].add(ver);

            }
        }
        String impressao = "";
        for (i = 0; i < Grafos.tam; i++) {
            impressao += String.valueOf(i) + "-> " + arv[i].imprimir() + "\n";
        }
        return impressao;
    }

    private Heap criarArestas(Boolean rep) {
        int i, j, taml;
        Heap arestas = new Heap();
        Aresta aresta;
        if (rep) {
            for (i = 0; i < Grafos.tam; i++) {
                taml = Grafos.lista[i].size();
                for (j = 0; j < taml; j++) {

                    aresta = new Aresta(i, Grafos.lista[i].getVer(j), Grafos.lista[i].getPeso(j));
                    if (Grafos.or == 1) {
                        arestas.inserir(aresta);
                    } else if (!arestas.verificarHeap(aresta)) {
                        arestas.inserir(aresta);
                    }
                }
            }
        } else {
            for (i = 0; i < Grafos.tam; i++) {
                for (j = 0; j < Grafos.tam; j++) {
                    if (Grafos.matriz[i][j] != null) {
                        aresta = new Aresta(i, j, Grafos.matriz[i][j]);
                        if (Grafos.or == 1) {
                            arestas.inserir(aresta);
                        } else if (!arestas.verificarHeap(aresta)) {
                            arestas.inserir(aresta);
                        }
                    }

                }
            }
        }
        return arestas;
    }

    public String agmPrim(Boolean rep) {
        if (rep) {
            return agmPrimLista();
        } else {
            return agmPrimMatriz();
        }
    }

    private String agmPrimLista() {
        ArrayList<VerticeAGM> vetor = new ArrayList();
        Lista[] list = new Lista[Grafos.tam];

        VerticeAGM u;
        VerticeAGM aux;

        for (int i = 0; i < Grafos.tam; i++) {
            aux = new VerticeAGM(-1, Grafos.INF, i, 'x');
            vetor.add(aux);
            list[i] = new Lista();
        }

        vetor.get(0).setPeso(0);

        int i, j, tamanho;
        int ancestral = -1;

        while (Grafos.qVazio(vetor)) {
            u = Grafos.extrairMinimo(vetor);

            Grafos.unir(list, u);
            tamanho = Grafos.lista[u.getPos()].size();
            ancestral = u.getPos();
            for (i = 0; i < tamanho; i++) {
                if (Grafos.lista[ancestral].getPeso(i) < vetor.get(Grafos.lista[ancestral].getVer(i)).getPeso()
                        && vetor.get(Grafos.lista[ancestral].getVer(i)).getQ() == 'x') {
                    vetor.get(Grafos.lista[ancestral].getVer(i)).setPeso(Grafos.lista[ancestral].getPeso(i));
                    vetor.get(Grafos.lista[ancestral].getVer(i)).setPai(ancestral);
                }
            }
        }
        String impressao = "";
        for (i = 0; i < Grafos.tam; i++) {
            impressao += String.valueOf(i) + "-> " + list[i].imprimir() + "\n";
        }
        return impressao;

    }

    private String agmPrimMatriz() {
        ArrayList<VerticeAGM> vetor = new ArrayList();
        Integer[][] matrix = new Integer[Grafos.tam][Grafos.tam];
        int i, j;
        VerticeAGM aux;
        for (i = 0; i < Grafos.tam; i++) {
            for (j = 0; j < Grafos.tam; j++) {
                matrix[i][j] = null;
            }
        }

        for (i = 0; i < Grafos.tam; i++) {
            aux = new VerticeAGM(-1, Grafos.INF, i, 'x');
            vetor.add(aux);
        }

        vetor.get(0).setPeso(0);

        VerticeAGM u;

        int ancestral = -1;

        while (Grafos.qVazio(vetor)) {
            u = Grafos.extrairMinimo(vetor);

            Grafos.unirMatriz(matrix, u);
            ancestral = u.getPos();
            for (j = 0; j < Grafos.tam; j++) {
                if (Grafos.matriz[ancestral][j] != null) {
                    if (Grafos.matriz[ancestral][j] < vetor.get(j).getPeso()
                            && vetor.get(j).getQ() == 'x') {
                        vetor.get(j).setPeso(Grafos.matriz[ancestral][j]);
                        vetor.get(j).setPai(ancestral);
                    }
                }
            }
        }
        String impressao = "";
        for (i = 0; i < Grafos.tam; i++) {
            for (j = 0; j < Grafos.tam; j++) {
                impressao += String.format("%d\t", matrix[i][j]);
            }
            impressao += "\n";
        }
        return impressao;
    }



}

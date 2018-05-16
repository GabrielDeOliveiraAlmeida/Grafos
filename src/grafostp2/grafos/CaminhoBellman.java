/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.Aresta;
import grafostp2.EstruturaAuxiliares.VerticeAGM;
import grafostp2.EstruturaDados.Heap;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author gabriel
 */
public class CaminhoBellman {
   public String bellman(Boolean rep, int inicio) {
        ArrayList<VerticeAGM> vetor = new ArrayList();
        VerticeAGM aux;

        for (int i = 0; i < Grafos.tam; i++) {
            aux = new VerticeAGM(-1, Grafos.INF, i);
            vetor.add(aux);
        }

        vetor.get(inicio).setPeso(0);

        Heap arestas = criarArestasMin(rep);
        int u, v;
        Aresta arest;
        int i, j, tamanho = arestas.size();

        for (j = 0; j < Grafos.tam; j++) {
            for (i = 0; i < tamanho; i++) {
                arest = arestas.get(i);
                u = arest.getVer1();
                v = arest.getVer2();
                if (arest.getPeso() + vetor.get(u).getPeso()
                        < vetor.get(v).getPeso()) {
                    vetor.get(v).setPeso(vetor.get(u).getPeso() + arest.getPeso());
                    vetor.get(v).setPai(u);
                }
            }
        }
        for(i=0; i<tamanho; i++){
            arest = arestas.get(i);
            u = arest.getVer1();
            v = arest.getVer2();
            if (arest.getPeso() + vetor.get(u).getPeso()
                        < vetor.get(v).getPeso()) {
                JOptionPane.showMessageDialog(null, "Grafo ciclico, não houve formação de caminho","ERRO", ERROR_MESSAGE);
                return "";
                
            }
        }
    for(i=0 ; i<vetor.size(); i++){
        System.out.printf("%d(%d) %d\n", vetor.get(i).getPos(), vetor.get(i).getPeso(), vetor.get(i).getPai());
    }
        return Grafos.recuperarCaminho(vetor, inicio);
    }

    private Heap criarArestasMin(Boolean rep) {
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
                    } else {
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
                        } else {
                            arestas.inserir(aresta);
                        }
                    }

                }
            }
        }
        return arestas;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.VerticeAGM;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class CaminhoDijistra {
    
    
    public String dijkstra(Boolean rep, int inicio) {

        ArrayList<VerticeAGM> vetor = new ArrayList();
        VerticeAGM aux;
        for (int i = 0; i < Grafos.tam; i++) {
            aux = new VerticeAGM(-1, Grafos.INF, i, 'x');
            vetor.add(aux);
        }

        vetor.get(inicio).setPeso(0);

        VerticeAGM u;

        int i, j, tamanho;
        int ancestral;
        if (rep) {
            while (Grafos.qVazio(vetor)) {
                u = Grafos.extrairMinimo(vetor);
                if (u == null) {
                    break;
                }
                tamanho = Grafos.lista[u.getPos()].size();
                ancestral = u.getPos();
                for (i = 0; i < tamanho; i++) {
                    if (u.getPeso()
                            + Grafos.lista[ancestral].getPeso(i)
                            < vetor.get(Grafos.lista[ancestral].getVer(i)).getPeso()
                            && vetor.get(Grafos.lista[ancestral].getVer(i)).getQ() == 'x') {
                        vetor.get(Grafos.lista[ancestral].getVer(i)).setPeso(Grafos.lista[ancestral].getPeso(i)
                                + u.getPeso());
                        vetor.get(Grafos.lista[ancestral].getVer(i)).setPai(ancestral);
                    }
                }
            }
        } else {
            while (Grafos.qVazio(vetor)) {
                u = Grafos.extrairMinimo(vetor);
                if (u == null) {
                    break;
                }
                ancestral = u.getPos();
                for (j = 0; j < Grafos.tam; j++) {
                    if (Grafos.matriz[ancestral][j] != null) {
                        if (u.getPeso()
                                + Grafos.matriz[ancestral][j]
                                < vetor.get(j).getPeso()
                                && vetor.get(j).getQ() == 'x') {
                            vetor.get(j).setPeso(Grafos.matriz[ancestral][j]
                                    + u.getPeso());
                            vetor.get(j).setPai(ancestral);
                        }
                    }
                }

            }
        }
        return Grafos.recuperarCaminho(vetor, inicio);
    }
    

}

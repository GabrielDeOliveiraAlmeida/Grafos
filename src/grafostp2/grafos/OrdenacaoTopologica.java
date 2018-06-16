/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.Vertice;
import grafostp2.EstruturaAuxiliares.VerticeP;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gabriel
 */
public class OrdenacaoTopologica {
    
    public ArrayList<Vertice> ordTopologica(Boolean rep, int inicio){
        int[] pai = new int[Grafos.tam];
        VerticeP[] verP = new  VerticeP[Grafos.tam];
        
        BuscaProfundidade.caminhoProfundidade(verP, inicio, inicio, rep,pai);

        ArrayList<Vertice> ordenar = new ArrayList<>();
            
        for(int i=0; i<Grafos.tam; i++){
            ordenar.add(new Vertice(i,verP[i].getFim()));
        }
        
        Collections.sort(ordenar);
        return ordenar;
    }
}

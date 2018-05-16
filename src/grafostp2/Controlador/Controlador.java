/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.Controlador;

import grafos.Desenho.Graph;
import grafostp2.grafos.ArvoreGeradora;
import grafostp2.grafos.BuscaLargura;
import grafostp2.grafos.BuscaProfundidade;
import grafostp2.grafos.CaminhoBellman;
import grafostp2.grafos.CaminhoDijistra;
import grafostp2.grafos.Grafos;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Controlador {

    public static Grafos grafos = new Grafos();
    public static BuscaProfundidade buscaP= new BuscaProfundidade();
    public static BuscaLargura buscaL= new BuscaLargura();
    public static CaminhoDijistra camD = new CaminhoDijistra();
    public static ArvoreGeradora agm = new ArvoreGeradora();
    public static CaminhoBellman camB = new CaminhoBellman();
    
    public Boolean pegaArquivo() {
        return grafos.pegaArquivo();
    }

    public int getOr() {
        return grafos.getOr();
    }

    public void inicializar() {
        grafos.inicializar();
    }

    public Graph getGraph(){
        return Grafos.graph;
    }
    public void montar() {
        grafos.montar();
    }

    public String imprimirLista() {
        return grafos.imprimirLista();
    }

    public String imprimirMatriz() {
        return grafos.imprimirMatriz();
    }

    public ArrayList<String> verificarCaminho(int inicio, int fim, Boolean rep, Boolean metodo) {
        return Grafos.verificarCaminho(inicio, fim, rep, metodo);
    }

    public String buscaLarguraString(int inicial, Boolean rep) {
        return buscaL.buscaLarguraString(inicial, rep);
    }

    public String buscaProfundidadeString(int inicial, Boolean rep) {
        return buscaP.buscaProfundidadeString(inicial, rep);
    }

    public Boolean verificarEntrada(int entrada) {
        return grafos.verificarEntrada(entrada);
    }

    public String getFileName() {
        return grafos.getFilename();
    }

    public String modificarArquivo() {
        return grafos.modificarArquivo();
    }

    public String buscaProfundidadeConexo(Boolean ver) {
        return buscaP.buscaProfundidadeConexo(ver);
    }

    public String agmKruskal(Boolean rep) {
        return agm.agmKruskal(rep);
    }

    public String agmPrim(Boolean rep) {
        return agm.agmPrim(rep);
    }

    public String dijkstra(Boolean rep, int inicial) {
        return camD.dijkstra(rep, inicial);
    }

    public String bellman(Boolean rep, int inicial) {
        return camB.bellman(rep, inicial);
    }

}

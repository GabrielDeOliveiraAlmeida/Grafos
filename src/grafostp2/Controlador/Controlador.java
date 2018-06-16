/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.Controlador;

import grafos.Desenho.Graph;
import grafostp2.EstruturaAuxiliares.Aresta;
import grafostp2.EstruturaAuxiliares.Vertice;
import grafostp2.EstruturaAuxiliares.VerticeP;
import grafostp2.EstruturaDados.Lista;
import grafostp2.grafos.ArvoreGeradora;
import grafostp2.grafos.BuscaLargura;
import grafostp2.grafos.BuscaProfundidade;
import grafostp2.grafos.CaminhoBellman;
import grafostp2.grafos.CaminhoDijistra;
import grafostp2.grafos.Grafos;
import grafostp2.grafos.OrdenacaoTopologica;
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
    public static OrdenacaoTopologica ord = new OrdenacaoTopologica();
    
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

    public String imprimirLista(Lista[] lista) {
        return grafos.imprimirLista(lista);
    }

    public String imprimirMatriz() {
        return grafos.imprimirMatriz();
    }

    public ArrayList<String> verificarCaminho(int inicio, int fim, Boolean rep, Boolean metodo) {
        return Grafos.verificarCaminho(inicio, fim, rep, metodo);
    }

    public String buscaLarguraString(int[] vet, int inicial, Boolean rep) {
        return buscaL.buscaLarguraString(vet, inicial, rep);
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

    public String buscaProfundidadeConexo(int[] comp, Boolean ver) {
        return buscaP.buscaProfundidadeConexo(comp, ver);
    }

    public ArrayList<Aresta> agmKruskal(Boolean rep) {
        return agm.agmKruskal(rep);
    }

    public ArrayList<Aresta> agmPrim(Boolean rep) {
        return agm.agmPrim(rep);
    }

    public Lista[] dijkstra(Boolean rep, int inicial) {
        return camD.dijkstra(rep, inicial);
    }

    public Lista[] bellman(Boolean rep, int inicial) {
        return camB.bellman(rep, inicial);
    }

    public ArrayList<Vertice> ordTopologica(Boolean rep,int inicio){
        return ord.ordTopologica(rep, inicio);
    }
    
    public void buscaProConexidade(int inicial, Boolean ver){
        buscaP.buscaProConexidade(inicial, ver);
    }
    
    public int[] coloracao(){
        return grafos.coloracao();
    }
}

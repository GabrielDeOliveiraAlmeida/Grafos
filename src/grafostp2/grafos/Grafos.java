/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafos.Desenho.Edge;
import grafos.Desenho.Graph;
import grafos.Desenho.Vertex;
import grafostp2.EstruturaAuxiliares.Aresta;
import grafostp2.EstruturaAuxiliares.Vertice;
import grafostp2.EstruturaDados.Heap;
import grafostp2.EstruturaDados.Lista;
import grafostp2.EstruturaAuxiliares.VerticeP;
import grafostp2.EstruturaAuxiliares.VerticeL;
import grafostp2.EstruturaAuxiliares.VerticeAGM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gabriel
 */
public class Grafos {

    public static Integer[][] matriz;
    public static Lista[] lista;
    public static int tam;
    public final static int INF = 100000000;
    public static int or;
    public static Graph graph; 
    public static Graph graphCopia;
    
    private File arquivo;
    private Scanner entrada;
    private BufferedReader br;
    
    
    
    String filename = "";

    public Grafos() {

    }

    public int getOr() {
        return or;
    }

    public Boolean pegaArquivo() {
        JFileChooser fc = new JFileChooser("./");
        fc.setFileFilter(new FileNameExtensionFilter("Arquivo Grafo ou Digrafo[.txt]", "txt"));
        fc.setAcceptAllFileFilterUsed(false);
        int result;
        result = fc.showOpenDialog(null);

        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile().getAbsolutePath();
            arquivo(filename);
            return true;
            
        }
        else return false;
    }

    public void arquivo(String fName) {

        try {
            arquivo = new File(fName);
            br = new BufferedReader(new FileReader(arquivo.getPath()));
            entrada = new Scanner(br);
            if (entrada.hasNextInt()) {
                or = entrada.nextInt();
                tam = entrada.nextInt();
                graph = new Graph(tam);
                graphCopia = new Graph(tam);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo Não Encontrado OU Vazio, verifique-o antes de continuar", "ERRO", ERROR_MESSAGE);
        }
    }

    public String modificarArquivo() {
        
        try {
            arquivo = new File(filename);
            br = new BufferedReader(new FileReader(arquivo.getPath()));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo Não Encontrado ou Não carregado (Ctrl-R),"
                    + " tente novamente", "ERRO", ERROR_MESSAGE);
            return null;
        }
        String arq = "";
        try {
            while (br.ready()) {
                arq += br.readLine();
                arq += "\n";
            }
        } catch (IOException ex) {
            System.out.println("Arquivo Não Encontrado, tente novamente");
        }
        return arq;
    }

    public String getFilename() {
        return filename;
    }

    public void inicializar() {
        matriz = new Integer[tam][tam];
        lista = new Lista[tam];

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = null;
            }
        }

        for (int i = 0; i < tam; i++) {
            lista[i] = new Lista();
        }
        montar();
        try {
            br.close();
        } catch (IOException e) {

        }
    }

    public void montar() {
        if (or == 1) {
            montarDigrafo();
        } else {
            montarGrafo();
        }
    }

    private void montarGrafo() {
        int numi, numj, peso;
        Vertice ver;
        Vertex verX, verY;
        Edge e;
        while (entrada.hasNextInt()) {
            numi = entrada.nextInt();
            numj = entrada.nextInt();
            peso = entrada.nextInt();
            ver = new Vertice(numj, peso);
            lista[numi].add(ver);
            ver = new Vertice(numi, peso);
            lista[numj].add(ver);

            verX = graph.getVertex().get(numi);
            verX.setID(numi);
            verY = graph.getVertex().get(numj);
            verY.setID(numj);
            e= new Edge(verX, verY, peso);   
            graph.addEdge(e);
            //e = new Edge(verY,verX,peso);
            //graph.addEdge(e);
            
            matriz[numi][numj] = peso;
            matriz[numj][numi] = peso;
        }
        clonarGraph();
    }

    private void montarDigrafo() {
        int numi, numj, peso;
        Vertice ver;
        Vertex verX, verY;
        Edge e;
        while (entrada.hasNextInt()) {
            numi = entrada.nextInt();
            numj = entrada.nextInt();
            peso = entrada.nextInt();
            
            verX = graph.getVertex().get(numi);
            verX.setID(numi);
            verY = graph.getVertex().get(numj);
            verY.setID(numj);
            e= new Edge(verX, verY, peso);    
            graph.addEdge(e);
            
            ver = new Vertice(numj, peso);
            lista[numi].add(ver);
            matriz[numi][numj] = peso;
        }
        clonarGraph();
    }

    private void clonarGraph(){
        graphCopia.setEdges(graph.getEdges());
        graphCopia.setVertex(graph.getVertex());
    }
    public String imprimirLista() {
        String impressao = "VérticeOrigem -> VérticeDestino(Peso da Aresta) >/\n";
        if (lista == null) {
            return "Grafo não carregado a partir do arquivo (Crtl + R)";
        }
        for (int i = 0; i < tam; i++) {
            impressao += String.valueOf(i) + "->";
            impressao += lista[i].imprimir();
            impressao += "\n";
        }
        return impressao;
    }

    public String imprimirMatriz() {
        int i, j;
        String impressao = "";
        if (matriz == null) {
            return "Grafo não carregado a partir do arquivo (Crtl + R)";
        }
        for (i = 0; i < tam; i++) {
            for (j = 0; j < tam; j++) {
                impressao += String.format("%d\t", matriz[i][j]);
            }
            impressao += "\n";
        }
        return impressao;
    }
    
        private static void imprimirVetor(ArrayList<VerticeAGM> caminho, Lista cam){
        int i, tamanho = caminho.size();
        String ca = "";
        VerticeAGM aux;
        for (i = 0; i < tamanho; i++) {
            aux = caminho.get(i);
            cam.add(new Vertice(aux.getPos(), aux.getPeso()));
        }

 //       return ca + "/";
    }

    public static ArrayList<String> verificarCaminho(int inicio, int fim, Boolean rep, Boolean metodo) {
        Boolean caminho = false;
        ArrayList<String> cami = new ArrayList<>();
        int[] pai = new int[tam];
        for (int i = 0; i < tam; i++) {
            pai[i] = -1;
        }
        if (metodo) {//profundidade
            VerticeP[] verP = new VerticeP[tam];
            BuscaProfundidade.caminhoProfundidade(verP, inicio, fim, rep, pai);
            caminho = BuscaProfundidade.recuperarCaminhoProfundidade(pai, inicio, fim, cami, false);
        } else {//largura
            VerticeL[] verL = new VerticeL[tam];
            BuscaLargura.buscaLargura(verL, inicio, rep);
            caminho = BuscaLargura.caminhoLargura(verL, inicio, fim, cami, false);
        }
        if (caminho) {
            return cami;
        } else {
            return null;
        }

    }
    

    public static VerticeAGM extrairMinimo(ArrayList<VerticeAGM> q) {
        VerticeAGM min = null;
        int j = -1;
        min = new VerticeAGM(-1, INF, -1, 'x');
        for (int i = 0; i < Grafos.tam; i++) {
            if (q.get(i).getQ() == 'x') {
                if (min.getPeso() > q.get(i).getPeso()) {
                    min.setPos(q.get(i).getPos());
                    min.setPeso(q.get(i).getPeso());
                    min.setPai(q.get(i).getPai());
                    min.setQ('-');
                    j = i;
                }
            }
        }
        if (j == -1) {
            return null;
        }
        q.get(j).setQ('-');
        return min;
    }
    
    public static Boolean qVazio(ArrayList<VerticeAGM> u) {
        for (int i = 0; i < Grafos.tam; i++) {
            if (u.get(i).getQ() == 'x') {
                return true;
            }
        }
        return false;
    }
        
    public Boolean verificarEntrada(int entrada) {
        if (entrada >= 0 && entrada < tam) {
            return true;
        } else {
            return false;
        }
    }

    public static void unirMatriz(Integer[][] matrix, VerticeAGM u) {
        if (u.getPai() == -1) {
            return;
        }
        matrix[u.getPai()][u.getPos()] = u.getPeso();
        matrix[u.getPos()][u.getPai()] = u.getPeso();
    }


    public static void unir(ArrayList<Aresta> list, VerticeAGM u) {
        if (u.getPai() == -1) {
            return;
        }
        System.out.println(u.getPos() + " "+ u.getPai());
        list.add(new Aresta(u.getPos(),u.getPai(), u.getPeso()));
        //list.add(u.getPos() + " " + u.getPai());
        //Vertice ver = new Vertice(u.getPai(), u.getPeso());
        //list[u.getPos()].add(ver);
        
        //list.add(u.getPos(), String.valueOf(u.getPai()));
        //if (Grafos.or == 0) {
           // ver = new Vertice(u.getPos(), u.getPeso());
        //    list[u.getPai()].add(ver);
        //list.add(u.getPai(), String.valueOf(u.getPos()));
        
       // }
    }
 

    public static Lista[] recuperarCaminho(ArrayList<VerticeAGM> vetor, int inicio ) {
        Lista[] cam;
        String dist;
        String impressao = "Vértices (Distância) | Caminho\n";
        cam = new Lista[Grafos.tam];
        for(int i=0;i<Grafos.tam; i++){
            cam[i] = new Lista();
        }
        
        ArrayList<VerticeAGM> caminho;
        for (int i = 0; i < tam; i++) {
            caminho = new ArrayList();
            recuperarCaminhoEstrutura(vetor, inicio, i, caminho, false);
            //if (vetor.get(i).getPeso() == INF) {
            //    dist = "NA";
            //} else {
            //    dist = String.valueOf(vetor.get(i).getPeso());
                
            //}
            imprimirVetor(caminho, cam[i]);
            //impressao += String.valueOf(i) + " (" + dist + ")\t|"
            //        + imprimirVetor(caminho, cam[i]) + "\n";
        }
        //impressao += "\n\nNA = Não Alcançável";
        return cam;
    }

    private static boolean recuperarCaminhoEstrutura(ArrayList<VerticeAGM> ver, int inicio, int fim, ArrayList<VerticeAGM> cami,
            Boolean troca) {

        boolean caminho = false;
        if (!troca) {
            cami.add(ver.get(inicio));
        }
        if ((ver.get(inicio).getPai() == fim) || (inicio == ver.get(fim).getPai())) {
            if (troca) {
                cami.add(ver.get(inicio));
            } else {
                cami.add(ver.get(fim));
            }
            return true;
        } else if (ver.get(inicio) == ver.get(fim)) {
            return true;
        } else if (ver.get(inicio).getPai() == -1 && ver.get(fim).getPai() == -1) {
            return false;
        } else {
            if (ver.get(inicio).getPai() == -1) {
                troca = true;
                caminho = recuperarCaminhoEstrutura(ver, fim, inicio, cami, troca);
            } else {
                caminho = recuperarCaminhoEstrutura(ver, ver.get(inicio).getPai(), fim, cami, troca);
                if (troca) {
                    cami.add(ver.get(inicio));
                }
            }
        }

        return caminho;

    }


}

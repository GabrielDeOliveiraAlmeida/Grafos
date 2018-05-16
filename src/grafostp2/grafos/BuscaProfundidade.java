/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.grafos;

import grafostp2.EstruturaAuxiliares.VerticeP;
import grafostp2.EstruturaDados.Lista;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class BuscaProfundidade {
    private static int tempo;
    
    
    public static void caminhoProfundidade(VerticeP[] verP, int inicio, int fim, Boolean rep, int[] pai) {
        for (int i = 0; i < Grafos.tam; i++) {
            verP[i] = new VerticeP('b', 0, 0, 0);
        }
        tempo = 0;
        if (rep) {
            visitarVerticeCaminho(verP, Grafos.lista, inicio, pai);
        } else {
            visitarVerticeMCaminho(verP, Grafos.matriz, inicio, pai);
        }

    }
    public String buscaProfundidadeString(int inicial, Boolean ver) {
        VerticeP[] verP = new VerticeP[Grafos.tam];
        verP = buscaProfundidade(verP, inicial, ver);
        String impressao = "Vértice -> (I = Tempo Inicial , F = Tempo Final)\n";

        for (int i = 0; i < Grafos.tam; i++) {
            impressao += String.valueOf(i) + " (I = " + String.valueOf(verP[i].getInicio())
                    + " , F = " + String.valueOf(verP[i].getFim()) + ")\n";
        }

        return impressao;
    }

    private VerticeP[] buscaProfundidade(VerticeP[] verP, int inicial, Boolean rep) {
        for (int i = 0; i < Grafos.tam; i++) {
            verP[i] = new VerticeP('b', 0, 0, 0);
        }
        tempo = 0;
        if (rep) {
            visitarVertice(verP, Grafos.lista, inicial);

            for (int i = 0; i < Grafos.tam; i++) {
                if (verP[i].getCor() == 'b') {
                    if (i != 0) {
                        System.out.println("Grafo DESCONEXO");
                    }
                    visitarVertice(verP, Grafos.lista, i);
                }
            }
        } else {
            visitarVerticeM(verP, Grafos.matriz, inicial);

            for (int i = 0; i < Grafos.tam; i++) {
                if (verP[i].getCor() == 'b') {
                    if (i != 0) {
                        System.out.println("Grafo DESCONEXO");
                    }
                    visitarVerticeM(verP, Grafos.matriz, i);
                }
            }
        }
        return verP;
    }

    private void visitarVertice(VerticeP[] ver, Lista[] lista, int i) {
        ver[i].setCor('c');
        tempo = tempo + 1;
        ver[i].setInicio(tempo);
        int tamanho = lista[i].size();
        for (int k = 0; k < tamanho; k++) {
            if (ver[lista[i].getVer(k)].getCor() == 'b') {
                //System.out.printf("Brancos %d\n", lista[i].getVer(k));
                visitarVertice(ver, Grafos.lista, Grafos.lista[i].getVer(k));
            }
        }
        ver[i].setCor('p');
        tempo = tempo + 1;
        ver[i].setFim(tempo);
    }

    private void visitarVerticeM(VerticeP[] ver, Integer[][] matriz, int i) {
        ver[i].setCor('c');
        tempo = tempo + 1;
        ver[i].setInicio(tempo);

        for (int k = 0; k < Grafos.tam; k++) {
            if (matriz[i][k] != null) {
                if (ver[k].getCor() == 'b') {
                    visitarVerticeM(ver, matriz, k);
                }
            }
        }
        ver[i].setCor('p');
        tempo = tempo + 1;
        ver[i].setFim(tempo);
    }
    

    private static void visitarVerticeMCaminho(VerticeP[] ver, Integer[][] matriz, int i, int[] pai) {
        ver[i].setCor('c');
        tempo = tempo + 1;
        ver[i].setInicio(tempo);

        for (int k = 0; k < Grafos.tam; k++) {
            if (matriz[i][k] != null) {
                if (ver[k].getCor() == 'b') {
                    pai[k] = i;
                    visitarVerticeMCaminho(ver, matriz, k, pai);
                }
            }
        }
        ver[i].setCor('p');
        tempo = tempo + 1;
        ver[i].setFim(tempo);
    }

    private static void visitarVerticeCaminho(VerticeP[] ver, Lista[] lista, int i, int[] pai) {
        ver[i].setCor('c');
        tempo = tempo + 1;
        ver[i].setInicio(tempo);
        int tamanho = lista[i].size();
        for (int k = 0; k < tamanho; k++) {
            if (ver[lista[i].getVer(k)].getCor() == 'b') {
                pai[lista[i].getVer(k)] = i;
                visitarVerticeCaminho(ver, lista, lista[i].getVer(k), pai);
            }
        }
        ver[i].setCor('p');
        tempo = tempo + 1;
        ver[i].setFim(tempo);
    }    

    
    public String buscaProfundidadeConexo(Boolean ver) {
        VerticeP[] verP = new VerticeP[Grafos.tam];
        int[] comp = new int[Grafos.tam];

        buscaProfundidadeConexo(verP, ver, comp);
        String impressao = "Vértice -> Componente\n";

        for (int i = 0; i < Grafos.tam; i++) {
            impressao += String.valueOf(i) + " -> " + String.valueOf(comp[i]) + "\n";
        }

        return impressao;
    }

    private void buscaProfundidadeConexo(VerticeP[] verP, Boolean rep, int[] vcomp) {
        String impressao;
        for (int i = 0; i < Grafos.tam; i++) {
            verP[i] = new VerticeP('b', 0, 0, 0);
        }
        tempo = 0;
        int comp = 1;
        for (int i = 0; i < Grafos.tam; i++) {
            if (verP[i].getCor() == 'b') {
                visitarVerticeConexo(verP, rep, i, vcomp, comp);
                comp++;
            }
        }

    }

    private void visitarVerticeConexo(VerticeP[] ver, Boolean rep, int i, int[] vcomp, int comp) {
        ver[i].setCor('c');
        vcomp[i] = comp;
        tempo = tempo + 1;
        ver[i].setInicio(tempo);
        if (rep) {
            int tam = Grafos.lista[i].size();
            for (int k = 0; k < tam; k++) {
                if (ver[Grafos.lista[i].getVer(k)].getCor() == 'b') {
                   
                    visitarVerticeConexo(ver, rep, Grafos.lista[i].getVer(k), vcomp, comp);
                }
            }
        } else {
            for (int k = 0; k < Grafos.tam; k++) {
                if (Grafos.matriz[i][k] != null) {
                    if (ver[k].getCor() == 'b') {
                        visitarVerticeConexo(ver, rep, k, vcomp, comp);
                    }
                }
            }
        }
        ver[i].setCor('p');
        tempo = tempo + 1;
        ver[i].setFim(tempo);
    }
    
    public static boolean recuperarCaminhoProfundidade(int[] pai, int inicio, int fim, ArrayList<String> cami,
            Boolean troca) {

        boolean caminho = false;
        if (!troca) {
            cami.add(String.valueOf(inicio));
        }
        if ((pai[inicio] == fim) || (inicio == pai[fim])) {
            if (troca) {
                cami.add(String.valueOf(inicio));
            } else {
                cami.add(String.valueOf(fim));
            }
            return true;
        } else if (pai[inicio] == pai[fim]) {
            return true;
        } else if (pai[inicio] == -1 && pai[fim] == -1) {
            return false;
        } else {
            if (pai[inicio] == -1) {
                troca = true;
                caminho = recuperarCaminhoProfundidade(pai, fim, inicio, cami, troca);
            } else {
                caminho = recuperarCaminhoProfundidade(pai, pai[inicio], fim, cami, troca);
                if (troca) {
                    cami.add(String.valueOf(inicio));
                }
            }
            //return caminho;
        }

        return caminho;

    }
}

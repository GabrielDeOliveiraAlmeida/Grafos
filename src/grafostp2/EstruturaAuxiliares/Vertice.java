/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.EstruturaAuxiliares;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Vertice implements Comparable<Vertice>  {

    private int ver;
    private int peso;

    public Vertice(int ver, int peso) {
        this.ver = ver;
        this.peso = peso;

    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int compareTo(Vertice ver) {
       if(this.peso < ver.getPeso()) return 1;
        else return -1;
    }
}

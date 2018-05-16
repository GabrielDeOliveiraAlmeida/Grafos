/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafostp2.EstruturaAuxiliares;

/**
 *
 * @author gabriel
 */
public class Aresta {
    private int peso;
    private int ver1, ver2;

    public Aresta(int ver1, int ver2, int peso) {
        this.ver1 = ver1;
        this.ver2 = ver2;
        this.peso = peso;
    }
    
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getVer1() {
        return ver1;
    }

    public void setVer1(int ver1) {
        this.ver1 = ver1;
    }

    public int getVer2() {
        return ver2;
    }

    public void setVer2(int ver2) {
        this.ver2 = ver2;
    }
    
    
    
}

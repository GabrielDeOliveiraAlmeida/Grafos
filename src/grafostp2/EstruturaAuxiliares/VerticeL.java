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
public class VerticeL {

    private char cor;
    private int pai;
    private int dist;
    private int pos;
    private int peso;

    public VerticeL() {

    }

    public VerticeL(char cor, int pai, int dist, int pos, int peso) {
        this.cor = cor;
        this.pai = pai;
        this.dist = dist;
        this.pos = pos;
        this.peso = peso;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

    public int getDist() {
        return dist;
    }

    public void incDist(int dist) {
        this.dist = dist + 1;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}

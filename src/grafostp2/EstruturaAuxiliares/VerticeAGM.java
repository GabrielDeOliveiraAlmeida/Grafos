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
public class VerticeAGM {

    private int pai;
    private int peso;
    private int pos;
    private char q;

    public VerticeAGM(int pai, int peso, int pos, char q) {
        this.pai = pai;
        this.peso = peso;
        this.pos = pos;
        this.q = q;
    }

    public VerticeAGM(int pai, int peso, int pos) {
        this.pai = pai;
        this.peso = peso;
        this.pos = pos;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public char getQ() {
        return q;
    }

    public void setQ(char q) {
        this.q = q;
    }

}

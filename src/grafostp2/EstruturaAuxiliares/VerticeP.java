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
public class VerticeP {

    private char cor;
    private int inicio;
    private int fim;
    private int peso;

    public VerticeP(char cor, int inicio, int fim, int peso) {
        this.cor = cor;
        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}

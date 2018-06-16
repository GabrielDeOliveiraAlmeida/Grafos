/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.Desenho;


import grafostp2.Desenho.Color.RainbowScale;
import grafostp2.EstruturaAuxiliares.Vertice;
import grafostp2.EstruturaAuxiliares.VerticeP;
import java.util.ArrayList;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class Graph {
    protected ArrayList<Vertex> vertex = new ArrayList<Vertex>();
    protected ArrayList<Edge> edges = new ArrayList<Edge>();
    private Boolean isLine;
    
    public Boolean getIsLine(){
        return isLine;
    }
    public Graph(int nVert) {
        isLine = false;
        RainbowScale cS = new RainbowScale();
        //GrayScale cS = new GrayScale();
        int colorStep = 255 / nVert;
        for (int i=0; i<nVert; i++){
            Vertex v = new Vertex();
            v.setID(i);
            v.setColor(cS.getColor(i*colorStep));
           // v.setColor(Color.RED);
//            if (i % 2 == 0){
//                v.setSelected(false);
//            }
            this.vertex.add(v);
        }
        computeCircledPosition(150);
    }

    public void addVertex(Vertex v){
        this.vertex.add(v);
    }

    public void addEdge(Edge e){
        this.edges.add(e);
    }

    public void computeCircledPosition(int ray){
        isLine = false;
        int nVert = this.vertex.size();
        int step = 360 / nVert;
        int deslocX = 100 + ray;
        int deslocY = 100 + ray;
        for (int i=0; i<nVert; i++){
            double ang = i * step;
            ang = ang * Math.PI / 180;//necessario em radianos
            float X = (float) Math.cos(ang);
            float Y = (float) Math.sin(ang);
            X = X * ray + deslocX;
            Y = Y * ray + deslocY;
            this.vertex.get(i).setX(X);
            this.vertex.get(i).setY(Y);
        }
    }

    public void computeLinePosition(ArrayList<Vertice> vetor){
        isLine = true;
        int deslocX = 100;
        int deslocY = 100;
        int tam = vetor.size();
        for(int i =0 ;i<tam; i++){
            for(Vertex v : vertex){
                if(vetor.get(i).getVer() == v.getID()){
                    v.setX(deslocX);
                    v.setY(deslocY);
                    deslocX+=100;
                }
            }
        } 
    }
    public ArrayList<Vertex> getVertex() {
        return this.vertex;
    }

    public void draw(java.awt.Graphics2D g2) {
       //Draw each edges of the graph
        for (Edge edge : edges) {
            edge.draw(g2);
        }
        //Draw each vertice of the graph
        for (Vertex v : this.vertex) {
            v.draw(g2);
        }
    }

    public java.awt.Dimension getSize() {
        if (this.vertex.size() > 0) {
            float maxX = vertex.get(0).getX();
            float minX = vertex.get(0).getX();
            float maxY = vertex.get(0).getY();
            float minY = vertex.get(0).getY();

            //Encontra o maior e menor valores para X e Y
            for (Vertex v : this.vertex) {
                if (maxX < v.getX()) {
                    maxX = v.getX();
                } else {
                    if (minX > v.getX()) {
                        minX = v.getX();
                    }
                }

                if (maxY < v.getY()) {
                    maxY = v.getY();
                } else {
                    if (minY > v.getY()) {
                        minY = v.getY();
                    }
                }
            }

            int w = (int) (maxX + (this.vertex.get(0).getRay() * 5)) + 350;
            int h = (int) (maxY + (this.vertex.get(0).getRay() * 5));

            return new java.awt.Dimension(w, h);
        } else {
            return new java.awt.Dimension(0, 0);
        }
    }    

    public void setVertex(ArrayList<Vertex> vertex) {
        this.vertex = vertex;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
        public void setArestasTransp (Boolean t){
        for (Edge a : this.edges){
            a.setTransposta(t);
        }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.Desenho;

import grafostp2.grafos.Grafos;
import static grafostp2.grafos.Grafos.graph;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Visualizar extends javax.swing.JPanel {

    private BufferedImage imageBuffer;
    private final int PREF_W = 40;
    private final int PREF_H = 40;
    private Vertex markedVertex;
    private java.awt.Color color = java.awt.Color.RED;
    private ArrayList<Vertex> selectedVertices;

    /**
     * Creates new form Visualizar
     */
    public Visualizar() {
        //this.setBackground(java.awt.Color.WHITE);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        ////configuração do rendering para obeter melhor qualidade
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        if (Grafos.graph != null && this.imageBuffer == null) {
            this.imageBuffer = new BufferedImage(graph.getSize().width + 1,
                    graph.getSize().height + 1, BufferedImage.TYPE_INT_RGB);

            java.awt.Graphics2D g2Buffer = this.imageBuffer.createGraphics();
            g2Buffer.setColor(this.getBackground());
            g2Buffer.fillRect(0, 0, graph.getSize().width + 1, graph.getSize().height + 1);

            g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graph.draw(g2Buffer);
            g2Buffer.dispose();
        }

        if (this.imageBuffer != null) {
            g2.drawImage(this.imageBuffer, 0, 0, null);
        }
    }

    public void setGraph(Graph graph) {
        if (graph != null) {
            this.setPreferredSize(new Dimension(graph.getSize().width,
                    graph.getSize().height));
            this.setSize(new Dimension(graph.getSize().width,
                    graph.getSize().height));
            this.cleanImage();
            this.repaint();
        }
    }

    public Vertex getMarkedVertex() {
        return markedVertex;
    }

    public void cleanImage() {
        this.imageBuffer = null;
    }

    public void adjustPanel() {
        float iniX = graph.getVertex().get(0).getX();
        float iniY = graph.getVertex().get(0).getY();
        float max_x = iniX, max_y = iniX;
        float min_x = iniY, min_y = iniY;
        int zero = graph.getVertex().get(0).getRay() * 5 + 10;

        for (int i = 1; i < graph.getVertex().size(); i++) {
            float x = graph.getVertex().get(i).getX();
            if (max_x < x) {
                max_x = x;
            } else if (min_x > x) {
                min_x = x;
            }

            float y = graph.getVertex().get(i).getY();
            if (max_y < y) {
                max_y = y;
            } else if (min_y > y) {
                min_y = y;
            }
        }

        for (Vertex v : graph.getVertex()) {
            v.setX(v.getX() + zero - min_x);
            v.setY(v.getY() + zero - min_y);
        }

        Dimension d = this.getSize();
        d.width = (int) max_x + zero;
        d.height = (int) max_y + zero;
        this.setSize(d);
        this.setPreferredSize(d);
    }

    public void markVertices(ArrayList<Vertex> vertices) {
        if (vertices != null) {
            this.cleanMarkedVertices(false);

            //change the vertices' colors
            for (Vertex v : vertices) {
                v.setSelected(true);
            }

            this.cleanImage();
            this.repaint();
        }
    }

    public void cleanMarkedVertices(boolean cleanVertex) {
        if (graph != null) {
            this.markedVertex = null;

            for (Vertex vertex : graph.getVertex()) {
                vertex.setSelected(false);

            }
        }

        this.cleanImage();
        this.repaint();
    }

    @Override
    public void setFont(java.awt.Font font) {
        //
    }

    @Override
    public java.awt.Font getFont() {
        return null;// colocar alguma fonte
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

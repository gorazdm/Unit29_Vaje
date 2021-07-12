package si.academia.unit29.vaja1;

import java.awt.*;
import java.util.ArrayList;

// import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

public class graphPanel extends JPanel{

    // public ArrayList temperaturesArray = null;
    public ArrayList<Float> temperaturesArray = null;

    private boolean showPolygon = false;

    graphPanel() {
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        this.setBackground(Color.black);

        int xPanel = getWidth();
        int yPanel = getHeight();
        int xStart = 30;
        int yStart = yPanel - yPanel/4;
        int xEnd = xPanel - 20;
        int yEnd = 20;
        g2D.setColor(Color.yellow);
        g2D.drawLine(xStart, yStart, xStart, yEnd);   // y
        g2D.drawLine(xStart, yStart, xEnd, yStart);   // x

        int dx = (xEnd - xStart) / 24;
        int dy = (yStart - yEnd) / 8;

        // x - os
        for (int i = 0; i < 24; i++) {
            int x = xStart + dx*i + dx/2;
            g2D.drawLine(x, yStart + 5, x, yStart - 5);
            String hours = Integer.toString(i + 1);
            g2D.drawString(hours, x - dx/10, yStart + 20);
        }

        // y - os
        for (int i = 1; i <= 8; i++) {
            int y = yStart - dy*i;
            g2D.drawLine(xStart - 5, y, xStart + 5, y);
            String temp = Integer.toString(i*5);
            g2D.drawString(temp, xStart - 25, y + dy/10);
        }
    }

    private void drawGraph(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        int xPanel = getWidth();
        int yPanel = getHeight();
        int xStart = 30;
        int yStart = yPanel - yPanel/4;
        int xEnd = xPanel - 20;
        int yEnd = 20;
        int dx = (xEnd - xStart) / 24;
        int dy = (yStart - yEnd) / 8;

        if (temperaturesArray != null) {
            for (int i = 0; i < temperaturesArray.size() && i < 24; i++) {
                float temp = (float) temperaturesArray.get(i);
                System.out.println(i + " -> " + temperaturesArray.get(i));
                int x = xStart + dx*i +1;
                if (temp > 0) {
                    int height = (int) (dy*temp)/5 - 1;
                    // g2D.setColor(Color.magenta);
                    g2D.setColor(new ColorUIResource(25, 109, 187));
                    g2D.fillRect(x, yStart - height, dx - 5, height);
                } else if (temp < 0) {
                    int height = (int) (dy*temp)/5 + 1;     // height < 0 -> negative
                    // g2D.setColor(Color.blue);
                    g2D.setColor(new ColorUIResource(181, 247, 225));
                    g2D.fillRect(x, yStart + 1, dx - 5, -height);

                    g2D.setColor(Color.yellow);
                    String hours = Integer.toString(i + 1);
                    g2D.drawString(hours, x + dx/3, yStart - 10);
                }
            }
        }
    }

    private void drawPoly(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if (temperaturesArray != null) {
            int xPanel = getWidth();
            int yPanel = getHeight();
            int xStart = 30;
            int yStart = yPanel - yPanel/4;
            int xEnd = xPanel - 20;
            int yEnd = 20;
            int dx = (xEnd - xStart) / 24;
            int dy = (yStart - yEnd) / 8;

            // Integer[] polyY = temperaturesArray.toArray(new Integer[0]);
            // Integer[] polyX = new Integer[0];
            // Float[] polyY = temperaturesArray.toArray(new Float[0]);
            // Float[] polyX = new Float[0];

            int elements = temperaturesArray.size();
            // float[] xPoints = new float[elements];
            float[] yPoints = new float[elements];
            int[] xPts = new int[elements];     // ure
            int[] yPts = new int[elements];     // temperature

            // iz 'ArrayList' dam temperature v 'array' !!
            for (int i = 0; i < elements; i++) {
                yPoints[i] = temperaturesArray.get(i);
            }

            // temperature pretvorim iz float v int !!
            for (int i = 0; i < elements; i++) {
                yPts[i] = (int) yPoints[i];
                // xPts[i] = (int) xPoints[i];
            }

            for (int i = 0; i < elements; i++) {
                int x = xStart + dx*i + dx/2;
                xPts[i] = x;

                int temp = yPts[i];
                int height = temp*dy/5;

                yPts[i] = yStart - height;
            }

            g2D.setColor(Color.magenta);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawPolyline(xPts, yPts, elements);
        }
    }

    void toggleBoolean() {
        showPolygon = !showPolygon;
        repaint();
    }

    void setBoolean() {
        showPolygon = false;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        if (!showPolygon) {
            drawGraph(g);
        } else {
            drawPoly(g);
        }
    }

    public void setTemperatures(ArrayList<Float> t) {
        temperaturesArray = t;
        repaint();
    }
}

package com.algo.quick;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        this.setContentPane(canvas);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    // TODO:
    private ArrayData aData;
    public void render(ArrayData aData) {
        this.aData = aData;
        this.repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // TODO:
            int width = AlgoFrame.this.getCanvasWidth() / AlgoFrame.this.aData.length();

            for (int i = 0; i < AlgoFrame.this.aData.length(); i++) {

                if (i >= AlgoFrame.this.aData.left && i <= AlgoFrame.this.aData.right) {
                    AlgoVisHelper.setColor(g2d, Color.BLUE);
                } else {
                    AlgoVisHelper.setColor(g2d, Color.gray);
                }

                if (i == AlgoFrame.this.aData.anchor) {
                    AlgoVisHelper.setColor(g2d, Color.cyan);
                }
                if (i == AlgoFrame.this.aData.curIndex) {
                    AlgoVisHelper.setColor(g2d, Color.magenta);
                }

                AlgoVisHelper.fillRectangle(
                        g2d,
                        i * width,
                        AlgoFrame.this.getCanvasHeight() - AlgoFrame.this.aData.getData(i),
                        width - 1,
                        AlgoFrame.this.aData.getData(i)
                );
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(
                    AlgoFrame.this.getCanvasWidth(),
                    AlgoFrame.this.getCanvasHeight()
            );
        }
    }
}

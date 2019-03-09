package com.algo.quick;

import java.awt.*;

public class AlgoVisualizer {

    private int DELAY = 80;
    private ArrayData aData;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, String title, int sceneWidth, int sceneHeight) {

        this.aData = new ArrayData(N, sceneHeight);

        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame(title, sceneWidth, sceneHeight);
            new Thread(() -> {
                this.run();
            }).start();
        });
    }

    private void run() {
        this.renderData(-1, -1, -1, -1);  // render
        this.quickSort(0, this.aData.length() -1);
        this.renderData(0, this.aData.length() - 1, -1, -1);    // render
    }

    private void quickSort(int left, int right) {
        this.renderData(left, right, -1, -1);   // render

        if (left >= right) {
            return;
        }

        int p = partition(left, right);
//        int p = partition2Ways(left, right);

        quickSort(left, p - 1);
        quickSort(p + 1, right);
    }

    private int partition(int left, int right) {
        int j = left;
        int value = this.aData.getData(left);

        this.renderData(left, right ,left, -1);   // render

        for (int k = left + 1; k <= right; k++) {
            if (this.aData.getData(k) > value) {

            } else {
                this.aData.swap(k, j + 1);
                j++;
            }

            this.renderData(left, right, left, k);
        }

        this.aData.swap(j, left);
        this.renderData(left, right, left, -1);
        return j;
    }

    private int partition2Ways(int left, int right) {
        int value = this.aData.getData(left);
        int i = left + 1;
        int j = right;

        while(true) {

            while(this.aData.getData(i) < value) {
                i++;
            }

            while(this.aData.getData(j) > value) {
                j--;
            }

            if (i >= j) {
                break;
            }

            this.aData.swap(i, j);
            i++;
            j--;
        }

        this.aData.swap(j, left);
        return j;
    }

    private void renderData(int left, int right, int anchor, int curIndex) {
        this.aData.left = left;
        this.aData.right = right;
        this.aData.anchor = anchor;
        this.aData.curIndex = curIndex;

        this.frame.render(this.aData);
        AlgoVisHelper.pause(this.DELAY);
    }
}

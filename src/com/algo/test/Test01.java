package com.algo.test;

import com.algo.quick.AlgoVisualizer;

public class Test01 {
    public static void main(String[] args) {

        int N = 100;
        int sceneWidth = 1000;
        int sceneHeight = 800;

        AlgoVisualizer visualizer = new AlgoVisualizer(
                N,
                "看得见的归并排序",
                sceneWidth,
                sceneHeight
        );
    }
}

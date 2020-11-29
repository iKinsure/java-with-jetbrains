package com.ikinsure.hyperskill.hard.recognition;

import java.util.Arrays;
import java.util.Random;

public enum NumberData {

    N0(new double[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
            new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},"0"),
    N1(new double[]{0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1},
            new double[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},"1"),
    N2(new double[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},"2"),
    N3(new double[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1 ,1, 1},
            new double[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},"3"),
    N4(new double[]{1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1},
            new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},"4"),
    N5(new double[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
            new double[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},"5"),
    N6(new double[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},"6"),
    N7(new double[]{1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1},
            new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},"7"),
    N8(new double[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},"8"),
    N9(new double[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
            new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1},"9");


    double[] idealInput;
    double[] idealOutput;
    double[] weight;
    String sign;

    NumberData(double[] idealInput, double[] idealOutput, String sign) {
        this.idealInput = idealInput;
        this.idealOutput = idealOutput;
        this.weight = initWeight(16);
        this.sign = sign;
    }

    private double[] initWeight(int size) {
        Random random = new Random();
        return Arrays.stream(new double[size]).map(d -> random.nextGaussian()).toArray();
    }
}

package com.ikinsure.hyperskill.hard.recognition;

public enum NumberData {
    N1(new int[]{-1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1}, 6, "1"),
    N2(new int[]{1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1}, 1, "2"),
    N3(new int[]{1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1 ,1 }, 0, "3"),
    N4(new int[]{1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1}, 2, "4"),
    N5(new int[]{1, 1, 1, 1, -1, -1, 1, 1, 1 , -1, -1, 1, 1, 1, 1}, 0, "5"),
    N6(new int[]{1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, -1, "6"),
    N7(new int[]{1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1}, 3, "7"),
    N8(new int[]{1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1}, -2, "8"),
    N9(new int[]{1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}, -1, "9"),
    N0(new int[]{1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1}, -1, "0");

    int[] weight;
    int bias;
    String sign;
    NumberData(int[] weight, int bias, String sign) {
        this.weight = weight;
        this.bias = bias;
        this.sign = sign;
    }
}

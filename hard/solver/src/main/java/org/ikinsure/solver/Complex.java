package org.ikinsure.solver;

public class Complex {

    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public void add(Complex c) {
        this.re += c.re;
        this.im += c.im;
    }

    public void sub(Complex c) {
        this.re -= c.re;
        this.im -= c.im;
    }

    public void multiply(Complex c) {
        final double re = this.re;
        this.re = re * c.re - im * c.im;
        this.im = re * c.im + c.re * im;
    }
    public void multiply(double value) {
        this.re *= value;
        this.im *= value;
    }

    public void divide(Complex c) {
        if (Util.equals(re, c.re) && Util.equals(im, c.im)) {
            this.re = 1.0;
            this.im = 0.0;
        } else {
            Complex temp1 = new Complex(c.re, -c.im);
            Complex temp2 = new Complex(c.re, c.im);
            this.multiply(temp1);
            temp2.multiply(temp1);
            this.divide(temp2.re);
        }
    }

    public void divide(double value) {
        this.re /= value;
        this.im /= value;
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public void conjugate() {
        this.im = -im;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        boolean reZero = Util.equals(re, 0.0);
        boolean imZero = Util.equals(im, 0.0);
        boolean imOne = Util.equals(im, 1.0);
        boolean imMinusOne = Util.equals(im, -1.0);
        if (reZero && imZero) { // 0 + 0j
            res.append("0");
        } else if (reZero) { // 0 + bj
            if (imOne) {
                res.append("i");
            } else if (imMinusOne) {
                res.append("-i");
            } else {
                res.append(im < 0.0 ? im : "+" + im).append("i");
            }
        } else if (imZero) { // a + 0j
            res.append(re);
        } else { // a + bj
            if (imOne) {
                res.append(re).append("+i");
            } else if (imMinusOne) {
                res.append(re).append("-i");
            } else {
                res.append(re).append(im < 0.0 ? im : "+" + im).append("i");
            }
        }
        return res.toString();
    }
}

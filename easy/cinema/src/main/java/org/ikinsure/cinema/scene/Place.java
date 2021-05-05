package org.ikinsure.cinema.scene;

public enum Place {

    FREE("S"),
    BUSY("B");

    public final String sign;

    Place(String sign) {
        this.sign = sign;
    }

}

package util;

import java.io.Serializable;

public class Date implements Serializable {

    private static final long serialVersionUID = 1L;

    public int d;
    public int m;
    public int a;

    public Date(int d, int m, int a) {
        this.d = d;
        this.m = m;
        this.a = a;
    }

    @Override
    public String toString() {
        String dia = d < 10 ? "0" + d : d + "";
        String mes = m < 10 ? "0" + m : m + "";
        return dia + "/" + mes + "/" + a;
    }
}

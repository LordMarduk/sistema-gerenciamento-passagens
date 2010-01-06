package rodoviaria;

import java.io.Serializable;

public class IdRodov implements Serializable{

    private int id;

    public IdRodov(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

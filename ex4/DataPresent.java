package ex4;

import java.io.Serializable;

public class DataPresent implements Serializable {

    String message;
    int data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


}

package model;

/**
 * Created by Junejas on 4/15/2017.
 */

public class DataModel {
    private String text;

    public DataModel(String text) {
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

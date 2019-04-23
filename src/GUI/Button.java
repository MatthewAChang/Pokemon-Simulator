package GUI;

import javax.swing.*;

public class Button extends JButton {
    private int id;

    public Button(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

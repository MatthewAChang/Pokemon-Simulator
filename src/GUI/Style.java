package GUI;

import java.awt.*;

public class Style {
    public static void Style() {
        System.out.println("Here");
        //setButtonFont("Franklin Gothic Heavy");
        //setOutputFont("Arial");
        setStatusOutlineColor(Color.GREEN);
    }

    private static void setFont(String font) {
        FrameStyle.getInstance().setFont(font);
    }

    private static void setOutputFont(String font) {
        FrameStyle.getInstance().setOutputFont(font);
    }

    private static void setButtonFont(String font) {
        FrameStyle.getInstance().setButtonFont(font);
    }

    private static void setStatusFont(String font) {
        FrameStyle.getInstance().setStatusFont(font);
    }

    private static void setFontSize(int size) {
        FrameStyle.getInstance().setFontSize(size);
    }

    private static void setOutputFontSize(int size) {
        FrameStyle.getInstance().setOutputFontSize(size);
    }

    private static void setButtonFontSize(int size) {
        FrameStyle.getInstance().setButtonFontSize(size);
    }

    private static void setStatusFontSize(int size) {
        FrameStyle.getInstance().setStatusFontSize(size);
    }

    private static void setFontColor(Color color) {
        FrameStyle.getInstance().setFontColor(color);
    }

    private static void setOutputFontColor(Color color) {
        FrameStyle.getInstance().setOutputFontColor(color);
    }

    private static void setButtonFontColor(Color color) {
        FrameStyle.getInstance().setButtonFontColor(color);
    }

    private static void setStatusFontColor(Color color) {
        FrameStyle.getInstance().setStatusFontColor(color);
    }

    private static void setStatusOutlineColor(Color color) {
        FrameStyle.getInstance().setStatusOutlineColor(color);
    }

    private static void setStatusBackgroundColor(Color color) {
        FrameStyle.getInstance().setStatusBackgroundColor(color);
    }
}

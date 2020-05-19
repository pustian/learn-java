package tian.pusen.swing.jdk7._04_font;

import java.awt.*;

public class ListFont {
    public static void main(String[] args) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String fontName: fontNames) {
            System.out.println(fontName);
        }
        System.out.println(fontNames.length);
    }
}

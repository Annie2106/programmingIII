package com.uptc.prg3.supper.client.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class that handles the external fonts used on the UI.
 *
 * @author Alejandro
 * @since 13/05/2019
 */
class Fonts {
    // Font file paths
    static final String LIVVIC = "res/fonts/Livvic.ttf";
    static final String QUICKSAND = "res/fonts/Quicksand.ttf";

    /**
     * Get a font specified by the font file paths of the class {@link Fonts}.
     *
     * @param source The font path.
     * @param size   The preferred size of the font.
     * @return The indicated font with the indicated size.
     */
    static Font getFont(String source, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(source)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

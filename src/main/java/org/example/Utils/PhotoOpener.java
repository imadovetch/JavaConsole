package org.example.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PhotoOpener {
    private String path;

    public  PhotoOpener(String args) {

        this.path = args != null ?  args : "";
        this.Opener();

    }
    private void Opener(){

        File file = new File(this.path);

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                try {

                    desktop.open(file);
                } catch (IOException e) {
                    System.err.println("Error opening file: " + e.getMessage());
                }
            } else {
                System.err.println("Desktop open action is not supported.");
            }
        } else {
            System.err.println("Desktop is not supported on this platform.");
        }
    }
}


package org.example.Database.Controllers.Users;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClickablePathConsole {

    public   ClickablePathConsole() {


            // Define the path to the image file
            String filePath = "C:\\Users\\ycode\\Downloads\\testp.png";

            // Convert the file path to a file URI
            String fileUriString = filePath.replace("\\", "/");
            String url = "file:///" + fileUriString;
        System.out.println(url);
            // Print the clickable URL to the console
            System.out.println("To view the image, click the following link (in compatible terminals or IDEs):");


            // Optionally, you can try to open it programmatically if supported
            try {
                // Ensure Desktop API is available and use it to open the file
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(java.net.URI.create(url));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

}

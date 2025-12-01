package org.jasper;

import java.io.File;

public class ExportProvider {

    public static String exportToDesktop(String dirName) {
        String home = System.getProperty("user.home");
        String desktop = home + File.separator + "Desktop";

        File folder = new File(desktop, dirName);

        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (!created) {
                throw new RuntimeException("Cannot create directory on Desktop.");
            }
        }

        return folder.getAbsolutePath();
    }

}

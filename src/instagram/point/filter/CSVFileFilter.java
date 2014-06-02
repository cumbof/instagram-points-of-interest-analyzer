/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package instagram.point.filter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Fabio Cumbo
 */
public class CSVFileFilter extends FileFilter {
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            // Directories must always be accepted, otherwise
            // you cannot navigate into the file-system!!!
            return true;
        }

        String name = f.getName().toLowerCase();

        return name.endsWith(".csv");
    }

    @Override
    public String getDescription() {
        return "Comma-Separated Values (*.csv)";
    }
    
}
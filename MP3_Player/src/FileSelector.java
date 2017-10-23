import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFrame;

public class FileSelector {
    File[] inputFiles;
    String songPath;
    FileDialog fileImport;
    boolean importFinished = false;

    public void findFile() {
        fileImport = new FileDialog(new JFrame());
        fileImport.toFront();
        while (!importFinished) {
            fileImport.setVisible(true);
            inputFiles = fileImport.getFiles();

            if (inputFiles.length > 0) {
                songPath = (fileImport.getFiles()[0].getAbsolutePath());
                importFinished = true;
            }
        }

    }

}

package trabalho;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

public class UtilsIO {

    public static void Salvar(File arquivo) throws IOException  {
        arquivo.createNewFile();
        PreencherArquivo(arquivo);
    }
    
    private static void PreencherArquivo(File arquivo) {
        
    }
}

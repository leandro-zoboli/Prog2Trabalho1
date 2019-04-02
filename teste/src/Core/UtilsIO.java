package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UtilsIO {

    public static void Salvar(File arquivo) throws IOException {
        arquivo.createNewFile();
        PreencherArquivo(arquivo);
    }

    private static void PreencherArquivo(File arquivo) {

    }
  
    public static int[] ReadBytes(File file, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = -1;
        }
        try {
            FileInputStream reader = new FileInputStream(file);

            for (int i = 0; i < length; i++) {
                result[i] = reader.read();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler o arquivo. Erro: " + e.getMessage());
        }
        return result;
    }
    
    public static String ReadData(File file, int length) {
        String result = "";
        int[] data = ReadBytes(file, length);
        for (int i : data) {
            result += (char)i;
        }
        return result;
    }

}

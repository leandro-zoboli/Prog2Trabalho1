package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class UtilsIO {

    public static final int GENRESLASTINDEX = 79;
    private static int cursorPosition = 0;
    public static boolean firstTimeSaving = false;

    public static void SaveFile(File file) throws IOException {
        file.createNewFile();
        StoreDataInFile(file);
    }

    private static void StoreDataInFile(File file) {

    }

    public static int[] ReadBytes(File file, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = -1;
        }
        try {
            if (!file.canRead()) {
                throw new Exception("O arquivo não pode ser lido.");
            }
            FileInputStream reader = new FileInputStream(file);
            reader.skip(cursorPosition);

            for (int i = cursorPosition; i < length + cursorPosition; i++) {
                int t = reader.read();
                if (t != -1) {
                    result[i - cursorPosition] = t;
                } else {
                    result[i - cursorPosition] = 32; // código em ascii para espaço
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar ler o arquivo. Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        cursorPosition += length;
        return result;
    }

    public static String ReadData(File file, int length) {
        String result = "";
        int[] data = ReadBytes(file, length);
        for (int i : data) {
            result += (char) i;
        }
        System.out.println(result);
        return result;
    }

    public static boolean IsNumber(String value) {
        String validNumbers = "0123456789";
        if (value.equals("")) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (validNumbers.indexOf(value.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean SaveBytes(File file, String value, int lenght, String fieldName) {
        try {
            if (!file.canWrite()) {
                throw new Exception("O arquivo não pode ser alterado.");
            }
            if (value.length() > lenght) {
                throw new Exception("O tamanho máximo para gravação do campo " + fieldName + " é de " + lenght + " caracteres.");
            }

            String fileData = "";

            if (firstTimeSaving) {
                firstTimeSaving = false;
            } else {
                cursorPosition = 0;
                fileData = ReadData(file, (int) file.length());
            }

            FileOutputStream writer = new FileOutputStream(file);

            if (!fileData.trim().equals("")) {
                for (byte letter : fileData.getBytes()) {
                    writer.write(letter);
                }
            }

            int count = 0;
            for (byte letter : value.getBytes()) {
                writer.write(letter);
                count++;
            }

            while (count < lenght) {
                writer.write(' ');
                count++;
            }

            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "O seguinte erro ocorreu ao tentar salvar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static int getCursorPosition() {
        return cursorPosition;
    }

    public static void setCursorPosition(int cursorPosition) {
        UtilsIO.cursorPosition = cursorPosition;
    }
}

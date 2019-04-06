package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UtilsIO {

    public static int genresLastIndex = 79;

    private static int cursorPosition = 0;

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
            System.out.println("Ocorreu um erro ao ler o arquivo. Erro: " + e.getMessage());
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
        return result.trim();
    }

    public static int getCursorPosition() {
        return cursorPosition;
    }

    public static void setCursorPosition(int cursorPosition) {
        UtilsIO.cursorPosition = cursorPosition;
    }

    public static boolean isNumber(String value) {
        String validNumbers = "0123456789";

        for (int i = 0; i < value.length(); i++) {
            if (validNumbers.indexOf(value.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}

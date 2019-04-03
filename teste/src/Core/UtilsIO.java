package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UtilsIO {

    private static int cursorPosition = 0;

    public static enum genres {
        Blues(0),
        ClassicRock(1),
        Country(2),
        Dance(3),
        Disco(4),
        Funk(5),
        Grunge(6),
        HipHop(7),
        Jazz(8),
        Metal(9),
        NewAge(10),
        Oldies(11),
        Other(12),
        Pop(13),
        RandB(14),
        Rap(15),
        Reggae(16),
        Rock(17),
        Techno(18),
        Industrial(19);

        public int value;

        genres(int value) {
            this.value = value;
        }
    }

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
}

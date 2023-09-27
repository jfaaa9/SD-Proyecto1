package miCodigo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class getPGM {
    
    private byte[][] image;
    private int cols, rows, maxVal;
    
    public getPGM(String filename) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(filename));

            // Lee el encabezado del archivo PGM (formato P5)
            dis.readLine(); // Debería ser "P5" para el formato P5
            while (dis.readByte() != '\n') ; // Saltar posibles comentarios

            // Lee las dimensiones (Cols y Rows)
            String[] dimensions = new String(readLine(dis)).trim().split(" ");
            cols = Integer.parseInt(dimensions[0]);
            rows = Integer.parseInt(dimensions[1]);

            maxVal = Integer.parseInt(new String(readLine(dis)).trim());

            // Leer la imagen en formato binario
            image = new byte[rows][cols];
            for (int i = 0; i < rows; i++) {
                dis.readFully(image[i]);
            }

            dis.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private byte[] readLine(DataInputStream dis) throws IOException {
        byte[] buffer = new byte[100];
        int i = 0;
        byte b;
        while ((b = dis.readByte()) != '\n') {
            buffer[i++] = b;
        }
        byte[] line = new byte[i];
        System.arraycopy(buffer, 0, line, 0, i);
        return line;
    }
    
    public byte[][] getImage() {
        return image;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public int getMaxValue() {
        return maxVal;
    }
    
    public int getPixel(int i, int j) {
        return image[i][j] & 0xFF; // Convierte el byte a un valor entero sin signo
    }
    
}

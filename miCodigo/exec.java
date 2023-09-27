package miCodigo;

import java.io.FileOutputStream;
import java.io.IOException;

public class exec {
    public static void main(String[] args) {
        // Reemplaza "nombre_de_tu_imagen.pgm" con la ruta de tu archivo PGM
        String filename = "imgNueva.pgm";

        // Crea una instancia de getPGM para leer la imagen original
        getPGM pgmReader = new getPGM(filename);

        // Obtiene la imagen como una matriz de bytes
        byte[][] originalImage = pgmReader.getImage();

        // Obtiene información sobre la imagen
        int rows = pgmReader.getRows();
        int cols = pgmReader.getCols();
        int maxVal = pgmReader.getMaxValue();

        // Crea una nueva matriz de bytes para la imagen modificada
        byte[][] modifiedImage = new byte[rows][cols];

        // Erosion
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                int min = 255;
                int newList[];
                newList = new int[5];
                newList[0] = (originalImage[i][j-1] & 0xFF);
                newList[1] = originalImage[i-1][j] & 0xFF;
                newList[2] = originalImage[i][j] & 0xFF;
                newList[3] = originalImage[i][j+1] & 0xFF;
                newList[4] = originalImage[i+1][j] & 0xFF;
                int l;
                for(l=0;l<5;l++){
                    if(newList[l]<min){
                        min = newList[l];
                    }
                }
                modifiedImage[i][j]=(byte) min;
                //int pixelValue = (originalImage[i][j] & 0xFF) + 10;
                //modifiedImage[i][j] = (byte) Math.min(pixelValue, 255);
            }
        }

        // Guarda la imagen modificada en un nuevo archivo PGM
        try {
            FileOutputStream outputStream = new FileOutputStream("Erosion.pgm");
            outputStream.write("P5\n".getBytes());
            outputStream.write((cols + " " + rows + "\n").getBytes());
            outputStream.write((maxVal + "\n").getBytes());
            for (int i = 0; i < rows; i++) {
                outputStream.write(modifiedImage[i]);
            }
            outputStream.close();
            System.out.println("Imagen modificada guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen modificada: " + e.getMessage());
        }
        
        // Dilatacion
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                int max = 0;
                int newList[];
                newList = new int[5];
                newList[0] = (originalImage[i][j-1] & 0xFF);
                newList[1] = originalImage[i-1][j] & 0xFF;
                newList[2] = originalImage[i][j] & 0xFF;
                newList[3] = originalImage[i][j+1] & 0xFF;
                newList[4] = originalImage[i+1][j] & 0xFF;
                int l;
                for(l=0;l<5;l++){
                    if(newList[l]>max){
                        max = newList[l];
                    }
                }
                modifiedImage[i][j]=(byte) max;
                //int pixelValue = (originalImage[i][j] & 0xFF) + 10;
                //modifiedImage[i][j] = (byte) Math.min(pixelValue, 255);
            }
        }

        // Guarda la imagen modificada en un nuevo archivo PGM
        try {
            FileOutputStream outputStream = new FileOutputStream("Dilatacion.pgm");
            outputStream.write("P5\n".getBytes());
            outputStream.write((cols + " " + rows + "\n").getBytes());
            outputStream.write((maxVal + "\n").getBytes());
            for (int i = 0; i < rows; i++) {
                outputStream.write(modifiedImage[i]);
            }
            outputStream.close();
            System.out.println("Imagen modificada guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen modificada: " + e.getMessage());
        }
    }
}

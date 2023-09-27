package miCodigo;

package miCodigo;

public class exec {
    public static void main(String[] args) {
        // Reemplaza "nombre_de_tu_imagen.pgm" con la ruta de tu archivo PGM
        String filename = "imgNueva.pgm";

        // Crea una instancia de getPGM para leer la imagen
        getPGM pgmReader = new getPGM(filename);

        // Obtiene la imagen como una matriz de shorts
        byte[][] image = pgmReader.getImage();

        // Obtiene información sobre la imagen
        int rows = pgmReader.getRows();
        int cols = pgmReader.getCols();
        int maxVal = pgmReader.getMaxValue();

        // Imprime información sobre la imagen
        System.out.println("Dimensiones de la imagen: " + cols + "x" + rows);
        System.out.println("Valor máximo de píxel: " + maxVal);

        // Accede a un píxel específico (por ejemplo, el píxel en la fila 0 y columna 0)
        int pixelValue = pgmReader.getPixel(0, 0);
        System.out.println("Valor del píxel en (0, 0): " + pixelValue);

        // Aquí puedes realizar cualquier operación que desees en la imagen
        // Por ejemplo, puedes recorrer la imagen y hacer algo con cada píxel

        // Ejemplo: Calcular la suma de todos los píxeles en la imagen
        int sumaTotal = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumaTotal += (image[i][j] & 0xFF); // Convierte el byte a un valor entero sin signo
            }
        }

        System.out.println("Suma total de píxeles en la imagen: " + sumaTotal);

    }
}

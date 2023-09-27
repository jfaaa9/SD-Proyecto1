#include <stdio.h>

int main() {
    FILE *arch, *sali;
    unsigned char c, c1, c2;
    int fila, colu, i, j, gris;

    arch = fopen("imgNueva.pgm", "rb");
    sali = fopen("valores.txt", "w"); // Abre un archivo de texto para escritura

    c1 = fgetc(arch);
    c2 = fgetc(arch);
    if (c1 != 'P' || c2 != '5') {
        printf("\nFormato no corresponde a una Imagen\n");
        return 0;
    }

    c = fgetc(arch);
    c = fgetc(arch);
    while (c != '\n')
        c = fgetc(arch);
    fscanf(arch, "%d", &colu);
    fscanf(arch, "%d", &fila);
    fscanf(arch, "%d", &gris);

    fprintf(sali, "Formato: P5\n");
    fprintf(sali, "fila : %d\n", fila);
    fprintf(sali, "colu : %d\n", colu);
    fprintf(sali, "gris : %d\n", gris);

    for (i = 0; i < fila; i++) {
        for (j = 0; j < colu; j++) {
            c = fgetc(arch);
            fprintf(sali, "%d ", (int)c); // Escribe el valor en el archivo de texto
        }
        fprintf(sali, "\n"); // Salto de línea para la siguiente fila
    }

    fclose(arch);
    fclose(sali);

    return 0;
}

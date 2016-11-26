package ia;

import java.awt.Graphics;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Lienzo lienzoPadre;

    public Laberinto(Lienzo lienzoPadre) {
        this.lienzoPadre = lienzoPadre;
        celdas = new Celda[N_CELDAS_ANCHO][N_CELDAS_ALTO];
        //inicializar el array de celdas
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), CAMINO);
            }
        }
        crearEdificio(3, 6, 3, 6);
        crearEdificio(9, 12, 3, 6);
        crearEdificio(15, 18, 3, 6);
        crearEdificio(21, 24, 3, 6);
        crearEdificio(27, 30, 3, 6);

        crearEdificio(3, 6, 9, 12);
        crearEdificio(9, 12, 9, 12);
        crearEdificio(15, 18, 9, 12);
        crearEdificio(21, 24, 9, 12);
        crearEdificio(27, 30, 9, 12);

        for (int i = 0; i < 15; i++) {
            celdas[1][i].tipoCelda = CALLE;
        }

        for (int i = 0; i < 15; i++) {
            celdas[7][i].tipoCelda = CALLE;
        }

        for (int i = 0; i < 15; i++) {
            celdas[13][i].tipoCelda = CALLE;
        }
        for (int i = 0; i < 15; i++) {
            celdas[19][i].tipoCelda = CALLE;
        }
        for (int i = 0; i < 15; i++) {
            celdas[25][i].tipoCelda = CALLE;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][1].tipoCelda = CALLE;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][7].tipoCelda = CALLE;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][13].tipoCelda = CALLE;
        }

        celdas[20][3].tipoCelda = PARADA;
        celdas[20][9].tipoCelda = PARADA;

        celdas[26][3].tipoCelda = PARADA;
        celdas[26][9].tipoCelda = PARADA;
        //    celdas[26][2].tipoCelda = PORTAL;
        this.setSize(SIZE_WIDTH, SIZE_HEIGHT);
    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    private void crearEdificio(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = OBSTACULO;
            }
        }
    }

    private void crearCalle(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = CALLE;
            }
        }
    }
}

package graficos;

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
                celdas[i][j].nPeatones = (int) Math.floor(Math.random() * (101));;
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

        for (int i = 0; i < 10; i++) {
            
        }
        insertarPeatones(2, 7, 2, 7);
        
        insertarPeatones(3,7,9,11);

        for (int i = 0; i < 15; i++) {
            celdas[1][i].tipoCelda = CALLE;
            celdas[1][i].nPeatones = 999;
        }

        for (int i = 0; i < 15; i++) {
            celdas[7][i].tipoCelda = CALLE;
            celdas[7][i].nPeatones = 999;
        }

        for (int i = 0; i < 15; i++) {
            celdas[13][i].tipoCelda = CALLE;
            celdas[13][i].nPeatones = 999;
        }
        for (int i = 0; i < 15; i++) {
            celdas[19][i].tipoCelda = CALLE;
            celdas[19][i].nPeatones = 999;
        }
        for (int i = 0; i < 15; i++) {
            celdas[25][i].tipoCelda = CALLE;
            celdas[25][i].nPeatones = 999;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][1].tipoCelda = CALLE;
            celdas[i][1].nPeatones = 999;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][7].tipoCelda = CALLE;
            celdas[i][7].nPeatones = 999;
        }

        for (int i = 0; i < 30; i++) {
            celdas[i][13].tipoCelda = CALLE;
            celdas[i][13].nPeatones = 999;
        }

        celdas[20][3].tipoCelda = PORTAL;
        celdas[20][9].tipoCelda = PORTAL;

        celdas[26][3].tipoCelda = PORTAL;
        celdas[26][9].tipoCelda = PORTAL;
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

    private void insertarPeatones(int x1, int x2, int y1, int y2) {
        int random = (int) Math.floor(Math.random() * (101));
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].nPeatones = random;
            }
        }
    }
}

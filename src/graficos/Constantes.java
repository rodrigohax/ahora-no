package graficos;

import java.awt.Color;
import java.util.Random;

public interface Constantes {

    public final int TAMANIO_CELDA = 31;
    public final int N_CELDAS_ANCHO = 31;
    public final int N_CELDAS_ALTO = 16;
    public final int SIZE_WIDTH = TAMANIO_CELDA * N_CELDAS_ANCHO;
    public final int SIZE_HEIGHT = TAMANIO_CELDA * N_CELDAS_ALTO;

    //Para manejar los tipos de celdas
    public final char JUGADOR = 'J';
    public final char CAMINO = 'V';
    public final char OBSTACULO = 'O';
    public final char VEHICULO = 'H';
    public final char PORTAL = 'R';
    //nuevas constantes
    public final char EDIFICIO = 'E';    
    public final char PEATON = 'P';
    public final char CALLE = 'C';
    public final char MICRO = 'X';
    public final char PARADA = 'Y';
    public final int NUMPEATONES = 1;
    
    public final int ALFA = 150;
    public final Color COLORGRIS = new Color(192, 192, 192, ALFA);
    public final Color COLORAMARILLO = new Color(255, 255, 128, ALFA);

    default int numeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
        return numero_aleatorio;
    }
}

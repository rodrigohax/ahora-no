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
    public final char CUADRA = 'E';
    public final char PEATON = 'P';
    public final char CALLE = 'C';
    public final char MICRO = 'X';
    public final char PARADA = 'Y';
    public final char CARTA = 'I';
    public final char PASOPEATONAL = 'Z';
    public final int PASAJERO = 'A';
    public final int PARED = 'T';
    
    public final int NPEATONES = 10;
    public final int NPORTALES = 3;
    public final int NCARTAS = 3;

}

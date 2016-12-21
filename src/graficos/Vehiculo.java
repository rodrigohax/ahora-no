package graficos;

import java.awt.Point;
import java.util.TimerTask;

public class Vehiculo extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda auto, celdaMovimiento;
    public int x, y;

    public Vehiculo(Laberinto laberinto, int x, int y) {
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(x, y, laberinto.celdas[x][y].tipoCelda);
        auto = new Celda(x, y, VEHICULO);
    }

    private void moverVehiculo() {
        if (celdaMovimiento.x > 0) {
            if (noHayPersona(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
        } else if (celdaMovimiento.x <= 0) {
            char temp = celdaMovimiento.tipoCelda;
            celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda;
           laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
            celdaMovimiento.x = this.x;
            celdaMovimiento.y = this.y;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = VEHICULO;
        }
    }

    private void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = VEHICULO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 0;
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = VEHICULO;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 2;
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = VEHICULO;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 3;
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = VEHICULO;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 1;
    }

    @Override
    public void run() {
        moverVehiculo();
        laberinto.lienzoPadre.repaint();
    }

    private boolean noHayPersona(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON;

    }
}

package graficos;

import java.awt.Point;

public class Peaton implements Constantes {

    public Laberinto laberinto;
    public Celda peaton, celdaMovimiento;
    public Point p1, p2, p3, p4;

    public Peaton(Laberinto laberinto, Point xp, Point yp, Point ep) {
        p1 = new Point(xp.x, xp.y);
        p2= new Point(yp.x,xp.y);
        p3= new Point(yp.x,yp.y);
        p4= new Point(ep.x,ep.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(this.p1.x, this.p1.y, laberinto.celdas[this.p1.x][this.p1.y].tipoCelda);
        peaton = new Celda(this.p1.x, this.p1.y,PEATON);
    }
    
    public void moverPeaton() {
        if (celdaMovimiento.x == p2.x && celdaMovimiento.y < p3.y && celdaMovimiento.y >= p2.y) {
            if (noHayPared(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                moverAbajo();
            }
        } else if (celdaMovimiento.x >= p1.x && celdaMovimiento.y == p1.y && celdaMovimiento.x < p2.x) {
            if (noHayPared(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
            }
        } else if (celdaMovimiento.x <= p3.x && celdaMovimiento.y == p3.y && celdaMovimiento.x > p4.x) {
            if (noHayPared(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
        } else if (celdaMovimiento.x == p4.x && celdaMovimiento.y <= p4.y && celdaMovimiento.y >= p1.y) {
            if (noHayPared(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
            }
        }
    }

    public void serCalleAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
    }

    public void serCalleArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
    }

    private void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 0;
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 2;
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 3;
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = PEATON;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 1;
    }

    private boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != JUGADOR;
    }
}

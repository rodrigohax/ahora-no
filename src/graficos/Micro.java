package graficos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Micro extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda auto, celdaMovimiento;
    public Point p1, p2, p3, p4, a, b, c, d;
    ArrayList<Peaton> peatones = new ArrayList<Peaton>();

    public Micro(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);

        a = new Point(xp.x, xp.y);
        b = new Point(yp.x, xp.y);
        c = new Point(yp.x, yp.y);
        d = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p1.x, p1.y, laberinto.celdas[p1.x][p1.y].tipoCelda);
        auto = new Celda(p1.x, p1.y, MICRO);

        for (int i = 0; i < NUMPEATONES; i++) {
            xp.x = xp.x - 1;
            peatones.add(new Peaton(laberinto, xp, yp));
        }

    }

    private void moverMicro() {
        for (int i = 0; i < peatones.size(); i++) {
            peatones.get(i).moverPeaton();
        }
        if (celdaMovimiento.x == p2.x && celdaMovimiento.y < p3.y && celdaMovimiento.y >= p2.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                if (esParada(celdaMovimiento.x + 1, celdaMovimiento.y + 1)) {
                    if (peatones.size() <= 0) {
                        System.out.println("TODOS LOS PEATONES HAN SIDO DEJADOS");
                    } else {
                        laberinto.repaint();
                        laberinto.lienzoPadre.repaint();
                        JOptionPane.showMessageDialog(null, "Peaton ha sido dejado en la parada");
                        peatones.remove(peatones.get(peatones.size() - 1));
                    }
                }
                moverAbajo();
            }
        } else if (celdaMovimiento.x >= p1.x && celdaMovimiento.y == p1.y && celdaMovimiento.x < p2.x) {
            if (noHayPersona(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
            }
        } else if (celdaMovimiento.x <= p3.x && celdaMovimiento.y == p3.y && celdaMovimiento.x > p4.x) {
            if (noHayPersona(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
            }
        } else if (celdaMovimiento.x == p4.x && celdaMovimiento.y <= p4.y && celdaMovimiento.y >= p1.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
                if (esParada(celdaMovimiento.x + 1, celdaMovimiento.y - 1)) {
                    if (peatones.size() <= 0) {
                        System.out.println("TODOS LOS PEATONES HAN SIDO DEJADOS");
                    } else {
                        JOptionPane.showMessageDialog(null, "Peaton ha sido dejado en la parada");
                        peatones.remove(peatones.get(peatones.size() - 1));
                    }
                }

            }
        }
    }

    private void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 0;
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 2;
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 3;
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;

        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexSprite = 1;
    }

    @Override
    public void run() {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        moverMicro();
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
    }

    private boolean noHayPersona(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON;

    }

    private boolean esParada(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda == PARADA;
    }
}

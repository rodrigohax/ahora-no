    package graficos;

    import inteligencia.BusquedaLadron;

    import java.awt.event.KeyEvent;
    import java.util.ArrayList;

    public class Ladron implements Constantes {

        public Laberinto laberinto;
        public Celda ladron, celdaMovimiento;
        public BusquedaLadron inteligencia;
        public static ArrayList<Portal> portales;
        public static int nCartasL;
        public Ladron(Laberinto laberinto, int x, int y, Jugador jugador) {
            this.laberinto = laberinto;
            celdaMovimiento = new Celda(x, y, laberinto.celdas[x][y].tipoCelda);
            ladron = new Celda(x, y, LADRON);
            laberinto.celdas[ladron.x][ladron.y].tipoCelda = LADRON;
            inteligencia = new BusquedaLadron(laberinto, this);
            nCartasL = 0;
        }

        void moverCelda(KeyEvent evento) {
            switch (evento.getKeyCode()) {
                case KeyEvent.VK_W:
                    System.out.println("Mover arriba");
                    moverCeldaArriba();
                    break;
                case KeyEvent.VK_S:
                    System.out.println("Mover abajo");
                    moverCeldaAbajo();
                    break;
                case KeyEvent.VK_A:
                    System.out.println("Mover izquierda");
                    moverCeldaIzquierda();
                    break;
                case KeyEvent.VK_D:
                    System.out.println("Mover derecha");
                    moverCeldaDerecha();
                    break;
            }
        }

        public boolean moverCeldaArriba() {
            if (ladron.y > 0) {
                if (noHayPared(ladron.x, ladron.y - 1)) {
                    avanzar(ladron.x, ladron.y - 1, 'U');
                    return true;
                }
            }
            return false;
        }

        public boolean moverCeldaAbajo() {
            if (ladron.y + 1 < N_CELDAS_ALTO) {
                if (noHayPared(ladron.x, ladron.y + 1)) {
                    avanzar(ladron.x, ladron.y + 1, 'D');
                    return true;
                }
            }
            return false;
        }

        public boolean moverCeldaIzquierda() {
            if (ladron.x > 0) {
                if (noHayPared(ladron.x - 1, ladron.y)) {
                    avanzar(ladron.x - 1, ladron.y, 'L');
                    return true;
                }
            }
            return false;
        }

        public boolean moverCeldaDerecha() {
            if (ladron.x + 1 < N_CELDAS_ANCHO) {
                if (noHayPared(ladron.x + 1, ladron.y)) {
                    avanzar(ladron.x + 1, ladron.y, 'R');
                    return true;
                }
            }
            return false;
        }

        public boolean noHayPared(int x, int y) {
            return laberinto.celdas[x][y].tipoCelda != OBSTACULO
                    && laberinto.celdas[x][y].tipoCelda != VEHICULO
                    && laberinto.celdas[x][y].tipoCelda != PEATON
                    && laberinto.celdas[x][y].tipoCelda != MICRO
                    && laberinto.celdas[x][y].tipoCelda != JUGADOR;
        }

        public boolean esJugador(int x, int y) {
            return laberinto.celdas[x][y].tipoCelda == JUGADOR;
        }

        private void avanzar(int x, int y, char mov) {
            laberinto.lienzoPadre.repaint();
            laberinto.lienzoPadre.validate();
            char temp;
            System.out.println(x + " " + y + " Cartas: " + nCartasL);
            switch (mov) {
                case 'D':
                    robaCartas(x,y);
                    temp = celdaMovimiento.tipoCelda;
                    celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                    laberinto.celdas[x][y - 1].tipoCelda = temp;
                    laberinto.celdas[x][y].tipoCelda = LADRON;
                    ladron.y = ladron.y + 1;
                    laberinto.celdas[ladron.x][ladron.y].indexSprite = 0;
                    break;
                case 'U':
                    robaCartas(x,y);
                    temp = celdaMovimiento.tipoCelda;
                    celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                    laberinto.celdas[x][y + 1].tipoCelda = temp;
                    laberinto.celdas[x][y].tipoCelda = LADRON;
                    ladron.y = ladron.y - 1;
                    laberinto.celdas[ladron.x][ladron.y].indexSprite = 2;
                    break;
                case 'R':
                    robaCartas(x,y);
                    temp = celdaMovimiento.tipoCelda;
                    celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                    laberinto.celdas[x - 1][y].tipoCelda = temp;
                    laberinto.celdas[x][y].tipoCelda = LADRON;
                    ladron.x = ladron.x + 1;
                    laberinto.celdas[ladron.x][ladron.y].indexSprite = 3;
                    break;
                case 'L':
                    robaCartas(x,y);
                    temp = celdaMovimiento.tipoCelda;
                    celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                    laberinto.celdas[x + 1][y].tipoCelda = temp;
                    laberinto.celdas[x][y].tipoCelda = LADRON;
                    ladron.x = ladron.x - 1;
                    laberinto.celdas[ladron.x][ladron.y].indexSprite = 1;
                    break;
            }
            laberinto.lienzoPadre.repaint();
            laberinto.lienzoPadre.validate();
        }

        private void robaCartas(int x, int y) {
            if (esJugador(x,y)) {
                Ladron.nCartasL=Jugador.nCartas;
                Jugador.nCartas=0;            
            }
        }
    }

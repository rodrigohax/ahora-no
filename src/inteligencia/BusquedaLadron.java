package inteligencia;

import graficos.Constantes;
import static graficos.Constantes.N_CELDAS_ALTO;
import static graficos.Constantes.N_CELDAS_ANCHO;
import graficos.Jugador;
import graficos.Laberinto;
import graficos.Ladron;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class BusquedaLadron extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Ladron ladron;
    public ArrayList<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    //para tener un busqueda anchura multiobjetivo
    public ArrayList<Estado> destinos;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito;
    public boolean parar;
    public Jugador jugador;

    public BusquedaLadron(Laberinto laberinto, Ladron ladron) {
        this.laberinto = laberinto;
        this.jugador = laberinto.lienzoPadre.jugador;
        this.ladron = ladron;
        colaEstados = new ArrayList<>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        destinos = new ArrayList<>();
        index_pasos = 0;
        exito = false;
        parar = false;
    }

    public boolean buscar(Estado inicial, Estado objetivo) {

        index_pasos = 0;
        colaEstados.add(inicial);
        historial.add(inicial);
        this.objetivo = objetivo;
        exito = false;

        if (inicial.equals(objetivo)) {
            exito = true;
        }

        while (!colaEstados.isEmpty() && !exito) {
            //temp = colaEstados.poll();
            temp = colaEstados.get(0);
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }

        if (exito) {
            System.out.println("Ruta calculada");
            this.calcularRuta();
            return true;
        } else {
            System.out.println("La ruta no pudo calcularse");
            return false;
        }

    }

    //distancia adversario
    public double distancia(int x1, int y1, int x2, int y2) {
        double valor;
        double parte1 = Math.pow(Math.abs(x1 - x2), 2);
        double parte2 = Math.pow(Math.abs(y1 - y2), 2);
        parte1 += parte2;
        valor = Math.sqrt(parte1);
        return valor;
    }

    private void moverArriba(Estado e) {
        if (e.y > 0) {
            if (laberinto.celdas[e.x][e.y - 1].puedeMoverse()) {
                Estado arriba = new Estado(e.x, e.y - 1, 'U', e);
                //              arriba.prioridad = laberinto.celdas[e.x][e.y - 1].npeatones;
                if (!historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    if (arriba.equals(objetivo)) {
                        objetivo = arriba;
                        exito = true;
                    }

                }
            }
        }
    }

    private void moverAbajo(Estado e) {
        if (e.y + 1 < N_CELDAS_ALTO) {
            if (laberinto.celdas[e.x][e.y + 1].puedeMoverse()) {
                Estado abajo = new Estado(e.x, e.y + 1, 'D', e);
                //              abajo.prioridad = laberinto.celdas[e.x][e.y + 1].npeatones;
                if (!historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    if (abajo.equals(objetivo)) {
                        objetivo = abajo;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverIzquierda(Estado e) {
        if (e.x > 0) {
            if (laberinto.celdas[e.x - 1][e.y].puedeMoverse()) {
                Estado izquierda = new Estado(e.x - 1, e.y, 'L', e);
//                izquierda.prioridad = laberinto.celdas[e.x - 1][e.y].npeatones;

                if (!historial.contains(izquierda)) {

                    colaEstados.add(izquierda);
                    historial.add(izquierda);

                    if (izquierda.equals(objetivo)) {

                        objetivo = izquierda;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverDerecha(Estado e) {

        if (e.x < N_CELDAS_ANCHO - 1) {
            if (laberinto.celdas[e.x + 1][e.y].puedeMoverse()) {
                Estado derecha = new Estado(e.x + 1, e.y, 'R', e);
                //             derecha.prioridad = laberinto.celdas[e.x + 1][e.y].npeatones;

                if (!historial.contains(derecha)) {
                    colaEstados.add(derecha);
                    historial.add(derecha);

                    if (derecha.equals(objetivo)) {
                        objetivo = derecha;
                        exito = true;
                    }
                }
            }
        }
    }

    public void calcularRuta() {
        Estado predecesor = objetivo;
        do {
            pasos.add(0, predecesor.oper);
            predecesor = predecesor.predecesor;
        } while (predecesor != null);
        index_pasos = pasos.size() - 1;

    }

    @Override
    public synchronized void run() {
        /*
        if (!parar) {
            colaEstados.clear();
            historial.clear();
            pasos.clear();
            Estado subinicial, subobjetivo;
            boolean resultado;
            do {
                subinicial = new Estado(ladron.ladron.x, ladron.ladron.y, 'N', null);
                subobjetivo = destinos.get(0);
                resultado = this.buscar(subinicial, subobjetivo);

                if (subinicial.equals(subobjetivo)) {
                    destinos.remove(subobjetivo);
                    System.out.println("en objetivo");
                } else if (!resultado) {

                    colaEstados.clear();
                    historial.clear();
                    pasos.clear();
                    destinos.remove(subobjetivo);

                }

                if (destinos.isEmpty()) {
                    this.cancel();
                }

            } while (!resultado && !destinos.isEmpty());

            if (pasos.size() > 1) {
                switch (pasos.get(1)) {
                    case 'D':
                        ladron.moverCeldaAbajo();
                        break;
                    case 'U':
                        ladron.moverCeldaArriba();
                        break;
                    case 'R':
                        ladron.moverCeldaDerecha();
                        break;
                    case 'L':
                        ladron.moverCeldaIzquierda();
                        break;
                }
                laberinto.lienzoPadre.repaint();
            }
        }
         */
        //Multiobjetivo

        if (!parar) {

            colaEstados.clear();
            historial.clear();
            pasos.clear();

            Estado subinicial, subobjetivo;
            boolean resultado;

            do {
                subinicial = new Estado(ladron.ladron.x,
                        ladron.ladron.y, 'N', null);

                subobjetivo = destinos.get(0);
                resultado = this.buscar(subinicial, subobjetivo);

                if (subinicial.equals(subobjetivo)) {
                    destinos.remove(subobjetivo);
                } else if (!resultado) {

                    colaEstados.clear();
                    historial.clear();
                    pasos.clear();
                    destinos.remove(subobjetivo);

                }

                if (destinos.isEmpty()) {
                    System.out.println("Se acabo a donde ir");
                    this.cancel();
                }

            } while (!resultado && !destinos.isEmpty());

            if (pasos.size() > 1) {
                switch (pasos.get(1)) {
                    case 'D':
                        ladron.moverCeldaAbajo();
                        break;
                    case 'U':
                        ladron.moverCeldaArriba();
                        break;
                    case 'R':
                        ladron.moverCeldaDerecha();
                        break;
                    case 'L':
                        ladron.moverCeldaIzquierda();
                        break;
                }
                laberinto.lienzoPadre.repaint();

            }

        }
    }
}

package graficos;

import inteligencia.Estado;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;

    public Vehiculo auto, auto2, auto3, auto4;
    public Micro micro;
    public Peaton peaton, peaton2;
    public Jugador jugador;
    public Ladron ladron;
    public Timer lanzadorTareas;

    public Graphics graficoBuffer;
    public Image imagenBuffer;
    public ArrayList<Portal> portales;

    public Lienzo() {
        laberinto = new Laberinto(this);
        portales = new ArrayList<>();
        // auto = new Vehiculo(laberinto, 29, 1);
        //   auto2 = new Vehiculo(laberinto, new Point(7, 1), new Point(13, 7));
//        auto3 = new Vehiculo(laberinto, new Point(1, 13), new Point(7, 1));
//        auto4 = new Vehiculo(laberinto, new Point(6, 5), new Point(4, 4));

        micro = new Micro(laberinto, new Point(19, 1), new Point(25, 13));
        jugador = new Jugador(laberinto, 0, 0);
   //     ladron = new Ladron(laberinto, 4, 4, jugador);

        try {
            fondo = ImageIO.read(new File("images/fondo.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        //escuchador eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                jugador.moverCelda(e);
                laberinto.lienzoPadre.repaint();
            }
        });

        for (int i = 4; i < 30; i = i + 6) {
            crearPortal(i, 5);
        }

        for (int i = 4; i < 30; i = i + 6) {
            crearPortal(i, 11);

        }

        lanzadorTareas = new Timer();
//        lanzadorTareas.scheduleAtFixedRate(auto, 0, 100);
        //     lanzadorTareas.scheduleAtFixedRate(auto2, 0, 100);
        //   lanzadorTareas.scheduleAtFixedRate(auto3, 0, 1300);
        //  lanzadorTareas.scheduleAtFixedRate(auto4, 0, 1300);
        lanzadorTareas.scheduleAtFixedRate(micro, 0, 1000);
//        lanzadorTareas.scheduleAtFixedRate(peaton2,0,400);

        jugador.inteligencia.destinos.add(new Estado(4, 5, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(10, 5, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(4, 5, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(16, 5, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(28, 11, 'N', null));
        jugador.inteligencia.destinos.add(new Estado(2, 2, 'N', null));

      //  ladron.inteligencia.destinos.add(new Estado(jugador.celdaMovimiento.x, jugador.celdaMovimiento.y, 'N', null));
       lanzadorTareas.scheduleAtFixedRate(jugador.inteligencia, 0, 1000);
//        lanzadorTareas.scheduleAtFixedRate(ladron.inteligencia, 0, 100);

    }

    @Override
    public void update(Graphics g) {
        if (graficoBuffer == null) {
            imagenBuffer = createImage(this.getWidth(), this.getHeight());
            graficoBuffer = imagenBuffer.getGraphics();
        }
        //volcamos color de fondo e imagen en el nuevo buffer grafico
        graficoBuffer.setColor(getBackground());
        graficoBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        graficoBuffer.drawImage(fondo, 0, 0, null);
        laberinto.update(graficoBuffer);
        //pintamos la imagen previa
        g.drawImage(imagenBuffer, 0, 0, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("N° Peatones:\n" + laberinto.celdas[3][4].nPeatones, laberinto.celdas[3][4].x, laberinto.celdas[3][4].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[9][4].nPeatones, laberinto.celdas[9][4].x, laberinto.celdas[9][4].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[15][4].nPeatones, laberinto.celdas[15][4].x, laberinto.celdas[15][4].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[21][4].nPeatones, laberinto.celdas[21][4].x, laberinto.celdas[21][4].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[27][4].nPeatones, laberinto.celdas[27][4].x, laberinto.celdas[27][4].y);

        g.drawString("N° Peatones:\n" + laberinto.celdas[3][10].nPeatones, laberinto.celdas[3][10].x, laberinto.celdas[3][10].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[9][10].nPeatones, laberinto.celdas[9][10].x, laberinto.celdas[9][10].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[15][10].nPeatones, laberinto.celdas[15][10].x, laberinto.celdas[15][10].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[21][10].nPeatones, laberinto.celdas[21][10].x, laberinto.celdas[21][10].y);
        g.drawString("N° Peatones:\n" + laberinto.celdas[27][10].nPeatones, laberinto.celdas[27][10].x, laberinto.celdas[27][10].y);

        g.drawString("N° Peatones:\n" + laberinto.celdas[3][1].nPeatones, laberinto.celdas[3][1].x, laberinto.celdas[3][1].y - 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[9][1].nPeatones, laberinto.celdas[9][1].x, laberinto.celdas[9][1].y - 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[15][1].nPeatones, laberinto.celdas[15][1].x, laberinto.celdas[15][1].y - 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[21][1].nPeatones, laberinto.celdas[21][1].x, laberinto.celdas[21][1].y - 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[27][1].nPeatones, laberinto.celdas[27][1].x, laberinto.celdas[27][1].y - 10);

        g.drawString("N° Peatones:\n" + laberinto.celdas[3][14].nPeatones, laberinto.celdas[3][14].x, laberinto.celdas[3][14].y + 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[9][14].nPeatones, laberinto.celdas[9][14].x, laberinto.celdas[9][14].y + 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[15][14].nPeatones, laberinto.celdas[15][14].x, laberinto.celdas[15][14].y + 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[21][14].nPeatones, laberinto.celdas[21][14].x, laberinto.celdas[21][14].y + 10);
        g.drawString("N° Peatones:\n" + laberinto.celdas[27][14].nPeatones, laberinto.celdas[27][14].x, laberinto.celdas[27][14].y + 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("Trafico:\n" + laberinto.celdas[19][7].nPeatones, laberinto.celdas[19][7].x - 128, laberinto.celdas[19][7].y + 12);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    private void crearPortal(int x, int y) {
        portales.add(new Portal(laberinto, x, y));
    }
}

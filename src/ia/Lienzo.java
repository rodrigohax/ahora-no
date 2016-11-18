package ia;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes {

    public Laberinto laberinto;
    public Image fondo;

    public Vehiculo auto, auto2;
    public Peaton peaton, peaton2;
    public Jugador jugador;
    public Timer lanzadorTareas;

    public Graphics graficoBuffer;
    public Image imagenBuffer;

    public Lienzo() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(19, 7);
        
        //Point p3 = new Point(10, 12);
        //Point p4 = new Point(33, 17);
        
        Point p5 = new Point(2, 2);
        Point p6 = new Point(18, 6);
        laberinto = new Laberinto(this);
        auto = new Vehiculo(laberinto, p1, p2);
        //auto2 = new Vehiculo(laberinto, p3, p4);
        peaton = new Peaton(laberinto, p5, p6);
        jugador = new Jugador(laberinto);
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
        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(auto, 0, 500);
//        lanzadorTareas.scheduleAtFixedRate(auto2, 0, 400);
        lanzadorTareas.scheduleAtFixedRate(peaton, 0, 1300);
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
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }
}

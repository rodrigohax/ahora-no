package graficos;

import java.awt.Color;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {

    //atributos
    public int x;
    public int y;
    public char tipoCelda;
    public int indexSprite;
    public BufferedImage[] spriteJugador, spriteVehiculo;
    //nuevos atributos para manejar imagenes
    public BufferedImage jugador, obstaculo, camino, vehiculo, portal;
    public BufferedImage edificio, acera, carretera, peaton, carta;
    public int nPeatones;

    //constructor, inicializa los atributos
    public Celda(int x, int y, char tipo) {
        this.x = x;
        this.y = y;
        this.tipoCelda = tipo;
        indexSprite = 0;
        try {
            //obstaculo = ImageIO.read(new File("images/obstaculo.png"));
            //camino = ImageIO.read(new File("images/camino.png"));
            vehiculo = ImageIO.read(new File("images/vehiculo.png"));
            //portal = ImageIO.read(new File("images/portal.png"));
            edificio = ImageIO.read(new File("images/edificio.png"));
            acera = ImageIO.read(new File("images/acera.png"));
            carretera = ImageIO.read(new File("images/carretera.png"));
            peaton = ImageIO.read(new File("images/peaton.png"));
            jugador = ImageIO.read(new File("images/jugador.png"));
            carta = ImageIO.read(new File("images/carta.png"));

            spriteJugador = cargarSprite(jugador, 2, 2);
            spriteVehiculo = cargarSprite(vehiculo, 2, 2);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public Celda(int x, int y, char tipo, int nPeatones) {
        this.x = x;
        this.y = y;
        this.tipoCelda = tipo;
        this.nPeatones = nPeatones;
    }

    @Override
    public void update(Graphics g) {
        //   g.drawString(Integer.toString(nPeatones), x + 10, y + 10);
        switch (tipoCelda) {
            case JUGADOR:
                g.drawImage(spriteJugador[indexSprite], x, y, null);
                for (int i = 0; i < Jugador.nCartas; i++) {
                    g.setColor(Color.black);
                    g.setFont(new Font("TimesRoman", Font.BOLD, 12));
                    g.drawString(Integer.toString(i+1), x - 25 + i * 25, y - 25);
                    g.drawImage(carta, x - 25 + i * 25, y - 25, this);
                }
                break;
            case OBSTACULO:
                g.drawImage(obstaculo, x, y, this);
                // 
                break;
            case CAMINO:
                //g.setColor(COLORGRIS);
                //g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case VEHICULO:
                g.drawImage(spriteVehiculo[indexSprite], x, y, this);
                break;
            case PEATON:
                g.drawImage(peaton, x, y, this);
                break;
            case CALLE:
                // g.setColor(COLORAMARILLO);
                // g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case PORTAL:
                g.setColor(YELLOW);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case MICRO:
                g.setColor(GREEN);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case PARADA:
                break;
            case PASOPEATONAL:
                break;
            case CUADRA:
                g.setColor(BLUE);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case CARTA:
                g.setColor(WHITE);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
            case PARED:
                g.setColor(WHITE);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    private BufferedImage[] cargarSprite(BufferedImage imagen, int x, int y) {
        BufferedImage sprite[] = new BufferedImage[x * y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sprite[(i * 2) + j] = imagen.getSubimage(i * TAMANIO_CELDA, j * TAMANIO_CELDA + 1, TAMANIO_CELDA, TAMANIO_CELDA);
            }
        }
        return sprite;
    }
}

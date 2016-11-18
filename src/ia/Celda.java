package ia;

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
    public BufferedImage edificio, acera, carretera, peaton;

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

            spriteJugador = cargarSprite(jugador, 2, 2);
            spriteVehiculo = cargarSprite(vehiculo, 2, 2);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void update(Graphics g) {
        switch (tipoCelda) {
            case JUGADOR:
                g.drawImage(spriteJugador[indexSprite], x, y, null);
                break;
            case OBSTACULO:
                g.drawImage(obstaculo, x, y, this);
                break;
            case CAMINO:
                //g.setColor(COLORGRIS);
                //g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case VEHICULO:
                g.drawImage(spriteVehiculo[indexSprite], x, y, this);
                break;
            case EDIFICIO:
                g.drawImage(edificio, x, y, this);
                break;
            case PEATON:
                g.drawImage(peaton, x, y, this);
                break;
            case CALLE:
               // g.setColor(COLORAMARILLO);
               // g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
            case PORTAL:
                g.setColor(COLORAMARILLO);
                g.fillRect(x, y, TAMANIO_CELDA, TAMANIO_CELDA);
                break;
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

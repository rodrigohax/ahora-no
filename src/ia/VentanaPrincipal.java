package ia;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes {

    //nuestra clase se compone de un lienzo de dibujo (herada de canvas)
    public Lienzo lienzo;

    //constructor
    public VentanaPrincipal() {
        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        this.getContentPane().add(lienzo);
        this.setSize(970, 500);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

/**
 *
 * @author Rodrigo
 */
public class Portal implements Constantes {
    public Laberinto laberinto;
    public Celda portal;
    public int id;
    public int nCartas;

    Portal(Laberinto laberinto, int x, int y) {
        this.laberinto = laberinto;
        portal = new Celda(x, y, PORTAL);
    }
}
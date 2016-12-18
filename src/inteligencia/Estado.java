/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligencia;

/**
 *
 * @author rodrigo
 */

public class Estado implements Comparable{
    
    public int x;
    public int y;
    //'N'=nada, 'L': izquierda, 'R': derecha, 'U': Arriba, 'D': abajo
    public char oper; 
    public Estado predecesor;
    public double prioridad;
    
    
    public Estado(int x, int y, char oper,Estado predecesor) {
        this.x=x;
        this.y=y;
        this.oper=oper;
        this.predecesor=predecesor;
        
    }
    
    @Override
    public boolean equals(Object x) {
        Estado e=(Estado)x;
        return this.x==e.x && this.y==e.y;
    }
        
    @Override
    public String toString() {
        return "("+x+","+y+"): Prioridad= "+this.prioridad;
    }
    
    @Override
    public int compareTo(Object o) {
        Estado e=(Estado)o;
        if ( this.prioridad == e.prioridad ) return 0;
        else {
            if ( this.prioridad > e.prioridad ) return 1;
            else return -1;
        }
    }
}



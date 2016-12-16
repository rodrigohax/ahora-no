/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligencia;
import graficos.Jugador;
import graficos.Laberinto;
import graficos.Constantes;
import java.util.ArrayList;
import java.util.TimerTask;

public class Busqueda extends TimerTask implements Constantes{
    
    public Laberinto laberinto;
    public Jugador jugador;
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
    
    public Busqueda(Laberinto laberinto,Jugador jugador) {
        
        this.laberinto=laberinto;
        this.jugador=jugador;
        colaEstados=new ArrayList<>();
        historial=new ArrayList<>();
        pasos=new ArrayList<>();
        destinos=new ArrayList<>();
        index_pasos=0;
        exito=false;
        parar=false;
    }
    
    public boolean buscar(Estado inicial,Estado objetivo) {
        
        System.out.println("Estado inicial"+inicial.toString());
        System.out.println("Estado objetivo"+objetivo.toString());
        index_pasos=0;
        colaEstados.add(inicial);
        historial.add(inicial);
        this.objetivo=objetivo;
        exito=false;
        
        if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ){
            
            temp=colaEstados.get(0);
            //System.out.println(temp.toString());
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ) {
            System.out.println("Ruta calculada");
            this.calcularRuta();
            return true;
        }
        else {
            System.out.println("La ruta no pudo calcularse");
            return false;
        }
        
    }
    
    private void moverArriba(Estado e) {  
        if ( e.y > 0 ) {
            if ( laberinto.celdas[e.x][e.y-1].tipoCelda != 'O' ) { 
                 Estado arriba=new Estado(e.x,e.y-1,'U',e);
                 if ( !historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    if ( arriba.equals(objetivo)) {
                        objetivo=arriba;
                        exito=true;
                    }
                    
                 }
            }     
        }
    }
    
    private void moverAbajo(Estado e) {
       
        if ( e.y+1 < N_CELDAS_ALTO ) { 
            if ( laberinto.celdas[e.x][e.y+1].tipoCelda != 'O' ) {
                 Estado abajo=new Estado(e.x,e.y+1,'D',e);   
                 if ( !historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    //laberinto.celdas[e.x][e.y+1].tipoCelda='A';
                    if ( abajo.equals(objetivo)) {
                        //laberinto.celdas[e.x][e.y+1].tipoCelda='P';
                        objetivo=abajo;
                        exito=true;
                    }
                 }   
            }     
        }
    }    
    
    private void moverIzquierda(Estado e) {
        if ( e.x > 0 ) {
            if ( laberinto.celdas[e.x-1][e.y].tipoCelda != 'O' ) {
                Estado izquierda=new Estado(e.x-1,e.y,'L',e);
                if ( !historial.contains(izquierda)) {
                    
                   colaEstados.add(izquierda);
                   historial.add(izquierda);
                   
                   if ( izquierda.equals(objetivo)) {
                       
                       objetivo=izquierda;
                       exito=true;
                   }
                }   
            }    
        }
    }    
    
    private void moverDerecha(Estado e) {
        
        if ( e.x < N_CELDAS_ANCHO-1 ) {
            if ( laberinto.celdas[e.x+1][e.y].tipoCelda != 'O' ) {
               Estado derecha=new Estado(e.x+1,e.y,'R',e); 
               if ( !historial.contains(derecha)){
                 colaEstados.add(derecha);
                 historial.add(derecha);
                 
                 if ( derecha.equals(objetivo)) {
                     objetivo=derecha;
                     exito=true;
                 }
               }  
            }     
        }
    }    
    
    public void calcularRuta() {
        Estado predecesor=objetivo;
        do{
            pasos.add(0,predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos=pasos.size()-1;
        
    }

    @Override
    public void run() {
       
       if ( ! parar ) {
        
          colaEstados.clear();
          historial.clear();
          pasos.clear(); 
       
          Estado subinicial,subobjetivo;
          boolean resultado;
          
          do{
              
              subinicial=new Estado(jugador.jugador.x,
                                jugador.jugador.y,'N',null);
              
              subobjetivo=destinos.get(0);
              resultado=this.buscar(subinicial,subobjetivo);
          
              if ( subinicial.equals(subobjetivo) ) 
                  destinos.remove(subobjetivo);
              else 
                  if ( !resultado) {
                      
                      colaEstados.clear();
                      historial.clear();
                      pasos.clear(); 
                      destinos.remove(subobjetivo);
                      
                  }
             
          
              if ( destinos.isEmpty() ) {
                 System.out.println("Se acabo a donde ir");
                 this.cancel();
              }
          
          }while(!resultado && !destinos.isEmpty());
      
          if ( pasos.size() > 1 ) {
             switch(pasos.get(1)) {
                case 'D': jugador.moverCeldaAbajo();break;
                case 'U': jugador.moverCeldaArriba(); break;
                case 'R': jugador.moverCeldaDerecha();break;
                case 'L': jugador.moverCeldaIzquierda();break;
             }
          
          
             laberinto.lienzoPadre.repaint();  
           
          }
           
       }
       
    }   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leilaoservidormulticast.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author lucas
 */
public class Temporizador {
    
    /*
    
    public int segundos = 10;
    int delay = 5000;   // delay de 5 seg.
    int interval = 1000;  // intervalo de 1 seg.
    Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            
            public void run() {
                   
            }
            
          
            System.out.println(segundos + " Segundos");   
            
            
        }, delay);
    
    */
    
    
    
    public Timer timer = new Timer();
    
    public int segundos = 25; 
    
    public int delay = 5000; 
    
    public boolean estaRodando = true;
    
    public TimerTask task = new TimerTask() {
        
        @Override
        public void run() {
              
            if(segundos > 0){
                segundos--;
                System.out.println(segundos + " Segundos"); 
            }   
            
           if(segundos == 0){
               estaRodando = false;
               task.cancel();
               System.out.println("task encerrada");
               
           }
            
        }
    };


}



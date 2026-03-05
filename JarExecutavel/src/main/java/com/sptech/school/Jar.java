package com.sptech.school;
import java.time.LocalDateTime;
import java.util.Random;
public class Jar {
    public static void main(String[] args) throws InterruptedException{
        Random aleatorio  = new Random();
        LocalDateTime agora = LocalDateTime.now();

        while (true){
            int cpu = aleatorio.nextInt(101);
            int ram = aleatorio.nextInt(101);
            int disco = aleatorio.nextInt(101);

            System.out.println("Status atuais do servidor");
            System.out.println("DATA DA COLETA: " + agora + " CPU: " + cpu + "%");
            System.out.println("DATA DA COLETA: " + agora + " RAM: " + ram + "%");
            System.out.println("DATA DA COLETA: " + agora + " DISCO: " + disco + "%");

            if (cpu > 75){
                System.out.println("Alerta, o nível uso da CPU está acima do esperado!");
            }
            if (ram > 80){
                System.out.println("Alerta, o nível uso da RAM está acima do esperado!");
            }
            if (disco > 85){
                System.out.println("Alerta, o nível uso do DISCO está acima do esperado!");
            }
            System.out.println();
            Thread.sleep(10000);
        }
    }
}

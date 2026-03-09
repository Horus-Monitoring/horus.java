package com.sptech.school;

public class Main {

    public static void main(String[] args) throws InterruptedException {

      JarFinal log1 = new JarFinal("Júlio Dantas", "ADM", "Conectado");

        log1.iniciar();

        while (true){
            log1.logHardware();
            System.out.println();
            Thread.sleep(10000);
        }
    }

}






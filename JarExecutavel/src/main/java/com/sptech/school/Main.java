package com.sptech.school;

public class Main {

    public static void main(String[] args) throws InterruptedException {

      JarFinal log = new JarFinal();

        log.logHardware();

        while (true){
            log.logHardware();
            System.out.println();
            Thread.sleep(10000);
        }

    }

}






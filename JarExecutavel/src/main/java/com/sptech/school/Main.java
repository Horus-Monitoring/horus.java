package com.sptech.school;

public class Main {

    public static void main(String[] args) throws InterruptedException {

      JarFinal log = new JarFinal();

        log.logHardware();

        while (true){
            log.logHardware();
            Thread.sleep(10000);
        }

    }

}






package com.sptech.school;

import java.util.Random;
import java.util.Scanner;

public class SlackLog {
    String usuario;
    String nivelPermissao;
    String statusServer;
    int contadorAlertas;
    int tipoServer;
    //1-ASM 2-Planos de Voo etc

    public SlackLog(String usuario,String nivelPermissao,String statusServer){
        this.usuario = usuario;
        this.nivelPermissao = nivelPermissao;
        this.statusServer = statusServer;
    }

    void iniciar(){
        System.out.println("\n"+"Iniciando Sessão...");
        System.out.println("Sessão iniciada por: "+usuario +" - " + nivelPermissao+"\n");


            Scanner entrada = new Scanner(System.in);
            System.out.println("Selecione o Servidor que você deseja observar: " + "\n" +
                    "1 - Servidor de Gestão de tráfego aéreo (ASM) " + "\n"
                    +"2 - Servidor de Planos de voo / Correlação de rastreamento " + "\n" +
                    "3- Encerrar Sessão");
            String opcao = entrada.nextLine();

            statusServer="Conectado";
            if(opcao.equals("1")){
                System.out.println("\n"+"Conectando com o Servidor de Gestão de tráfego aéreo (ASM)..");
                System.out.println("Status: "+ statusServer);
                tipoServer=1;
            }else if(opcao.equals("2")){
                System.out.println("\n"+"Conectando com o Servidor de Planos de voo / Correlação de rastreamento)..");
                System.out.println("Status: "+ statusServer);
                tipoServer=2;
            }else{
                System.out.println("Encerrando a sessão...");
            }
    }

void logHardware() {
    Random numero = new Random();

    double valorCPU = numero.nextDouble(8000);
    double valorRam = numero.nextDouble(8000);

    double porcentCPU = (valorCPU / 8000) * 100;
    double porcentRAM = (valorRam / 8000) * 100;

    if (tipoServer == 1 && (porcentCPU >= 80 && porcentCPU <= 95)) {
        System.out.println("Métrica Atual: " + porcentCPU);
        System.out.println(":warning: ALERTA CRÍTICO! Uso de CPU elevado!");

    } else if (tipoServer == 2 && (porcentRAM >= 70 && porcentRAM <= 80)){
        System.out.println("Métrica Atual: " + porcentRAM);
    System.out.println(":warning: ALERTA CRÍTICO!Uso de RAM elevado!");
} else{
        if(tipoServer==1) {
            System.out.println("Métrica Atual: " + porcentCPU);
            System.out.println("Situação: Estável.");
        }
else{
        System.out.println("Métrica Atual: "+ porcentRAM);
System.out.println("Situação: Estável.");
}
    }
        }
}



package com.sptech.school;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class JarFinal {
    String usuario;
    String nivelPermissao;
    String statusServer;
    int[] contadorAlertas =  new int[10];
    int tipoServer;


    public JarFinal(String usuario, String nivelPermissao, String statusServer) {
        this.usuario = usuario;
        this.nivelPermissao = nivelPermissao;
        this.statusServer = statusServer;
    }

    void iniciar() {
        System.out.println("\n" + "Iniciando Sessão...");
        System.out.println("Sessão iniciada por: " + usuario + " - " + nivelPermissao + "\n");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Selecione o Servidor que você deseja observar: " + "\n" +
                "1 - Servidor de Gestão de tráfego aéreo (ASM) " + "\n"
                + "2 - Servidor de Planos de voo / Correlação de rastreamento " + "\n" +
                "3- Encerrar Sessão");
        int opcao = entrada.nextInt();
        statusServer = "Conectado";
        if (opcao == 1) {
            System.out.println("\n" + "Conectando com o Servidor de Gestão de tráfego aéreo (ASM)..");
            System.out.println("Status: " + statusServer);
            tipoServer = 1;
        } else if (opcao == 2) {
            System.out.println("\n" + "Conectando com o Servidor de Planos de voo / Correlação de rastreamento)..");
            System.out.println("Status: " + statusServer);
            tipoServer = 2;
        } else {
            System.out.println("Encerrando a sessão...");
        }
    }

    void logHardware() {
        Random numero = new Random();
        double valorCPU = numero.nextInt(101);
        double valorRam = numero.nextInt(101);

        LocalDateTime agora = LocalDateTime.now();

        if (tipoServer == 1 && (valorCPU >= 80)) {
            System.out.println("Métrica Atual: " + valorCPU + "%");
            System.out.println("ALERTA CRÍTICO! Uso de CPU elevado! - " + agora);
        } else if (tipoServer == 2 && (valorRam >= 70)) {
            System.out.println("Métrica Atual - RAM: " + valorRam  + "%");
            System.out.println("ALERTA CRÍTICO!Uso de RAM elevado - " + agora);
        } else {
            if (tipoServer == 1) {
                System.out.println("Métrica Atual - CPU: " + valorCPU + "%");
                System.out.println("Situação: Estável.");
            } else {
                System.out.println("Métrica Atual: " + valorRam  + "%");
                System.out.println("Situação: Estável.");
            }
        }
    }
}


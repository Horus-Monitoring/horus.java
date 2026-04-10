package com.sptech.school;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class JarFinal {

    public static final String[] severidades = {"Crítica","Alta","Média", "Baixa"};
    public static final String[] status = {"Online", "Offline", "Atenção", "Crítico"};
    public static final String[] componentes = {"CPU", "RAM", "Disco"};
    public static final String[] servidores = {"[Tag servidor] [IP]", "[Tag servidor] [IP]", "[Tag servidor] [IP]"};

    void logHardware() {
        Random random = new Random();
        double valorComponente = random.nextInt(0, 101);
        double limite = 70.0;
        boolean gerarIndidente = false;

        DateTimeFormatter data_hora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String tempoReal = LocalDateTime.now().format(data_hora);
        String nivelChamado = severidades[0];
        String nivelStatus = status[0];
        String tipoComponete = componentes[random.nextInt(componentes.length)];
        String servidor = servidores[random.nextInt(servidores.length)];

        if (valorComponente == 0){
            // servidor offline ou sem conexão
            nivelStatus = status[1]; // offline
            nivelChamado = severidades[0]; // crítica
            gerarIndidente = true;
        } else if (valorComponente >= (limite * 0.7) && valorComponente < (limite * 0.8)){
            // componente acima do limite em 70% e menor que 80% do limite
            nivelStatus = status[0]; // online
            nivelChamado = severidades[3]; // baixa
            gerarIndidente = true;
        } else if (valorComponente >= (limite * 0.8) && valorComponente < (limite * 0.9)){
            // componente acima do limite em 80% e abaixo de 90%
            nivelStatus = status[2]; // atenção
            nivelChamado = severidades[2]; // média
            gerarIndidente = true;
        } else if (valorComponente >= (limite * 0.9) && valorComponente < limite) {
            // componente acima do limite em 90% e baixo de 100%
            nivelStatus = status[3]; // crítico
            nivelChamado = severidades[1]; // alta
            gerarIndidente = true;
        }else if(valorComponente >= limite){
            nivelStatus = status[3]; // crítico
            nivelChamado = severidades[0]; // crítica
            gerarIndidente = true;
        }

        if(gerarIndidente){
            System.out.println(tempoReal + tipoComponete + nivelStatus + nivelChamado);
        }
    }
}


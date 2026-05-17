package com.sptech.school.service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class RelatorioService {

    public void gerarRelatorio(){
        System.out.println("Gerando relatório...");
    }

    public Path buscarJSON(){
        Path caminho = Paths.get("C:/Users/ricar/Downloads/dashboard (1).json");
        if(Files.exists(caminho)) {
            System.out.println("Arquivo encontrado.");
            return caminho;
        } else {
            System.out.println("Arquivo não encontrado.");
            return null;
        }
    }
    /*

    buscar o JSON
    ler o JSON
    buscar os dados no BD MySQL
    juntar dados json e mysql
    estruturar o texto do relatório
    salvar o PDF
    enviar o PDF
    **/
}

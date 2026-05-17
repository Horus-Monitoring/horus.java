package com.sptech.school.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class RelatorioService {

    public void gerarRelatorio(){
        System.out.println("Gerando relatório...");
    }

    public Path buscarJSON(){
        /*Path caminho = Paths.get(
                "client",
                "empresa_1",
                "c0:35:32:c7:0b:59",
                "dashboard.json"
        );
        Para buscar na S3?
        */
        Path caminho = Paths.get("C:/Users/ricar/Downloads/dashboard (1).json");
        if(Files.exists(caminho)) {
            System.out.println("Arquivo encontrado.");
            return caminho;
        } else {
            throw new RuntimeException("Arquivo não encontrado.");
        }
    }

    public JsonNode lerJSON(Path caminho) throws IOException {
        ObjectMapper leitor = new ObjectMapper();
        return leitor.readTree(caminho.toFile());
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

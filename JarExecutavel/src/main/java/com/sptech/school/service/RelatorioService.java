package com.sptech.school.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

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
        Path caminho = Paths.get("C:\\Users\\ricar\\Downloads\\dashboard (2).json");
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

    public String gerarTexto (JsonNode json, List<RelatorioData> mysql){

        RelatorioData infosUsuario = mysql.getFirst();
        StringBuilder logDeAlertas = new StringBuilder();

        String informacoes = """
                 Relatório de Mudança de Turno
               
               
                Usuário solicitante: %s.
                CPF: %s.
                Email: %s.
                Função: %s da %s.
                
                ----- Informações do Servidor -----
                Mac Address: %s.
                Nome: %s.
                Status do Servidor: %s.
                
                """.formatted(
                infosUsuario.getNome(),
                infosUsuario.getCpf(),
                infosUsuario.getEmail(),
                infosUsuario.getFuncao(),
                infosUsuario.getRazaoSocial(),
                infosUsuario.getMacAddress(),
                infosUsuario.getHostname(),
                infosUsuario.getStatusServidor()
        );
        String metricas = """
                ---  Métricas nas Últimas 24h   ---
                Ao longo do último turno, o servidor apresentou %s %% de perda de pacotes.
                A latência média registrada foi de %s ms, com uma taxa de %s %% de atualização do ADS-B.
                Dessa forma, %s rotas foram impactadas.
                
                A perda de pacotes para cada serviço do SAGITARIO corresponde a:
                  - Serviço de Rastreamento: %s%%;
                  - Serviço de Rotas: %s%%;
                  - Serviço de Correlação: %s%%;
                  - Serviço de API Gateway: %s%%;
                  - Serviço de Banco de Dados: %s%%;
                  - Sync Service: %s%%;
                -----      Logs de Alertas    -----
                """.formatted(
                        json.get("kpis").get("perda_pacotes"),
                        json.get("kpis").get("latencia_media"),
                        json.get("kpis").get("adsb_update"),
                        json.get("kpis").get("rotas_sem_atualizacao"),
                        json.get("perda_pacotes_servico").get("Rastreamento"),
                        json.get("perda_pacotes_servico").get("Rotas"),
                        json.get("perda_pacotes_servico").get("Correlação"),
                        json.get("perda_pacotes_servico").get("API Gateway"),
                        json.get("perda_pacotes_servico").get("Banco de Dados"),
                        json.get("perda_pacotes_servico").get("Sync Service")
                    );

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (RelatorioData r : mysql) {
            String status_servidor = r.getStatusServidor();
            String tipo = r.getTipoComponente();
            String limite = r.getLimite().toString();
            String unidade_medida = r.getUnidadeMedida();
            String data_alerta = r.getDataAlerta().format(dataFormatada);
            String criticidade = r.getCriticidade();
            String status_alerta = r.getStatusAlerta();

            String texto = """
                    ----------------------------
                    Em %s, houve um alerta %s, deixado condição do servidor em %s.
                    O problema ocorreu no componente %s, cujo limite definido é %s %s.
                    Situação: %s.
                    """.formatted(
                            data_alerta,
                            criticidade,
                            status_servidor,
                            tipo,
                            limite,
                            unidade_medida,
                            status_alerta
                    );
            logDeAlertas.append(texto);
        }

        return informacoes + metricas + logDeAlertas;
    }


    /*

    buscar o JSON (OK)
    ler o JSON (OK)
    buscar os dados no BD MySQL
    juntar dados json e mysql
    estruturar o texto do relatório
    salvar o PDF
    enviar o PDF
    **/
}

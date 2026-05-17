package com.sptech.school.app;

import com.sptech.school.JarFinal;
import com.sptech.school.config.Jira;
import com.sptech.school.config.MySQLConnection;
import com.sptech.school.config.Slack;
import com.sptech.school.JarFinal;
import java.io.IOException;

import com.sptech.school.service.RelatorioData;
import com.sptech.school.service.RelatorioRepository;
import com.sptech.school.service.RelatorioService;
import org.json.JSONObject;


public class App {

    public static void main(String[] args) throws Exception {


        MySQLConnection conexao = new MySQLConnection();
        RelatorioRepository data = new RelatorioRepository();
        data.buscarDados("ricardo@horus.com");

        /*JSONObject json = new JSONObject();

        String baseUrl = "https://horusmonitoring.atlassian.net";
        String email = "horusmonitoring@outlook.com.br";
        String apiToken = "";
        Jira jira = new Jira(baseUrl, email, apiToken);

        while (true){
            JarFinal log = new JarFinal();
            String mensagem = log.logHardware();
            json.put("text", mensagem);
            System.out.println(mensagem);

            if(mensagem != null){
                String response = jira.createIssue(
                        "KAN",
                        log.getEvento(),
                        "Task",
                        log.getNivelChamado()
                );
            }

            Slack.sendMessage(json);
            Thread.sleep(10000);
        }*/

    }
}

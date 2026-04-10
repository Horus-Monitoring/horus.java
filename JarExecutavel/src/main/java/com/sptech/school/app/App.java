package com.sptech.school.app;

import com.sptech.school.JarFinal;
import com.sptech.school.config.Slack;
import com.sptech.school.JarFinal;
import java.io.IOException;
import org.json.JSONObject;


public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        JSONObject json = new JSONObject();

        JarFinal log = new JarFinal();

        while (true){
            json.put("text", log.logHardware());
            Thread.sleep(10000);

            Slack.sendMessage(json);
        }

    }
}

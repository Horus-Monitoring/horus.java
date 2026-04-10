package com.sptech.school.app;

import com.sptech.school.config.Slack;
import java.io.IOException;
import org.json.JSONObject;


public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        JSONObject json = new JSONObject();

        json.put("text", "Fácil né? :shrug:");

        Slack.sendMessage(json);
    }
}

package net.awesomepwoered.valhallaslack.Utilities;

import com.google.gson.JsonObject;
import net.awesomepwoered.valhallaslack.ValhallaSlack;
import net.md_5.bungee.api.ProxyServer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by John on 12/2/2014.
 */
public class Messenger {

    String hookMessages = "https://hooks.slack.com/services/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";
    String hookResponses = "https://hooks.slack.com/services/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";
    String playerHead = "https://cravatar.eu/helmavatar/MHF_ArrowRight/100.png";
    String headTemplate = "https://cravatar.eu/helmhead/%REPLACE/100.png";

    public void sendResponse(String m) {
        JsonObject jo = new JsonObject();
        jo.addProperty("username", "Valhalla");
        jo.addProperty("text", m);
        jo.addProperty("icon_url", playerHead);
        doPost("payload=" + jo.toString(), hookResponses);
    }

    public void sendMessage(String m, String player) {
        JsonObject jo = new JsonObject();
        jo.addProperty("username", player);
        jo.addProperty("text", m.replace("&", "%26"));
        jo.addProperty("icon_url", headTemplate.replace("%REPLACE", player));
        doPost("payload=" + jo.toString(), hookMessages);
    }


    public void doPost(final String message, final String channel) {
        ProxyServer.getInstance().getScheduler().runAsync(ValhallaSlack.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(channel);
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    try (BufferedOutputStream B = new BufferedOutputStream(con.getOutputStream())) {
                        B.write(message.getBytes("utf8"));
                        B.flush();
                    }
                    int i = con.getResponseCode();
                    System.out.println("Got: " + i);
                } catch (MalformedURLException e) {
                    throw new SlackException("cannot into url " + e);
                } catch (IOException e) {
                    throw new SlackException("Sum ting wong " + e);
                }
            }
        });
    }

}
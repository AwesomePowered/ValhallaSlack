package net.awesomepwoered.valhallaslack.Master;

import net.awesomepwoered.valhallaslack.Commands.CommandGlist;
import net.awesomepwoered.valhallaslack.Utilities.Messenger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Created by John on 12/1/2014.
 */
public class SlackListener extends AbstractHandler {

    private Plugin plugin;
    Messenger m = new Messenger();

    public SlackListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handle(String target, Request request, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("utf8");
        request.setHandled(true);
        if (target.equalsIgnoreCase("/slack")) {
            if (!req.getParameter("key").equals("SomeRandomStringHere")) {
                return;
            }
            //String test = req.getParameter("text");
            System.out.println("POST REQUEST RECEIVED");
            m.sendResponse("Hey, got ya message");
            parseCommand(req.getParameter("text"));
            for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
                String x = e.nextElement().toString();
                System.out.println(x + " = " + req.getParameter(x));

            }
        }
    }

    public void parseCommand(String command) throws IOException {
        String cmd = command.replace(".", "").toLowerCase();
        System.out.println("Command: " + cmd);
        if (cmd.startsWith("glist")) {
            new CommandGlist().execute();
        }
        if (cmd.startsWith("alert")) {
            plugin.getProxy().broadcast(ChatColor.translateAlternateColorCodes('&', cmd.replace("alert", "").replace("&amp;", "&")));
        }

    }

}

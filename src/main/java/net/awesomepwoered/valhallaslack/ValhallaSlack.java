package net.awesomepwoered.valhallaslack;

import net.awesomepwoered.valhallaslack.Master.BungeeListener;
import net.awesomepwoered.valhallaslack.Master.SlackListener;
import net.awesomepwoered.valhallaslack.Utilities.JettyLogger;
import net.md_5.bungee.api.plugin.Plugin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.util.log.StdErrLog;

import java.util.Properties;

/**
 * Created by John on 12/1/2014.
 */

public class ValhallaSlack extends Plugin {

    public static ValhallaSlack instance;

    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new BungeeListener());
        //doStart();
    }

    public void doStart() {
        org.eclipse.jetty.util.log.Log.setLog(new JettyLogger());
        Properties p = new Properties();
        p.setProperty("org.eclipse.jetty.LEVEL", "WARN");
        StdErrLog.setProperties(p);
        ContextHandler context = new ContextHandler("/");
        SessionHandler sessions = new SessionHandler(new HashSessionManager());
        sessions.setHandler(new SlackListener(this));
        context.setHandler(sessions);
        final Server server = new Server(6969);
        server.setSessionIdManager(new HashSessionIdManager());
        server.setHandler(sessions);
        server.setStopAtShutdown(true);
        getProxy().getScheduler().runAsync(this, new Runnable() {
            @Override
            public void run() {
                try {
                    server.start();
                    getLogger().info("Starting le HTTP servur");
                } catch(Exception e) {
                    getLogger().warning("Cannot into port biding");
                    e.printStackTrace();
                }
            }
        });
    }

}

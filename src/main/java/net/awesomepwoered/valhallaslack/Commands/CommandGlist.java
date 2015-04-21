package net.awesomepwoered.valhallaslack.Commands;

import net.awesomepwoered.valhallaslack.Utilities.Messenger;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by John on 12/6/2014.
 */
public class CommandGlist {

    Messenger m = new Messenger();

    public void execute() {
        for (ServerInfo ser : ProxyServer.getInstance().getServers().values()) {
            List<String> players = new ArrayList<>();
            for (ProxiedPlayer player : ser.getPlayers() )
            {
                players.add(player.getDisplayName());
            }
            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
            m.sendResponse(ser.getName() + "(" + ser.getPlayers().size() +")" + Util.format(players, ", "));
        }
        m.sendResponse("Total players online: " + ProxyServer.getInstance().getOnlineCount());
    }

}

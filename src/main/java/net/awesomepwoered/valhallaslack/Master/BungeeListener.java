package net.awesomepwoered.valhallaslack.Master;

import net.awesomepwoered.valhallaslack.Utilities.Messenger;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by John on 12/16/2014.
 */
public class BungeeListener implements Listener {

    Messenger m = new Messenger();

    @EventHandler
    public void onChat(ChatEvent ev) {
        ProxiedPlayer p = (ProxiedPlayer) ev.getSender();
        String name = p.getName();
        String message = ev.getMessage();
        String server = p.getServer().getInfo().getName();
        m.sendMessage("[" + name + "]" + "(" + server + ") " + message, name);
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage(name + " logged out", name);
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage(name + " logged in: " + ev.getPlayer().getServer().getInfo().getName(), name);
    }

    @EventHandler
    public void onLogin(LoginEvent ev) {
        String name = ev.getConnection().getName();
        String host = ev.getConnection().getVirtualHost().getHostString();
        String pIP = ev.getConnection().getAddress().getAddress().toString();
        m.sendMessage(name + " ("+ pIP + ") is logging in using " + host, name);
    }

    @EventHandler
    public void onKick(ServerKickEvent ev) {
        ProxiedPlayer p = ev.getPlayer();
        m.sendMessage(p.getName() + " was kicked from " + ev.getKickedFrom() + " for "  + ev.getKickReason(), p.getName());
    }

}

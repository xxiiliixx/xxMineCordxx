package pl.xxiiliixx.MineCord.Minecraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.xxiiliixx.MineCord.Discord.Activity.SetActivity;

public class Players implements Listener {

    public static SetActivity setActivity;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        int number = Bukkit.getOnlinePlayers().size();
        setActivity.NumberOfPlayers(number);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        int number = Bukkit.getOnlinePlayers().size();
        setActivity.NumberOfPlayers(number);
    }

}

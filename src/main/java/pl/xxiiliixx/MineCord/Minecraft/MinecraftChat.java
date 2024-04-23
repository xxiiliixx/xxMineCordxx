package pl.xxiiliixx.MineCord.Minecraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.xxiiliixx.MineCord.Discord.Chat;

public class MinecraftChat implements Listener {

    // get minecraft chat
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Chat chat = new Chat();
        chat.ChannelWrite(player, message);

    }

}

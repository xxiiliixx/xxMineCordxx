package pl.xxiiliixx.MineCord.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.xxiiliixx.MineCord.Manager.ConfigManager;
import pl.xxiiliixx.MineCord.Manager.ConnectedAccountsManager;

public class Chat extends ListenerAdapter {

    public static ConfigManager configManager;
    public static ConnectedAccountsManager connectedAccountsManager;
    public static JDA jda;

    public void ChannelWrite(Player player, String message) {
        String channelId = configManager.getMinecraftChatChannelId();

        TextChannel channel = jda.getTextChannelById(channelId);

        if(channel != null) {
            channel.sendMessage(configManager.getChatDiscordMessage().replace("{PLAYER}", player.getName()).replace("{MESSAGE}", message)).queue();
        } else {
            System.out.println("[xxMineCordxx][ERROR] Channel ID is null");
        }

    }

    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        String message = event.getMessage().getContentDisplay();

        String minecraftPlayerName = connectedAccountsManager.getMinecraftName(user.getId());

        if (!event.getAuthor().isBot()) {
            if (event.getChannel().getId().equals(configManager.getMinecraftChatChannelId())) {
                if (connectedAccountsManager.isPlayerAccountConnected(user.getId())) {
                    Bukkit.getServer().broadcastMessage(configManager.getChatMinecraftMessage().replace("{PLAYER}", minecraftPlayerName).replace("{MESSAGE}", message));
                    event.getMessage().delete().queue();
                    event.getTextChannel().sendMessage("[DC] " + configManager.getChatDiscordMessage().replace("{PLAYER}", minecraftPlayerName).replace("{MESSAGE}", message)).queue();
                } else {
                    event.getMessage().delete().queue();
                }

            }

        }

    }

}


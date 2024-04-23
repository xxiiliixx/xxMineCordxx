package pl.xxiiliixx.MineCord.Manager;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
        config.options().copyDefaults(true);
    }

    public String getBotToken() {
        return config.getString("bot_token");
    }

    public boolean getMinecraftChatChannelEnabled() {
        return config.getBoolean("minecraft_chat_channel_enabled");
    }

    public String getMinecraftChatChannelId() {
        return config.getString("minecraft_chat_channel_id");
    }

    public String getChatDiscordMessage() {
        return config.getString("chat_discord_message");
    }

    public String getVerificationCodeMessage() {
        return config.getString("verification_code_message");
    }

    public String getAccountConnectionError() {
        return config.getString("account_connection_error_message");
    }

    public boolean getNumberOfPlayersActivityEnable() {
        return config.getBoolean("number_of_players_activity_enable");
    }

    public String getNumberOfPlayersActivity() {
        return config.getString("number_of_players_activity");
    }

    public String getChatMinecraftMessage() {
        return config.getString("chat_minecaft_message");
    }

}

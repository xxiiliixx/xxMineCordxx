package pl.xxiiliixx.MineCord.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import pl.xxiiliixx.MineCord.Discord.Activity.SetActivity;
import pl.xxiiliixx.MineCord.Discord.Commands.SetupConnectAccountChannel;
import pl.xxiiliixx.MineCord.Manager.ConfigManager;

import javax.security.auth.login.LoginException;

public class Bot {

    public static ConfigManager configManager;
    private JDA jda;

    public void Build() {
        String token = configManager.getBotToken();
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);

        // events
        builder.addEventListeners(new SetupConnectAccountChannel());
        builder.addEventListeners(new ConnectingAccounts());
        builder.addEventListeners(new Chat());

        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        Chat.jda = jda;
        SetActivity.jda = jda;

    }

}

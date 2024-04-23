package pl.xxiiliixx.MineCord.Discord;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import pl.xxiiliixx.MineCord.Manager.ConfigManager;
import pl.xxiiliixx.MineCord.Manager.ConnectedAccountsManager;
import pl.xxiiliixx.MineCord.Manager.VerificationCodesManager;

import java.util.Random;

public class ConnectingAccounts extends ListenerAdapter {

    public static ConfigManager configManager;
    public static ConnectedAccountsManager connectedAccountsManager;
    public static VerificationCodesManager verificationCodesManager;

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("connect_button")) {
            Random random = new Random();

            int code = 0;

            do {
                code = random.nextInt(1000000);
            } while (verificationCodesManager.isCorrectCode(code));


            if (!connectedAccountsManager.isPlayerAccountConnected(event.getUser().getId())) {
                verificationCodesManager.AddCode(event.getUser().getId(), code);
                event.reply(configManager.getVerificationCodeMessage().replace("{CODE}", Integer.toString(code))).setEphemeral(true).queue();

            } else {
                event.reply(configManager.getAccountConnectionError()).setEphemeral(true).queue();
            }

        } else if (event.getButton().getId().equals("unconnect_button")) {
            if (connectedAccountsManager.isPlayerAccountConnected(event.getUser().getId())) {
                connectedAccountsManager.Remove(event.getUser().getId());
                event.reply("Succes").setEphemeral(true).queue();
            }

        }

    }

}

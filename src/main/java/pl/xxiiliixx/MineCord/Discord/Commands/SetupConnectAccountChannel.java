package pl.xxiiliixx.MineCord.Discord.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import pl.xxiiliixx.MineCord.Manager.ConnectedAccountsManager;

import java.awt.*;

public class SetupConnectAccountChannel extends ListenerAdapter {

    public static ConnectedAccountsManager connectedAccountsManager;

    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel channel = event.getTextChannel();
        String messageText = event.getMessage().getContentDisplay();
        User user = event.getAuthor();

        String[] command = messageText.split(" ", 3);

        if (isAdmin(event.getMember()) || event.getMember().isOwner()) {
            if (command[0].equalsIgnoreCase("//setup")) {
                if (connectedAccountsManager.getConnectAccountChannelId().equals("")) {
                    connectedAccountsManager.SetChannel(channel.getId());

                    EmbedBuilder eb = new EmbedBuilder();

                    eb.setTitle(command[1]);
                    eb.setColor(Color.BLUE);
                    eb.setDescription(command[2]);

                    Button button = Button.primary("connect_button", "Link");
                    Button button2 = Button.danger("unconnect_button", "Unlink");

                    event.getMessage().delete().queue();

                    channel.sendMessageEmbeds(eb.build()).setActionRows(ActionRow.of(button), ActionRow.of(button2)).queue((message) -> {
                        String embedId = message.getId();
                        connectedAccountsManager.SetMessageId(embedId);
                    });

                } else {
                    channel.sendMessage("**You have already connection channel...**").queue();
                }

            } else if (command[0].equalsIgnoreCase("//remove")) {
                if (connectedAccountsManager.getConnectAccountChannelId().equals(channel.getId())) {
                    event.getMessage().delete().queue();

                    String messageId = connectedAccountsManager.getMessageId();

                    connectedAccountsManager.SetChannel("");
                    connectedAccountsManager.SetMessageId("");

                    channel.sendMessage("**Variable deleted...**").queue();

                    channel.deleteMessageById(messageId).queue();
                } else if (connectedAccountsManager.getConnectAccountChannelId().equals("")) {
                    channel.sendMessage("**You did not setup channel. Use //setup (title) (description)**").queue();
                } else {
                    channel.sendMessage("**Incorrect channel...**").queue();
                }

            }

        }

    }

    private boolean isAdmin(Member member) {
        for(Role role : member.getRoles()) {
            if(role.hasPermission(Permission.ADMINISTRATOR)) {
                return true;
            }

        }

        return false;

    }

}

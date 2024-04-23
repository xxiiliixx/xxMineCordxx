package pl.xxiiliixx.MineCord.Minecraft.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.xxiiliixx.MineCord.Manager.ConnectedAccountsManager;
import pl.xxiiliixx.MineCord.Manager.Verification.VerificationData;
import pl.xxiiliixx.MineCord.Manager.VerificationCodesManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class ConnectionCommand implements CommandExecutor {

    public static VerificationCodesManager verificationCodesManager;
    public static ConnectedAccountsManager connectedAccountsManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("dc_connect")) {
            Player player = (Player) sender;
            if (isNumber(args[0])) {
                if (verificationCodesManager.isCorrectCode(Integer.parseInt(args[0]))) {
                    VerificationData verificationData = verificationCodesManager.getValue(Integer.parseInt(args[0]));

                    String userId = verificationData.UserId;
                    LocalDateTime dateTime = verificationData.DateTime;

                    Duration duration = Duration.between(dateTime, LocalDateTime.now());

                    if (duration.toMinutes() <= 2) {
                        player.sendMessage(ChatColor.GREEN + "Accounts connected");
                        connectedAccountsManager.Set(player.getName(), userId);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Your code is expired");
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect code");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Incorrect code format");
            }

        }
        return true;
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}

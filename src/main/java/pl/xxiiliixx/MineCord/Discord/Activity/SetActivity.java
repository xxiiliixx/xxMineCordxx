package pl.xxiiliixx.MineCord.Discord.Activity;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import pl.xxiiliixx.MineCord.Manager.ConfigManager;

public class SetActivity {

    public static ConfigManager configManager;
    public static JDA jda;

    public void NumberOfPlayers(int number) {
        String snumber = Integer.toString(number) + "/" + Integer.toString(Bukkit.getMaxPlayers());
        String s = configManager.getNumberOfPlayersActivity().replace("{NUMBER}" , snumber);
        jda.getPresence().setActivity(Activity.playing(s));
    }

}

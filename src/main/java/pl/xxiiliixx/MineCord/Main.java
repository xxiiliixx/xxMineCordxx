    package pl.xxiiliixx.MineCord;

    import org.bukkit.configuration.file.FileConfiguration;
    import org.bukkit.configuration.file.YamlConfiguration;
    import org.bukkit.plugin.java.JavaPlugin;
    import pl.xxiiliixx.MineCord.Discord.Activity.SetActivity;
    import pl.xxiiliixx.MineCord.Discord.Bot;
    import pl.xxiiliixx.MineCord.Discord.Chat;
    import pl.xxiiliixx.MineCord.Discord.Commands.SetupConnectAccountChannel;
    import pl.xxiiliixx.MineCord.Discord.ConnectingAccounts;
    import pl.xxiiliixx.MineCord.Manager.ConfigManager;
    import pl.xxiiliixx.MineCord.Manager.ConnectedAccountsManager;
    import pl.xxiiliixx.MineCord.Manager.VerificationCodesManager;
    import pl.xxiiliixx.MineCord.Minecraft.Commands.ConnectionCommand;
    import pl.xxiiliixx.MineCord.Minecraft.MinecraftChat;
    import pl.xxiiliixx.MineCord.Minecraft.Players;

    import java.io.File;
    import java.io.IOException;

    public class Main extends JavaPlugin {

        private ConfigManager configManager;
        private ConnectedAccountsManager connectedAccountsManager;
        private FileConfiguration connectedAccountsFile;

        @Override
        public void onEnable() {
            Bot bot = new Bot();

            // config
            configManager = new ConfigManager(getConfig());
            Chat.configManager = configManager;
            bot.configManager = configManager;
            ConnectingAccounts.configManager = configManager;
            SetActivity.configManager = configManager;

            if (new File(getDataFolder(), "connected_accounts.yml") != null) {
                saveResource("connected_accounts.yml", false);
            }
            connectedAccountsFile = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "connected_accounts.yml"));

            connectedAccountsManager = new ConnectedAccountsManager(connectedAccountsFile);
            SetupConnectAccountChannel.connectedAccountsManager = connectedAccountsManager;
            ConnectionCommand.connectedAccountsManager = connectedAccountsManager;
            ConnectingAccounts.connectedAccountsManager = connectedAccountsManager;
            Chat.connectedAccountsManager = connectedAccountsManager;

            saveDefaultConfig();

            // VerificationCodesManager
            VerificationCodesManager verificationCodesManager = new VerificationCodesManager();
            ConnectingAccounts.verificationCodesManager = verificationCodesManager;
            ConnectionCommand.verificationCodesManager = verificationCodesManager;

            // SetActivity
            SetActivity setActivity = new SetActivity();
            Players.setActivity = setActivity;

            // bot enable
            bot.Build();

            // events
            if (configManager.getMinecraftChatChannelEnabled()) {
                getServer().getPluginManager().registerEvents(new MinecraftChat(), this);
            }

            if (configManager.getNumberOfPlayersActivityEnable()) {
                getServer().getPluginManager().registerEvents(new Players(), this);
            }

            // commands
            getCommand("dc_connect").setExecutor(new ConnectionCommand());

            System.out.println("[xxMineCordxx] Bot is enabled");
        }

        @Override
        public void onDisable() {
            System.out.println("[xxMineCordxx] Bot says: 'Good night!'");
            saveConfig();
            try {
                connectedAccountsFile.save(new File(getDataFolder(), "connected_accounts.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

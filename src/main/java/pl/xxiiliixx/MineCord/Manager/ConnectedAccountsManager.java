package pl.xxiiliixx.MineCord.Manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConnectedAccountsManager {

    private FileConfiguration connectedAccounts;

    public ConnectedAccountsManager(FileConfiguration connectedAccounts) {
        this.connectedAccounts = connectedAccounts;
        connectedAccounts.options().copyDefaults(true);
    }

    public boolean isPlayerAccountConnected(String id) {
        if(connectedAccounts.getString("connected_account_" + id) != null) {
            return true;
        }
        return false;
    }

    public String getConnectAccountChannelId() {
        return connectedAccounts.getString("connect_account_channel_id");
    }

    public String getMessageId() {
        return connectedAccounts.getString("connect_account_message_id");
    }

    public void Set(String minecraftName, String discordId){
        connectedAccounts.set("connected_account_" + discordId, minecraftName);
    }

    public void SetChannel(String id) {
        connectedAccounts.set("connect_account_channel_id", id);
    }

    public void SetMessageId(String id) {
        connectedAccounts.set("connect_account_message_id", id);
    }

    public String getMinecraftName(String id) {
        return connectedAccounts.getString("connected_account_" + id);
    }

    public void Remove(String id) {
        connectedAccounts.set("connected_account_" + id, null);
    }

}

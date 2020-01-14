package me.drawlin.staffchat;

import lombok.Getter;
import me.drawlin.archive.Archive;
import me.drawlin.staffchat.bot.Bot;
import me.drawlin.staffchat.chat.ChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class StaffchatPlugin extends JavaPlugin {

    @Getter
    private static StaffchatPlugin staffchatPlugin;
    private Bot bot;
    private String botToken;

    public void onEnable() {
        staffchatPlugin = this;
        bot = new Bot("NTcyOTQ2MjE2MjA0MjM4ODU5.Xh4y8Q.-DvXEffA_mXbWVmDmCw1apWtyVI");
        new Archive(this, false).getCommandFramework().registerCommands(new ChatCommand(bot.getJda()));

        loadConfig();
    }

    public void onDisable() {
        staffchatPlugin = null;
    }

    private void loadConfig() {
        if (getConfig().getString("bot-token") == null) /* if bot-token doesn't exist then set the config values */{
            getConfig().set("bot-token", "retrieve the token from Discord applications");
        }
        saveConfig();
        botToken = getConfig().getString("bot-token");
    }

}

package me.drawlin.staffchat.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BotListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getTextChannel().getName().equalsIgnoreCase("staff-chat")) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.hasPermission("staffchat.staff"))
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[DC] &b" + event.getAuthor().getName() + ": " + event.getMessage().getContentRaw()));
            }
        }

    }
}

package me.drawlin.staffchat.chat;

import me.drawlin.archive.command.Command;
import me.drawlin.archive.command.CommandArgs;
import me.drawlin.staffchat.utility.StringUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ChatCommand {

    private JDA jda;
    private final String staffPermission = "staffchat.staff";

    public ChatCommand(JDA jda) {
        this.jda = jda;
    }

    @Command(name = "staffchat", aliases = {"sc"})
    public void onCommand(CommandArgs args) {
        if (!args.getSender().hasPermission(staffPermission)) {
            args.getSender().sendMessage(ChatColor.RED + "No permission.");
            return;
        }

        if (args.length() == 0) {
            args.getSender().sendMessage(ChatColor.RED + "Usage: /staffchat <message...>");
            return;
        }

        final String message = StringUtil.buildString(args.getArgs(), 0);

        /* send message to Discord */
        TextChannel textChannel = jda.getTextChannels().stream().filter(channel -> channel.getName().equalsIgnoreCase("staff-chat")).findFirst().orElse(null);
        Objects.requireNonNull(textChannel).sendMessage(args.getPlayer().getName() + ": " + message).queue();

        /* send message to ingame staff members */
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission(staffPermission))
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[SC] &b" + args.getPlayer().getName() + ": " + message));
        }

    }

}

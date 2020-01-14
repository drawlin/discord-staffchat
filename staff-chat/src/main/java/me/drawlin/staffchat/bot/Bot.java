package me.drawlin.staffchat.bot;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.security.auth.login.LoginException;

@Getter @Setter
public class Bot {

    private JDA jda;

    public Bot(String token) {
        JDABuilder builder = new JDABuilder(token);
        builder.setActivity(Activity.watching("Server staff chat"));
        try {
            jda = builder.build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException ex) {
            ex.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not login to the Discord bot.");
        }

        jda.addEventListener(new BotListener());
    }

}

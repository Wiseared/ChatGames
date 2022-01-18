package xyz.wiseared.chatgames;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.wiseared.chatgames.listeners.ChatListener;
import xyz.wiseared.chatgames.tasks.GameTask;
import xyz.wiseared.chatgames.utils.CC;
import xyz.wiseared.chatgames.utils.Messages;

import java.util.List;

@Getter
@Setter
public class ChatGames extends JavaPlugin {

    private static ChatGames instance;
    private String prefix;

    private String previousGame;
    private String type;
    private String answer;
    private List<String> rewards;
    private Boolean active;

    public static ChatGames getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        Messages.init();
        this.prefix = getConfig().getString("SETTINGS.PREFIX");
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7&m-------------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&fEnabled &e&lChatGames"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&fMade by &eWiseared"));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7&m-------------------------------------------------"));

        // Start the countdown for a new game
        GameTask gameTask = new GameTask(this);
        gameTask.runTaskTimer(this, 20L * getConfig().getInt("SETTINGS.TIME"), 20L * getConfig().getInt("SETTINGS.TIME"));
    }
}
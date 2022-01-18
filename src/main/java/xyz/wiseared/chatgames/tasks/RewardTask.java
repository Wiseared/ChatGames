package xyz.wiseared.chatgames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.wiseared.chatgames.ChatGames;

public class RewardTask extends BukkitRunnable {

    private final ChatGames plugin;
    private final String name;

    public RewardTask(String name, ChatGames plugin) {
        this.name = name;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getRewards().forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replaceAll("%player%", name)));
    }
}
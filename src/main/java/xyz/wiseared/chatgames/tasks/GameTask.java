package xyz.wiseared.chatgames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.wiseared.chatgames.ChatGames;
import xyz.wiseared.chatgames.utils.CC;
import xyz.wiseared.chatgames.utils.GameUtils;
import xyz.wiseared.chatgames.utils.Messages;

import java.util.ArrayList;

public class GameTask extends BukkitRunnable {

    private final ChatGames plugin;

    public GameTask(ChatGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.setRewards(new ArrayList<>());
        plugin.setActive(true);
        String game = GameUtils.getGame();
        plugin.setPreviousGame(game);
        plugin.setType(plugin.getConfig().getString("GAMES." + game + ".TYPE"));
        plugin.setAnswer(plugin.getConfig().getString("GAMES." + game + ".ANSWER"));

        for (String string : plugin.getConfig().getStringList("GAMES." + game + ".REWARDS")) {
            plugin.getRewards().add(string);
        }

        switch (plugin.getType()) {
            case "shuffle":
                Bukkit.broadcastMessage(CC.translate(plugin.getConfig().getString("GAMES." + game + ".QUESTION").replaceAll("%prefix%", plugin.getPrefix()).replaceAll("%word%", GameUtils.shuffleString(plugin.getAnswer()))));
                break;
            case "reversed":
                Bukkit.broadcastMessage(CC.translate(plugin.getConfig().getString("GAMES." + game + ".QUESTION").replaceAll("%prefix%", plugin.getPrefix()).replaceAll("%word%", GameUtils.reverseWord(plugin.getAnswer()))));
                break;
            case "equation":
            case "typeword":
                Bukkit.broadcastMessage(CC.translate(plugin.getConfig().getString("GAMES." + game + ".QUESTION").replaceAll("%prefix%", plugin.getPrefix())));
                break;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (plugin.getActive()) {
                    plugin.setActive(false);
                    plugin.setAnswer(null);
                    plugin.setType(null);
                    Bukkit.broadcastMessage(CC.translate(Messages.NO_ANSWER.replaceAll("%prefix%", plugin.getPrefix())));
                }
            }
        }.runTaskLater(plugin, 20L * plugin.getConfig().getInt("SETTINGS.GAME-ENDING"));
    }
}
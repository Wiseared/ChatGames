package xyz.wiseared.chatgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.wiseared.chatgames.ChatGames;
import xyz.wiseared.chatgames.tasks.RewardTask;
import xyz.wiseared.chatgames.utils.CC;
import xyz.wiseared.chatgames.utils.Messages;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equalsIgnoreCase(ChatGames.getInstance().getAnswer()) && ChatGames.getInstance().getActive()) {
            event.setCancelled(true);
            ChatGames.getInstance().setAnswer(null);
            ChatGames.getInstance().setActive(false);
            ChatGames.getInstance().setType(null);
            RewardTask rewardTask = new RewardTask(player.getName(), ChatGames.getInstance());
            rewardTask.runTask(ChatGames.getInstance());
            Bukkit.broadcastMessage(CC.translate(Messages.GUESSED_RIGHT.replaceAll("%prefix%", ChatGames.getInstance().getPrefix()).replaceAll("%answer%", event.getMessage()).replaceAll("%player%", player.getName())));
        }
    }
}
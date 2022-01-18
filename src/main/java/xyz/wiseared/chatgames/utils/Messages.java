package xyz.wiseared.chatgames.utils;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.wiseared.chatgames.ChatGames;

public class Messages {

    public static String NO_ANSWER;
    public static String GUESSED_RIGHT;

    public static void init() {
        FileConfiguration messages = ChatGames.getInstance().getConfig();

        NO_ANSWER = messages.getString("MESSAGES.NO-ANSWER");
        GUESSED_RIGHT = messages.getString("MESSAGES.GUESSED-RIGHT");
    }
}
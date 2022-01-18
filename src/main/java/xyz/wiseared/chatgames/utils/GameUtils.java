package xyz.wiseared.chatgames.utils;

import lombok.experimental.UtilityClass;
import xyz.wiseared.chatgames.ChatGames;

import java.util.*;

@UtilityClass
public class GameUtils {

    public String getGame() {
        List<String> games = new ArrayList<>();
        games.addAll(ChatGames.getInstance().getConfig().getConfigurationSection("GAMES").getKeys(false));
        games.remove(ChatGames.getInstance().getPreviousGame());
        Random random = new Random();
        return games.get(random.nextInt(games.size()));
    }

    public String reverseWord(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        return stringBuilder.reverse().toString();
    }

    public String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        StringBuilder shuffled = new StringBuilder();

        for (String letter : letters) {
            shuffled.append(letter);
        }

        return shuffled.toString();
    }
}
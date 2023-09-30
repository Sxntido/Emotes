package team.aquatic.studios.tools;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    public static String APISounds(Player p, String config) {
        String path = config;
        String[] space = path.split(":");
        try {
            int volumen = Integer.valueOf(space[1]).intValue();
            float pitch = Float.valueOf(space[2]).floatValue();
            Sound sound = Sound.valueOf(space[0]);
            p.playSound(p.getLocation(), sound, volumen, pitch);
        } catch (IllegalArgumentException e) {
            getLoggs("&cSound bug: &e" + space + " , &cis invalid", true);
        }
        return path;
    }
    private static void getLoggs(String s, boolean b) {}
}
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

public class Runes {
    public String name;
    public String level;
    public String weapon;
    public String armor;
    public String shield;
    public String drops;
    public String runewords;

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    public String getShield() {
        return shield;
    }

    public String getDrops() {
        return drops;
    }

    public String getRunewords() {
        return runewords;
    }

    public static String getRune(String name) {
        String rune = "";
        Collection<Runes> runeList = createRuneObject();
        for (Runes r : Objects.requireNonNull(runeList)) {
            if (Objects.equals(r.getName().toLowerCase(), name.toLowerCase())) {
                rune = r.getName() + "\n" +
                        "Level: " + r.getLevel() + "\n" +
                        "Weapon: " + r.getWeapon() + "\n" +
                        "Armor/Helm: " + r.getArmor() + "\n" +
                        "Shield: " + r.getShield() + "\n" +
                        "Drops: " + r.getDrops() + "\n" +
                        "Runewords: " + r.getRunewords();
            }
        }
        return rune;
    }

    public static String getRunes() {
        Collection<Runes> runeList = createRuneObject();
        StringBuilder runeNames = new StringBuilder();
        for (Runes rune : Objects.requireNonNull(runeList)) {
            Objects.requireNonNull(runeNames).append(rune.getName()).append(" ");
        }
        return runeNames.toString();
    }

    public static boolean exists(String name) {
        Collection<Runes> runeList = createRuneObject();
        for (Runes r : Objects.requireNonNull(runeList)) {
            if (Objects.equals(r.getName().toLowerCase(), name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Collection<Runes> createRuneObject() {
        try {
            String jsonFile = "src/main/resources/json/runes.json";
            Reader reader = Files.newBufferedReader(Paths.get(jsonFile));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Runes>>(){}.getType();
            return gson.fromJson(reader, collectionType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
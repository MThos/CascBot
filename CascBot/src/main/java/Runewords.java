import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Runewords {
    public String name;
    public String level;
    public String item;
    public String ladder;
    public String[] runes;
    public String[] properties;

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getItem() {
        return item;
    }

    public String getLadder() {
        return ladder;
    }

    public String getRunes() {
        return Arrays.toString(runes);
    }

    public String getProperties() {
        return Arrays.toString(properties);
    }

    public static String getRuneword(String name) {
        String rune = "";
        Collection<Runewords> runewordList = createRuneObject();
        for (Runewords r : Objects.requireNonNull(runewordList)) {
            if (Objects.equals(r.getName().toLowerCase(), name.toLowerCase())) {
                rune = r.getName() + "\n" +
                        "Level: " + r.getLevel() + "\n" +
                        "Item: " + r.getItem() + "\n" +
                        "Ladder: " + r.getLadder() + "\n" +
                        "Runes: " + r.getRunes() + "\n" +
                        "Properties: " + r.getProperties();
            }
        }
        return rune;
    }

    public static boolean exists(String name) {
        Collection<Runewords> runewordList = createRuneObject();
        for (Runewords r : Objects.requireNonNull(runewordList)) {
            if (Objects.equals(r.getName().toLowerCase(), name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Collection<Runewords> createRuneObject() {
        try {
            String jsonFile = "src/main/resources/json/runewords.json";
            Reader reader = Files.newBufferedReader(Paths.get(jsonFile));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Runewords>>(){}.getType();
            return gson.fromJson(reader, collectionType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
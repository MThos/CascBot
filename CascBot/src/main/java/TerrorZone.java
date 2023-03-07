import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class TerrorZone {
    public int[] id;

    public TerrorZone(int[] id) {
        this.id = id;
    }

    public int[] getId() {
        return id;
    }

    public static void getTerrorZone() {
        getTerrorZoneId();
    }

    public static void getTerrorZoneId() {
        try {
            String tzUrl = "https://d2rapi.fly.dev/tz";
            InputStream input = new URL(tzUrl).openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            TerrorZone zones = new TerrorZone(new Gson().fromJson(reader, int[].class));
            getTerrorZoneName(zones);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getTerrorZoneName(TerrorZone zones) {
        try {
            String jsonFile = "src/main/resources/json/level_id.json";
            Reader reader = Files.newBufferedReader(Paths.get(jsonFile));
            Type collectionType = new TypeToken<Collection<ZoneId>>(){}.getType();
            Collection<ZoneId> zoneList = new Gson().fromJson(reader, collectionType);

            for (int id : zones.getId()) {
                for (ZoneId zone : zoneList) {
                    if (id == zone.getId()) {
                        System.out.println(zone.getName());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

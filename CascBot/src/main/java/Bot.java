import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
    public static void main(String[] arguments) throws InterruptedException {
        String _TOKEN_ = "";
        String _ID_ = "";

        JDA jdaAPI = JDABuilder.createDefault(_TOKEN_, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new Listener())
                .build()
                .awaitReady();

        Guild server = jdaAPI.getGuildById(_ID_);

        if (server != null) {
            // alive
            server.upsertCommand("alive", "check if bot is alive").queue();
            // help
            server.upsertCommand("help", "gets command options for bot").queue();
            // runes
            server.upsertCommand("runes", "gets a list of all runes in level order").queue();
            // rune
            server.upsertCommand("rune", "gets details on a specific rune")
                    .addOption(OptionType.STRING, "name", "what is the rune name you want details of?", true)
                    .queue();
            // runewords
            server.upsertCommand("runeword", "get details on a specific runeword")
                    .addOption(OptionType.STRING, "name", "what is the runeword you want details of?", true)
                    .queue();
            // terror zone
            server.upsertCommand("tz", "gets current terror zone").queue();
        }
    }
}
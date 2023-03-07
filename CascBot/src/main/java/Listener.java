import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Listener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("alive")) {
            isAlive(event);
        } else if (event.getName().equalsIgnoreCase("help")) {
            help(event);
        } else if (event.getName().equalsIgnoreCase("runes")) {
            runes(event);
        } else if (event.getName().equalsIgnoreCase("rune")) {
            rune(event);
        } else if (event.getName().equalsIgnoreCase("runeword")) {
            runeword(event);
        } else if (event.getName().equalsIgnoreCase("tz")) {
            terrorZone(event);
        }
    }

    private void terrorZone(SlashCommandInteractionEvent event) {
        TerrorZone.getTerrorZone();
    }

    private void runeword(SlashCommandInteractionEvent event) {
        OptionMapping runeOption = event.getOption("name");

        if (runeOption != null) {
            if (Runewords.exists(runeOption.getAsString())) {
                event.reply(Runewords.getRuneword(runeOption.getAsString())).queue();
            } else {
                event.reply("That runeword does not exist.").queue();
            }
        } else {
            event.reply("You must fill in the rune name.").queue();
        }
    }

    private void rune(SlashCommandInteractionEvent event) {
        OptionMapping runeOption = event.getOption("name");

        if (runeOption != null) {
            if (Runes.exists(runeOption.getAsString())) {
                event.reply(Runes.getRune(runeOption.getAsString())).queue();
            } else {
                event.reply("That rune does not exist. It must be one of the following: " + Runes.getRunes()).queue();
            }
        } else {
            event.reply("You must fill in the rune name.").queue();
        }
    }

    private void runes(SlashCommandInteractionEvent event) {
        event.reply(Runes.getRunes()).queue();
    }

    private void isAlive(SlashCommandInteractionEvent event) {
        event.reply("I am alive and well!").queue();
    }

    private void help(SlashCommandInteractionEvent event) {
        event.reply(
                "/help : gets command options for bot\n" +
                        "/alive : confirms bot is running\n" +
                        "/runes : gets list of all runes\n" +
                        "/rune [name] : get information on specific rune\n" +
                        "/runeword [name] : get information on specific runeword"
        ).queue();
    }
}
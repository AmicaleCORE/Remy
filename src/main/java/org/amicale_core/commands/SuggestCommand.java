package org.amicale_core.commands;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.components.commands.SlashCommandOption;
import fr.xen0xys.discordjava.components.commands.SlashSubCommand;
import org.amicale_core.modals.MusicSuggestionModal;
import org.amicale_core.modals.ProductSuggestionModal;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class used to create the /suggest command
 */
public class SuggestCommand extends AbstractSlashCommand {

    private static final SlashSubCommand suggestProduct = new SlashSubCommand("product", "Suggest a product");
    private static final SlashSubCommand suggestMusic = new SlashSubCommand("music", "Suggest a music");

    /**
     * Default constructor for tge suggest command
     * Create the command using {@link SlashCommandOption}
     */
    public SuggestCommand() {
        super("suggest", "Allow you to suggest a product",
                suggestProduct, suggestMusic);
    }

    @Override
    public void callback(@NotNull final DJApp djApp, @NotNull final SlashCommandInteraction e) {
        switch (Objects.requireNonNull(e.getSubcommandName()).toLowerCase()){
            case "product" -> {
                ProductSuggestionModal modal = new ProductSuggestionModal();
                djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
                e.replyModal(modal.getModal()).queue();
            }
            case "music" -> {
                MusicSuggestionModal modal = new MusicSuggestionModal();
                djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
                e.replyModal(modal.getModal()).queue();
            }
            default -> e.reply("Unknown subcommand").setEphemeral(true).queue();
        }
    }
}

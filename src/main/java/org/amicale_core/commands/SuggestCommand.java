package org.amicale_core.commands;

import net.dv8tion.jda.api.entities.Member;
import org.amicale_core.Remy;
import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.components.commands.SlashCommandOption;
import org.amicale_core.modals.SuggestionModal;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SuggestCommand extends AbstractSlashCommand {

    public SuggestCommand() {
        super("suggest", "Allow you to suggest a product",
                new SlashCommandOption(OptionType.STRING, "product_name", "The name of the product", false));
    }

    @Override
    public void callback(@NotNull final DJApp djApp, @NotNull final SlashCommandInteraction e) {
        OptionMapping option = e.getOption("product_name");
        if(Objects.isNull(option)){
            SuggestionModal modal = new SuggestionModal();
            djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
            e.replyModal(modal.getModal()).queue();
        }else{
            Member member;
            if(Objects.nonNull(member = e.getMember()))
                Remy.sendSuggestion(djApp, option.getAsString(), member, e);
        }
    }
}

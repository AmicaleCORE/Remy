package org.amicale_core.modals;

import net.dv8tion.jda.api.entities.Member;
import org.amicale_core.Remy;
import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class used to create a {@link AbstractModal} for the /suggest command
 */
public class SuggestionModal extends AbstractModal {

    /**
     * Constructor for the suggestion modal class
     * Create a modal using {@link ModalField}
     */
    public SuggestionModal() {
        super("suggestion", "Suggestion",
                new ModalField("product_name", "Product name", TextInputStyle.SHORT, "Product name", 0, 50));
    }

    @Override
    public void callback(@NotNull final DJApp djApp, @NotNull final ModalInteractionEvent e) {
        ModalMapping option = e.getValue("product_name");
        if(Objects.isNull(option)){
            SuggestionModal modal = new SuggestionModal();
            djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
            e.reply("An error occurred, please try again").setEphemeral(true).queue();
        }else{
            Member member = e.getMember();
            if(Objects.nonNull(member))
                Remy.sendSuggestion(djApp, option.getAsString(), member, e);
        }
    }
}

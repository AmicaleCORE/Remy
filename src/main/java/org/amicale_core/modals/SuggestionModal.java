package org.amicale_core.modals;

import org.amicale_core.Remy;
import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SuggestionModal extends AbstractModal {

    public SuggestionModal() {
        super("suggestion", "Suggestion", new ModalField("product_name", "Product name", TextInputStyle.SHORT, "Product name", 0, 50));
    }

    @Override
    public void callback(@NotNull DJApp djApp, @NotNull ModalInteractionEvent e) {
        ModalMapping option = e.getValue("product_name");
        if(Objects.isNull(option)){
            SuggestionModal modal = new SuggestionModal();
            djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
            e.reply("An error occurred, please try again").setEphemeral(true).queue();
        }
        else
            Remy.sendSuggestion(djApp, option.getAsString(), e.getMember(), e);
    }
}

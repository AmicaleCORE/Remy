package org.amicale_core.modals;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.amicale_core.Remy;
import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.amicale_core.models.SuggestionType;
import org.amicale_core.models.embeds.ProductSuggestionEmbed;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class used to create a {@link AbstractModal} for the /suggest command
 */
public class ProductSuggestionModal extends AbstractModal {

    /**
     * Constructor for the suggestion modal class
     * Create a modal using {@link ModalField}
     */
    public ProductSuggestionModal() {
        super("product_suggestion", "Suggestion de produit",
                new ModalField("product_name", "Product name", TextInputStyle.SHORT, "Product name", 0, 50));
    }

    @Override
    public void callback(@NotNull final DJApp djApp, @NotNull final ModalInteractionEvent e) {
        ModalMapping option = e.getValue("product_name");
        if(Objects.isNull(option)){
            ProductSuggestionModal modal = new ProductSuggestionModal();
            djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
            e.reply("Can't find product name").setEphemeral(true).queue();
            return;
        }
        Member member = e.getMember();
        if(Objects.isNull(member)){
            e.reply("Can't find member").setEphemeral(true).queue();
            return;
        }
        MessageEmbed embed = new ProductSuggestionEmbed(option.getAsString(), member).build();
        Remy.sendSuggestion(null, embed, e, SuggestionType.PRODUCT);
    }
}

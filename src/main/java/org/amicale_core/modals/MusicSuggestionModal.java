package org.amicale_core.modals;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.amicale_core.Remy;
import org.amicale_core.models.SuggestionType;
import org.amicale_core.models.embeds.MusicSuggestionEmbed;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MusicSuggestionModal extends AbstractModal {

    private static final ModalField musicNameField = new ModalField("music_name", "Nom de la musique", TextInputStyle.SHORT, "Nom de la musique", 0, 50);
    private static final ModalField musicLinkField = new ModalField("music_link", "Lien youtube de la musique", TextInputStyle.SHORT, "Lien youtube de la musique", 0, 100);

    public MusicSuggestionModal() {
        super("music_suggestion", "Suggestion de musique", musicNameField, musicLinkField);
    }

    @Override
    public void callback(@NotNull DJApp djApp, @NotNull ModalInteractionEvent e) {
        ModalMapping musicName = e.getValue("music_name");
        ModalMapping musicLink = e.getValue("music_link");
        if(Objects.isNull(musicName) || Objects.isNull(musicLink)){
            ProductSuggestionModal modal = new ProductSuggestionModal();
            djApp.getComponentsManager().handleModal(e.getUser().getIdLong(), modal);
            e.reply("Can't find music name or link").setEphemeral(true).queue();
            return;
        }
        if(!musicLink.getAsString().contains("youtube.com/")){
            e.reply("The link must be a youtube link").setEphemeral(true).queue();
            return;
        }
        Member member = e.getMember();
        if(Objects.isNull(member)){
            e.reply("Can't find member").setEphemeral(true).queue();
            return;
        }
        MessageEmbed embed = new MusicSuggestionEmbed(musicName.getAsString(), member).build();
        Remy.sendSuggestion(musicLink.getAsString(), embed, e, SuggestionType.MUSIC);
    }
}

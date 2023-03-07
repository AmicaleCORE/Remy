package org.amicale_core.models.embeds;

import org.amicale_core.Remy;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class SuggestionEmbed extends EmbedBuilder {
    public SuggestionEmbed(String name, Member member){
        this.setTitle(name.toUpperCase());
        this.setDescription(String.format("Suggestion de %s", member.getAsMention()));
        this.setFooter(Remy.CONFIG.getEmbedFooter());
        this.setColor(Color.ORANGE);
    }
}

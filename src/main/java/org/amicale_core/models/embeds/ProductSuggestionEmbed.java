package org.amicale_core.models.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.amicale_core.Remy;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Class used to display the suggestion when the /suggest command is executed
 */
public class ProductSuggestionEmbed extends EmbedBuilder {
    /**
     * Constructor of the Suggestion Embed class
     * @param name Name of the suggestion
     * @param member {@link Member} who send the suggestion
     */
    public ProductSuggestionEmbed(@NotNull final String name, @NotNull final Member member){
        this.setTitle(name.toUpperCase());
        this.setDescription(String.format("Suggestion de %s", member.getAsMention()));
        this.setFooter(Remy.CONFIG.getEmbedFooter());
        this.setColor(Color.ORANGE);
    }
}

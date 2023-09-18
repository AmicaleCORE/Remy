package org.amicale_core.models.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import org.amicale_core.Remy;
import org.amicale_core.models.CustomMessage;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

/**
 * Class used to display an embed for the /top command (currently not added)
 */
@SuppressWarnings("unused")
public class TopEmbed extends EmbedBuilder {
    /**
     * Constructor for the Top Embed class
     * @param messages {@link CustomMessage} to display
     */
    public TopEmbed(@NotNull final List<CustomMessage> messages){
        this.setColor(Color.GREEN);
        this.setTitle("Produits:");
        for(CustomMessage message: messages){
            String content = String.format("Nombre de votes pour : %s", message.reactionCount());
            this.addField(String.format("Produit: %s", message.getProductName()), content, false);
        }
        this.setFooter(Remy.CONFIG.getEmbedFooter());
    }

}

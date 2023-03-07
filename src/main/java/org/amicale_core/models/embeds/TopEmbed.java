package org.amicale_core.models.embeds;

import org.amicale_core.Remy;
import org.amicale_core.models.CustomMessage;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;

@SuppressWarnings("unused")
public class TopEmbed extends EmbedBuilder {
    public TopEmbed(List<CustomMessage> messages){
        this.setColor(Color.GREEN);
        this.setTitle("Produits:");
        String content;
        for(CustomMessage message: messages){
            content = String.format("Nombre de votes pour : %s", message.reactionCount());
            this.addField(String.format("Produit: %s", message.getProductName()), content, false);
        }
        this.setFooter(Remy.CONFIG.getEmbedFooter());
    }

}

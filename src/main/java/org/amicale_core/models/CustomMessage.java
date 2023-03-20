package org.amicale_core.models;

import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to sort best wanted suggestions
 * @param message The {@link Message}
 * @param reactionCount The number of positive reactions
 */
public record CustomMessage(@NotNull Message message, int reactionCount) {
    /**
     * Get the product name from the {@link Message}
     * @return The product name as a {@link String}
     */
    public String getProductName() {
        return message.getEmbeds().get(0).getTitle();
    }
}


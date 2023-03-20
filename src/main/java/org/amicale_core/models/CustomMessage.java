package org.amicale_core.models;

import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;

public record CustomMessage(@NotNull Message message, int reactionCount) {
    public String getProductName() {
        return message.getEmbeds().get(0).getTitle();
    }
}


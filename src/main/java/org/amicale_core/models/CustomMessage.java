package org.amicale_core.models;

import net.dv8tion.jda.api.entities.Message;

public record CustomMessage(Message message, int reactionCount) {

    public String getProductName() {
        return message.getEmbeds().get(0).getTitle();
    }
}


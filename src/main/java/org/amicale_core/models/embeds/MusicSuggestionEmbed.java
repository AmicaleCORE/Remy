package org.amicale_core.models.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import org.amicale_core.Remy;
import org.amicale_core.models.YoutubeResponse;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

/**
 * Class used to display the suggestion when the /suggest command is executed
 */
public class MusicSuggestionEmbed extends EmbedBuilder {
    public MusicSuggestionEmbed(@NotNull final String musicLick, @NotNull final String memberMention){
        System.out.println(getVideoId(musicLick));
        YoutubeResponse response = YoutubeResponse.getYoutubeVideoInfos(getVideoId(musicLick));
        if(Objects.isNull(response)) return;
        this.setTitle(response.title());
        this.setImage(response.thumbnail());
        this.setUrl(musicLick);
        this.setDescription(String.format("Suggestion de %s", memberMention));
        this.setFooter(Remy.CONFIG.getEmbedFooter());
        this.setColor(Color.ORANGE);
    }

    private String getVideoId(String musicLink){
        return musicLink.split("v=")[1].split("&")[0];
    }
}

package org.amicale_core.models;

import fr.xen0xys.discordjava.config.Configuration;

import java.io.File;

/**
 * Used to load the bot configuration file
 */
public class RemyConfig extends Configuration {

    /**
     * Constructor of RemyConfig class
     * Load the config file
     */
    public RemyConfig() {
        super(new File("Remy"), "config.yml");
    }

    public long getGuildId(){
        return this.getConfiguration().getLong("Bot.GuildId");
    }

    public long getProductSuggestionsChannelId(){
        return this.getConfiguration().getLong("Bot.ProductSuggestionsChannelId");
    }

    public long getMusicSuggestionsChannelId(){
        return this.getConfiguration().getLong("Bot.MusicSuggestionsChannelId");
    }

    public String getEmbedFooter(){
        return this.getConfiguration().getString("Bot.EmbedFooter");
    }

    public boolean isActivityEnabled(){
        return this.getConfiguration().getBoolean("Bot.Activity.Enable");
    }

    public String getActivityType(){
        return this.getConfiguration().getString("Bot.Activity.Type");
    }

    public String getActivityContent(){
        return this.getConfiguration().getString("Bot.Activity.Content");
    }

    public String getActivityUrl(){
        return this.getConfiguration().getString("Bot.Activity.Url");
    }

    public String getYoutubeApiKey() {
        return this.getConfiguration().getString("Bot.YoutubeAPIKey");
    }
}

package org.amicale_core.models;

import fr.xen0xys.discordjava.config.Configuration;

import java.io.File;

public class RemyConfig extends Configuration {
    public RemyConfig() {
        super(new File("Remy"), "config.yml");
    }

    public long getGuildId(){
        return this.getConfiguration().getLong("Bot.GuildId");
    }

    public long getSuggestionsChannelId(){
        return this.getConfiguration().getLong("Bot.SuggestionsChannelId");
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
}

package org.amicale_core;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.amicale_core.commands.SuggestCommand;
import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.utils.IdUtils;
import org.amicale_core.models.RemyConfig;
import org.amicale_core.models.embeds.SuggestionEmbed;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Scanner;

/**
 * Main class of Remy bot
 */
public class Remy {

    public static final RemyConfig CONFIG = new RemyConfig();

    /**
     * Entry point of the program
     * @param args custom arguments, not used here
     */
    public static void main(@NotNull final String[] args){
        try {
            DJApp app = new DJApp(CONFIG.getToken(), "Remy");
            initActivity(app);
            Guild guild = app.getJDA().getGuildById(CONFIG.getGuildId());
            if(Objects.isNull(guild))
                throw new RuntimeException("Guild not found");
            app.getCommandsManager().registerLocalCommand(guild, new SuggestCommand(), true);
            app.getLogger().info("Remy started!");
            Scanner scanner = new Scanner(System.in);
            do {
                if(scanner.nextLine().equals("exit")){
                    app.stop();
                    return;
                }
            }while (true);
        } catch (@NotNull final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Init the bot activity display
     * @param app {@link DJApp} bot instance
     */
    private static void initActivity(@NotNull final DJApp app){
        if(!CONFIG.isActivityEnabled()) return;
        String text = CONFIG.getActivityContent();
        Activity activity = switch (CONFIG.getActivityType()) {
            case "STREAMING" -> Activity.streaming(text, CONFIG.getActivityUrl());
            case "LISTENING" -> Activity.listening(text);
            case "WATCHING" -> Activity.watching(text);
            default -> Activity.playing(text);
        };
        app.getJDA().getPresence().setActivity(activity);
    }

    /**
     * Static method to send a suggestion
     * @param djApp {@link DJApp} bot instance
     * @param suggestionName Name of the suggestion
     * @param sender {@link Member} who send the suggestion
     * @param interaction {@link IReplyCallback} for the reply sending
     */
    public static void sendSuggestion(@NotNull final DJApp djApp, @NotNull final String suggestionName, @NotNull final Member sender, @NotNull final IReplyCallback interaction){
        TextChannel targetChannel = new IdUtils(djApp).getTextChannelFromId(Remy.CONFIG.getSuggestionsChannelId());
        if(Objects.isNull(targetChannel))
            throw new RuntimeException("Channel not found");
        Message message = targetChannel.sendMessageEmbeds(new SuggestionEmbed(suggestionName, Objects.requireNonNull(sender)).build()).complete();
        message.addReaction(Emoji.fromUnicode("\uD83D\uDC4D")).queue();
        message.addReaction(Emoji.fromUnicode("\uD83D\uDC4E")).queue();
        interaction.reply("Suggestion envoyée :white_check_mark:").setEphemeral(true).queue();
    }
}

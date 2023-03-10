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

import java.util.Objects;
import java.util.Scanner;

public class Remy {

    public static RemyConfig CONFIG;

    public static void main(String[] args){
        CONFIG = new RemyConfig();
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


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initActivity(DJApp app){
        if(CONFIG.isActivityEnabled()){
            Activity activity;
            String text = CONFIG.getActivityContent();
            activity = switch (CONFIG.getActivityType()) {
                case "STREAMING" -> Activity.streaming(text, CONFIG.getActivityUrl());
                case "LISTENING" -> Activity.listening(text);
                case "WATCHING" -> Activity.watching(text);
                default -> Activity.playing(text);
            };
            app.getJDA().getPresence().setActivity(activity);
        }

    }

    public static void sendSuggestion(DJApp djApp, String productName, Member sender, IReplyCallback interaction){
        TextChannel targetChannel = new IdUtils(djApp).getTextChannelFromId(Remy.CONFIG.getSuggestionsChannelId());
        if(Objects.isNull(targetChannel))
            throw new RuntimeException("Channel not found");
        Message message = targetChannel.sendMessageEmbeds(new SuggestionEmbed(productName, Objects.requireNonNull(sender)).build()).complete();
        message.addReaction(Emoji.fromUnicode("\uD83D\uDC4D")).queue();
        message.addReaction(Emoji.fromUnicode("\uD83D\uDC4E")).queue();
        interaction.reply("Suggestion envoy??e :white_check_mark:").setEphemeral(true).queue();
    }
}

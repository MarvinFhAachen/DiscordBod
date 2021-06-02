import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.List;

//https://discord.com/developers/applications/849612919569776670/oauth2
public class DiscordMain extends ListenerAdapter {

    public static JDA jda ;
    String it_gebabbel = "it-gebabbel";

    //Main create api conection to bot
    public static void main(String[] args) throws LoginException {
        String token = "ODQ5NjEyOTE5NTY5Nzc2Njcw.YLdtsQ.PtIsPSe8LeD8WodHrPwzo0vbMKQ";
        //jda = new JDABuilder();//veraltet
        jda = JDABuilder.createDefault(token).build();
        jda.addEventListener(new DiscordMain());

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase("!help")){
            EmbedBuilder em = new EmbedBuilder();
            //em.setDescription("Das is die description");
            em.addField("!help","Liste an allen befehlen.",true);
            em.addField("!essen","Random nachricht aus \"rezepte\uD83C\uDF5D\"",true);
            em.setTitle("Bot Infos:");
            em.setColor(Color.PINK);

            event.getChannel().sendMessage(em.build()).queue();
        }
        else if(args[0].equalsIgnoreCase("!essen")){
            //Get essens chanle
            TextChannel essen = jda.getTextChannelsByName("rezepte\uD83C\uDF5D", true).get(0);

            //get list of food
            List<Message> messages = essen.getHistory().retrievePast(100).complete();
            System.out.println("Messenges: "+ messages.size());

            //Pick random messages
            int chosen =  (int)(Math.random() * ((messages.size()-1) + 1));
            System.out.println("Random num: " +chosen);
            //send answer
            event.getChannel().sendMessage(messages.get(chosen)).queue();
        }



    }

    public EmbedBuilder MakeEmbed(String title,String description ){
        EmbedBuilder em = new EmbedBuilder();
        em.setDescription(description);
        //em.addField("Name","Value",false);
        em.setTitle(title);
        //em.setColor(Color.GREEN);
        return em;
    }
}


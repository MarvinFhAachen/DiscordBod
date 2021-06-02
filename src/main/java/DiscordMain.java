import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//https://discord.com/developers/applications/849612919569776670/oauth2
public class DiscordMain extends ListenerAdapter {

    public static JDA jda ;
    String it_gebabbel = "it-gebabbel";
    ctictactoe ticktacktoe;
    viergewind vier;

    //Main create api conection to bot
    public static void main(String[] args) throws LoginException {
        String token = "ODQ5NjEyOTE5NTY5Nzc2Njcw.YLdtsQ.PtIsPSe8LeD8WodHrPwzo0vbMKQ";
        //jda = new JDABuilder();//veraltet
        jda = JDABuilder.createDefault(token).build();
        jda.addEventListener(new DiscordMain());

    }



    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase("!help")){
            EmbedBuilder em = new EmbedBuilder();
            //em.setDescription("Das is die description");
            em.addField("!help","Liste an allen befehlen.",true);
            em.addField("!essen","Random nachricht aus \"rezepte\uD83C\uDF5D\"",true);
            em.addField("!tick","Spiel tick tack toe ",true);
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
        else if(args[0].equalsIgnoreCase("!tick")){

            ticktacktoe = new ctictactoe();
            event.getChannel().sendMessage(ticktacktoe.printmap()).queue();

        }
        else if(args[0].equalsIgnoreCase("!4")){
            vier = new viergewind();
            event.getChannel().sendMessage(vier.printmap()).queue();
        }
        else if(event.getAuthor().isBot()){
            if(args[0].equalsIgnoreCase( ":video_game:")){
                ticktacktoe.addreactions(event.getMessage());
            }
            else if(args[0].equalsIgnoreCase( ":video_game:VierGewinnt")){
                vier.addreactions(event.getMessage());
            }

        }



    }

    @Override
    public void onGuildMessageReactionAdd( GuildMessageReactionAddEvent event) {

        //wenn bot reaktion addet tu nichts
        if(event.getMember().getUser().isBot())
            return;
        //get message

         event.retrieveMessage().queue((message -> {
             //parse mesage
             String[] args = message.getContentRaw().split("\\s+");


             if(args[0].equalsIgnoreCase( ":video_game:")){

                 switch (event.getReactionEmote().toString()){
                     case   "RE:U+31U+20e3":ticktacktoe.makemove(1); break;
                     case   "RE:U+32U+20e3":ticktacktoe.makemove(2); break;
                     case   "RE:U+33U+20e3":ticktacktoe.makemove(3); break;
                     case   "RE:U+34U+20e3":ticktacktoe.makemove(4); break;
                     case   "RE:U+35U+20e3":ticktacktoe.makemove(5); break;
                     case   "RE:U+36U+20e3":ticktacktoe.makemove(6); break;
                     case   "RE:U+37U+20e3":ticktacktoe.makemove(7); break;
                     case   "RE:U+38U+20e3":ticktacktoe.makemove(8); break;
                     case   "RE:U+39U+20e3":ticktacktoe.makemove(9); break;
                 }
                 message.delete().queue();
                 event.getChannel().sendMessage(ticktacktoe.printmap()).queue();
             }
             else if(args[0].equalsIgnoreCase( ":video_game:VierGewinnt")){

                 switch (event.getReactionEmote().toString()){
                     case   "RE:U+31U+20e3":vier.makemove(1); break;
                     case   "RE:U+32U+20e3":vier.makemove(2); break;
                     case   "RE:U+33U+20e3":vier.makemove(3); break;
                     case   "RE:U+34U+20e3":vier.makemove(4); break;
                     case   "RE:U+35U+20e3":vier.makemove(5); break;
                     case   "RE:U+36U+20e3":vier.makemove(6); break;
                     case   "RE:U+37U+20e3":vier.makemove(7); break;
                 }
                // message.delete().queue();
                 event.getChannel().sendMessage(vier.printmap()).queue();
             }
         }));

    }

    public EmbedBuilder MakeEmbed(String title, String description ){
        EmbedBuilder em = new EmbedBuilder();
        em.setDescription(description);
        //em.addField("Name","Value",false);
        em.setTitle(title);
        //em.setColor(Color.GREEN);
        return em;
    }
}


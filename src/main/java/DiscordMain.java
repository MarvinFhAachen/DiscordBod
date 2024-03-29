
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;


import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//https://discord.com/developers/applications/849612919569776670/oauth2
public class DiscordMain extends ListenerAdapter {
    public static JDA jda ;


    String it_gebabbel = "it-gebabbel";
    ctictactoe ticktacktoe;
    viergewind vier;
    quis quiz;

    //Main create api conection to bot
    public static void main(String[] args) throws LoginException, IOException {

        //quis quiz2 = new quis();
        //quiz2.readquizze();
        //quiz2.start_add_quis("!test QuisName \nDas ist frage 1? Antwort1! Antwort2! %2");
        //System.out.println(quiz2.loadQuiz("Baum"));

        String js_string = new String( Files.readAllBytes(Paths.get("token.json")));
        JSONObject js = new JSONObject(js_string);
        String token = js.get("token").toString();
        System.out.println(token);
        jda = JDABuilder.createDefault(token).build();
        
        Thread thread = new Thread();
        
        

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
                 //message.delete().queue();
                 //event.getChannel().sendMessage(ticktacktoe.printmap()).queue();
                 message.editMessage(ticktacktoe.printmap()).queue();
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
                 //event.getChannel().sendMessage(vier.printmap()).queue();
                 message.editMessage(vier.printmap()).queue();
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


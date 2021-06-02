import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
//https://discord.com/developers/applications/849612919569776670/oauth2
public class DiscordMain {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "ODQ5NjEyOTE5NTY5Nzc2Njcw.YLdtsQ.PtIsPSe8LeD8WodHrPwzo0vbMKQ";
        builder.setToken(token);
        builder.build();
    }
}

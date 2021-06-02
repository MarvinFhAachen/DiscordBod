import net.dv8tion.jda.api.entities.Message;

public class viergewind {
    String con_e = ":black_medium_square:";
    String con_red = ":red_circle:";
    String con_orange = ":orange_circle:";

    boolean state = true;

    public static final String[] emochar ={"U+0031 U+20E3","U+0032 U+20E3","U+0033 U+20E3","U+0034 U+20E3","U+0035 U+20E3","U+0036 U+20E3","U+0037 U+20E3"};

    String[][] map ={{con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e},
                    {con_e,con_e,con_e,con_e,con_e,con_e}};

    String printmap(){
        String ret = ":video_game:VierGewinnt \n";
        for(int y = 0 ; y<6;y++){
            for(int x = 0 ; x<7;x++){
                ret += map[x][y];
            }
            ret += "\n";
        }
        ret += ":one::two::three::four::five::six::seven:";
        if(state)
            ret += con_red;
        else
            ret += con_orange;
        return ret;
    }

    void makemove(int row){
        row = row -1;
        System.out.println("makevove");
        System.out.println("row: "+row);
        for(int y = 0 ; y<6;y++){

            if(map[row][y].equalsIgnoreCase(con_orange) || map[row][y].equalsIgnoreCase(con_red)){
                if(y == 0)
                    return;
                if(state)
                    map[row][y-1] = con_red;
                else
                    map[row][y-1] = con_orange;

                state = !state;
                return;

            }
            else if(y == 5){
                if(state)
                    map[row][y] = con_red;
                else
                    map[row][y] = con_orange;

                state = !state;
                return;
            }
        }

    }



    void check_for_Winner(){
        int count = 0 ;
        //check horizontal
        for(int y = 0 ; y<6;y++){
              //  map[x][y];
        }
        //check vertical
        //check diagonale down
        //check diagonal up

    }
    void addreactions(Message ms){
        for (int x = 0 ; x<7;x++)
            ms.addReaction(emochar[(x)]).queue();


    }

}

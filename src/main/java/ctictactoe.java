

import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;

public class ctictactoe {
    String con_x = ":x:";
    String con_o = ":o:";
    String con_e = ":eight_pointed_black_star:";

    public static final String[] emochar ={"U+0031 U+20E3","U+0032 U+20E3","U+0033 U+20E3","U+0034 U+20E3","U+0035 U+20E3","U+0036 U+20E3","U+0037 U+20E3","U+0038 U+20E3","U+0039 U+20E3"};
    public static final String[] emochar2 ={":one:",":two:",":three:",":four:",":five:",":six:",":seven:",":eight:",":nine:"};

    boolean state = false;


    //ArrayList<ArrayList<String>> map;
    String[][] map = {  {con_e,con_e ,con_e},//[0][0]//[0][1] // 00     01  x
                      {con_e,con_e ,con_e},
                        {con_e,con_e ,con_e}};                 //10     11
                                                                //y
    String printmap(){
        String ret  = ":video_game: \n";
        for(int y = 0 ; y<3;y++){
            for (int x = 0 ; x<3;x++){
                ret += map[y][x] ;
            }
            for (int x = 0 ; x<3;x++){
                ret += emochar2[y*3 +x ] ;
            }
            ret += "\n";
        }
        if(wincheck())
            ret += " \n Gewonnen";

        return ret ;
    }

    boolean makemove(int pos){

        //get x and y
        int x = (pos-1) %3;
        int y = (pos-1) /3;
        //System.out.println("x: "+x );
        //System.out.println("y: "+y );


        //check if emty
        if(map[y][x] != con_e)
            return false ;

        //write simbel
        if(state)
            map[y][x] = con_x;
        else
            map[y][x] = con_o;

        //change state
        state = !state;
        //System.out.println(wincheck());
        return  true ;
    }

    void addreactions(Message ms){
        for(int y = 0 ; y<3;y++){
            for (int x = 0 ; x<3;x++){
                 if(map[y][x] == con_e)
                     ms.addReaction(emochar[(x + y*3)]).queue();//add reaction emochar[(x+1 + y*3)]
            }
        }
    }

    boolean wincheck(){
        boolean win = false;
        //horizontal
        if(map[0][0].equalsIgnoreCase(map[0][1]) && map[0][1].equalsIgnoreCase(map[0][2]) && !map[0][0].equalsIgnoreCase(con_e))
            win = true;
        if(map[1][0].equalsIgnoreCase(map[1][1]) && map[1][1].equalsIgnoreCase(map[1][2])&& !map[1][0].equalsIgnoreCase(con_e))
            win = true;
        if(map[2][0].equalsIgnoreCase(map[2][1]) && map[2][1].equalsIgnoreCase(map[2][2])&& !map[2][0].equalsIgnoreCase(con_e))
            win = true;

        //vertical
        if(map[0][0].equalsIgnoreCase(map[1][0]) && map[1][0].equalsIgnoreCase(map[2][0])&& !map[0][0].equalsIgnoreCase(con_e))
            win = true;
        if(map[0][1].equalsIgnoreCase(map[1][1]) && map[1][1].equalsIgnoreCase(map[2][1])&& !map[0][1].equalsIgnoreCase(con_e))
            win = true;
        if(map[0][2].equalsIgnoreCase(map[1][2]) && map[1][2].equalsIgnoreCase(map[2][2])&& !map[0][2].equalsIgnoreCase(con_e))
            win = true;

        //diagpnal up
        if(map[0][0].equalsIgnoreCase(map[1][1]) && map[1][1].equalsIgnoreCase(map[2][2])&& !map[0][0].equalsIgnoreCase(con_e))
            win = true;
        //diagpnal down
        if(map[0][2].equalsIgnoreCase(map[1][1]) && map[1][1].equalsIgnoreCase(map[2][0])&& !map[0][2].equalsIgnoreCase(con_e))
            win = true;

        return win;
    }
}

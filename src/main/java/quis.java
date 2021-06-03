import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class quis {

    JSONObject quizze;
    JSONObject quiz;



    void  readquizze() throws IOException {
        String js_string = new String( Files.readAllBytes(Paths.get("quiz.json")));
        quizze = new JSONObject(js_string);

    }
    boolean loadQuiz(String name){
        try {
             quiz = (JSONObject) quizze.get(name);
        }catch (JSONException exeot){
            return false;
        }

    return true;
    }



    //wipwipwip
    boolean start_add_quis(String str){
        String[] rows = str.split("\n");
        JSONObject newquiz = new JSONObject();

        if(rows.length < 2)
            return false;

        //Name
        newquiz.put("name",rows[0].split("\\s+")[1]);
        System.out.println(newquiz.get("name").toString());

        //name vergeben
        if(loadQuiz(newquiz.get("name").toString()))
            return false;

        JSONArray jsq = new JSONArray();
        newquiz.put("Questions",jsq);


        for(int i = 1 ; i<rows.length;i++){
            JSONObject jr = new JSONObject();
            String[] ele = str.split("%");

            //Questtion
            String question = ele[0];
            jr.put("Question",question);

            //Answers
            JSONArray jaq = new JSONArray();
            for(int x = 1 ; x<ele.length-1;x++){
                jaq.put(ele[x]);
            }
            jr.put("Answers",jaq);

            //right
            jr.put("Right",ele[ele.length-1]);
 
        }



        return true;
    }


}


//max 4 antworten
//!newquizz test
//Frage frage frage% antwort a% antwort b% 2
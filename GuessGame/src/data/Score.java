package data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Score {

    HashMap<Float, String> inputHashpmap = new HashMap<Float, String>(); //unorderd everything
    HashMap<Integer, String> returnTopFive = new HashMap<Integer, String>(); //ordered everything

    public String getTopFive()
    {
        
        if(returnTopFive.isEmpty()) return "";
        StringBuilder lines = new StringBuilder();
        
        for(int i = 0 ; i < returnTopFive.size(); i++)
        {
            lines.append(returnTopFive.get(i+1));
            lines.append(System.getProperty("line.separator"));

        }

        return lines.toString();
    }
    //idea is that every time new score is added
    // but returned first 5
    public void evalueteTop(float score) {

        LocalTime myObj = LocalTime.now();

        if (inputHashpmap.isEmpty()) {

            inputHashpmap.put(score, String.valueOf(score) + "----" + myObj.toString());

        }


        if (inputHashpmap.get(score)== null)  
        {

            inputHashpmap.put(score, String.valueOf(score) + "----" + myObj.toString());

        }




        List<Float> arr = new ArrayList<>(inputHashpmap.keySet());

        arr.sort(Comparator.reverseOrder());


           //extract all  floats into array
            //use method Arrays.sort
            //make new top 5 hashmap from previous list, extract data from  inputHashpmap into new hasmap
          

            //generate top 5
            for (int i =1  ; i<= arr.size()   ; i++) {  //just to be safe if there are less than 5 records
                returnTopFive.put(i, inputHashpmap.get(arr.get(i-1)));
                if (i == 5)  break;
        }

            //var getScore = Float.valueOf(topFive.get(Integer.valueOf(i)).split("----")[0]).floatValue();


    }

}

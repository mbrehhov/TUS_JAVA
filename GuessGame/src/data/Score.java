package data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public record Score() {

    private static HashMap<Float, String> inputHashpmap = new HashMap<Float, String>(); // unorderd everything
    private static HashMap<Integer, String> returnTopFive = new HashMap<Integer, String>(); // ordered everything

    public Object[][] getTopFive() {

        if (returnTopFive.isEmpty())
            return null;

        Object[][] multiObject = new Object[returnTopFive.size()][];

        for (int i = 0; i < returnTopFive.size(); i++) {
            // later here add name of gamer also.
            multiObject[i] = new Object[4];

            String[] splitData = returnTopFive.get(i + 1).split("----");

            multiObject[i][0] = splitData[0];

            multiObject[i][1] = Level.findLevel(Float.parseFloat(splitData[0])).getUrl();

            multiObject[i][2] = splitData[1];
            multiObject[i][3] = splitData[2];

        }

        return multiObject;
    }

    // idea is that every time new score is added
    // but returned first 5
    public void evalueteTop(String name, float score) {

        LocalTime local_time = LocalTime.now();

        if (inputHashpmap.isEmpty()) {

            inputHashpmap.put(score, String.valueOf(score) + "----" + local_time.toString() + "----" +name);

        }

        if (inputHashpmap.get(score) == null) {

            inputHashpmap.put(score, String.valueOf(score) + "----" + local_time.toString() + "----" +name);

        }

         // extract all floats into array
        // use method Arrays.sort
       
        List<Float> arr = new ArrayList<>(inputHashpmap.keySet());
         arr.sort(Comparator.reverseOrder());

        // make new top 5 hashmap from previous list, extract data from inputHashpmap
        // into new hasmap where index is place (ex. 1 - first place, 2 - second place..etc)

        // generate top 5
        for (int i = 1; i <= arr.size(); i++) { // just to be safe if there are less than 5 records
            returnTopFive.put(i, inputHashpmap.get(arr.get(i - 1)));
            if (i == 5)
                break;
        }

    }

}

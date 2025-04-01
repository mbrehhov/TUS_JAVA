package data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Score {

    private static HashMap<Float, String> inputHashpmap = new HashMap<Float, String>(); // unorderd everything
    private static HashMap<Integer, String> returnTopFive = new HashMap<Integer, String>(); // ordered everything

    public Object[][] getTopFive() {

        AtomicInteger index = new AtomicInteger(1);

        List<Float> arr = new ArrayList<>(inputHashpmap.keySet());
         arr.sort(Comparator.reverseOrder());

          arr.stream()
         .limit(5)
         .forEach( (n) -> {
            returnTopFive.put(index.getAndIncrement(), inputHashpmap.get(n));
        } );

        
       
        if (returnTopFive.isEmpty())
            return null;

        Object[][] multiObject = new Object[returnTopFive.size()][];

        for (int i = 0; i < returnTopFive.size(); i++) {
            multiObject[i] = new Object[4];

            String[] splitData = returnTopFive.get(i + 1).split("----");
            multiObject[i][0] = splitData[0];
            multiObject[i][1] = Level.findLevel(Float.parseFloat(splitData[0])).getUrl();
            multiObject[i][2] = splitData[1];
            multiObject[i][3] = splitData[2];

        }

        return multiObject;
    }

  
    public void evalueteTop(String name, float score) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.now();
        String local_time = formatter.format(time);


        if (inputHashpmap.isEmpty()) {

            inputHashpmap.put(score, String.valueOf(score) + "----" + local_time + "----" +name);

        }

        if (inputHashpmap.get(score) == null) {

            inputHashpmap.put(score, String.valueOf(score) + "----" + local_time + "----" +name);

        }

    }

}

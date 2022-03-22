package Quiz;

import java.io.*;
import java.util.ArrayList;


public class read {

    public static ArrayList<Object[]> read() throws IOException {

        ArrayList<Object[]> al = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("PerfectPoliciesQuiz_SampleData.txt"));
            FileInputStream fstream = new FileInputStream("PerfectPoliciesQuiz_SampleData.txt");
            DataInputStream in = new DataInputStream(fstream);
            int n = 1;
            String line = ""; // create line string
            while ((line = br.readLine()) != null) // while line not empty
            {
                String[] breaker = new String[10];
                breaker[0] = (line);
                breaker[1] = (br.readLine());
                breaker[2] = (br.readLine());
                breaker[3] = (br.readLine());
                breaker[4] = (br.readLine());
                breaker[5] = (br.readLine());
                breaker[6] = (br.readLine());
                breaker[7] = (br.readLine());
                breaker[8] = (br.readLine());
                breaker[9] = (br.readLine());
                
                al.add(breaker);
            }


            br.close(); // close buffered reader
            in.close();
            fstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return al;
    }
}







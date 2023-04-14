import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Analyzer {
	//compute polynomial hash code according to page 413 in our book
	//Use horner's method 
	public static int hashCode(int a, String x) {
            int b = x.length();
            int hashCode = 0;
            for(int i = 0; i < b; i++){
                hashCode = (int)x.charAt(i)+ a*hashCode;
            }
            return hashCode;
	}
	
        public static HashMap<Integer, List<String>> createMap(int a)throws FileNotFoundException {
            HashMap<Integer, List<String>> m = new HashMap<Integer, List<String>>();
            File inputFile = new File("dictionary.txt");
            Scanner in = new Scanner(inputFile);
            while(in.hasNext())
            {
                String word = in.nextLine();
                int code = hashCode(a, word);
                if(!m.containsKey(code)){
                    m.put(code, new ArrayList<String>());
                }
                m.get(code).add(word);
            }
            in.close();
        return m;
        }
	
	public static void main(String[] args) throws FileNotFoundException {
            System.out.println("Please check the dictionary.txt file directory (in createMap) just in case, may need to add src/");
            System.out.println("hashCode(1, stop): " + hashCode(1, "stop"));
            System.out.println("hashCode(2, stop): " + hashCode(2, "stop"));
            System.out.println("hashCode(3, stop): " + hashCode(3, "stop"));
            System.out.println("hashCode(1, pots): " + hashCode(1, "pots"));
            System.out.println("hashCode(2, pots): " + hashCode(2, "pots"));
            System.out.println("hashCode(3, pots): " + hashCode(3, "pots"));
            System.out.println("hashCode(1, tops): " + hashCode(1, "tops"));
            System.out.println("hashCode(2, tops): " + hashCode(2, "tops"));
            System.out.println("hashCode(3, tops): " + hashCode(3, "tops"));
            
            //initial test for 17
            int a = 17;
            HashMap<Integer, List<String>> m = createMap(a);
            int mapSize = m.size();
            
            int collisions = 0;
            int key = 0;
            for(int e : m.keySet()){
                if (m.get(e).size() > collisions){
                    key = e;
                    collisions = m.get((Integer)e).size();
                }
            }
          
            System.out.println("a: "+ a +
                               "\r\nMap Size: " + mapSize +
                               "\r\nMax Collisions: " + collisions +
                               "\r\nKey: " + key);
            if(collisions < 10){System.out.println("Values: " + m.get(key));}
            
            //loop for a = 32 through 41
            a = 31;
            while(a<42){
            
            a++;
            m = createMap(a);
            mapSize = m.size();
            
            collisions = 0;
            key = 0;
            for(int e : m.keySet()){
                if (m.get(e).size() > collisions){
                    key = e;
                    collisions = m.get((Integer)e).size();
                }
            }
          
            System.out.println("a: "+ a +
                               "\r\nMap Size: " + mapSize +
                               "\r\nMax Collisions: " + collisions +
                               "\r\nKey: " + key);
            if(collisions < 10){System.out.println("Values: " + m.get(key));}        
            }
        }
}

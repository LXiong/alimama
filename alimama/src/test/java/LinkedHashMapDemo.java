import java.util.*;

public class LinkedHashMapDemo {

   private static final int MAX_ENTRIES = 4;

   public static void main(String[] args) {
      LinkedHashMap lhm = new LinkedHashMap(MAX_ENTRIES + 1, .75F, false) {

         protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
         }
      };
      lhm.put(0, "H");
      lhm.put(1, "E");
      lhm.put(2, "L");
      lhm.put(3, "L");
      lhm.put(4, "O");

      System.out.println("" + lhm);

   }
}
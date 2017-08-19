import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        java.util.List<Integer> integers=new ArrayList<Integer>();
        
        integers.add(5);
        
        integers.add(7);
        
        integers.add(8);
        
        
        integers.add(6);
        
        integers.add(7);
        
        
        integers.add(5);
        
        integers.add(70);

        
       Map<Integer,Object> map = new LinkedHashMap<Integer, Object>();
       
       for(Integer integer:integers){
    	   map.put(integer, integer);
       }
       
       System.out.println(map.values());
	

       System.out.println(map.keySet());
	}

}

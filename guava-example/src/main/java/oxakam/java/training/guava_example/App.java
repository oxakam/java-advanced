package oxakam.java.training.guava_example;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class App 
{
    public static void main( String[] args )
    {    	
    	Multiset<String> animals = HashMultiset.create();
    	animals.add("cat");
    	animals.add("dog");
    	animals.add("giraf");
    	
    	animals.forEach(System.out::println);
    }
}

package exercise1;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 1: You have a Map<String, String> that holds in each element a country with its capital (countries Map)
 *              a) Find all the countries that start with 'R' character, iterating through the keys of the Map using
 *              keySet() method
 *              b) Find all the countries that start with 'R' character, iterating through the keys of the Map using
 *              Map.Entry, and convert all the characters of the countries found to lowercase
 *              c) Find the capital city with the longest name, iterating through the values of the Map (values() method)
 *
 */
public class Exercise1 {

    // A map that holds some key-value pairs, the key represents the country and its value represents
    // the capital city of the country
    private Map<String, String> countries;


    public Exercise1(Map<String, String> countries){
        this.countries = countries;
    }

    public List<String> iteratingOverKeys(){

        // The list of countries that start with the 'R' character
        List<String> seekingCountries = new ArrayList<String>();

        // TODO Exercise #1 a) You need to iterate over the map keys using a foreach loop (see Map.keySet())
        // TODO Exercise #1 a) and add the countries that start with 'R' character into the seekingCountries list
        // TODO Exercise #1 a) hint: see String documentation

Set<String> key= countries.keySet();
        for(String i:key)
        if(i.startsWith("R")) seekingCountries.add(i);
        return seekingCountries;
    }

    public List<String> iteratingOverEntries(){

        // The list of countries that contain the 'Q' character
        List<String> seekingCountries = new ArrayList<String>();

        // TODO Exercise #1 b) You need to iterate over the map entries using a foreach loop (see Map.Entry)
        // TODO Exercise #1 b) and convert to lowercase (hint: String documentation) all the countries that contain 'Q'
        // TODO Exercise #1 b) or 'q' letter into the seekingCountries list
      //  Set<String> key= countries.keySet();
      //  for(String i:key)
       // if(i.contains("Q")|| i.contains("q")) seekingCountries.add(i);
     for(Map.Entry<String, String> e:countries.entrySet())
     if(e.getKey().contains("q")||e.getKey().contains("Q"))
                    {
                        seekingCountries.add(e.getKey().toLowerCase());
                                                         }





        return seekingCountries;
    }

    public String iteratingOverValues(){

        // The capital city with the longest name
        String seekingCapital = "";
        int max=0;

        // TODO Exercise #1 c) You need to iterate over the map values using a foreach loop (see Map.values())
        // TODO Exercise #1 c) and find the capital city with the longest name
        for(String c: countries.values()) {
            if (c.length() > max) {
                max = c.length();
                seekingCapital = c;
            }
        }
        return seekingCapital;
    }
}

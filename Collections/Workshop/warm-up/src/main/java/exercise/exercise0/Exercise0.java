package exercise.exercise0;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {
    ArrayList<Integer> lista=new ArrayList<Integer>();

    public Exercise0(){

    }

    public void iterateThroughList() {

        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it

        // TODO Exercise #0 a) Hint: Don't forget to specify the type of the list (Integer, String etc.)
        for (int i = 0; i < 5; i++)
            lista.add(i);


        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its elements
        System.out.println("b)");
        ListIterator<Integer> it = lista.listIterator();
        while (it.hasNext()) {
            int x = it.next();
            System.out.println(x + " ");
        }
        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        System.out.println("c)");
        for (int i = 0; i < lista.size(); i++)
            System.out.println(lista.get(i));

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        System.out.println("d)");
        for(int i:lista)
          System.out.println(i);

    }
    public static void main(String[] args) {
        // TODO Exercise #0 e) Create a new instance of Exercise0 class and call the iterateThroughList() method
   Exercise0 obj=new Exercise0();
        obj.iterateThroughList();

    }
}

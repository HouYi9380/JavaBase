package cn.myself.day2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WY on 2017/11/13.
 */
public class TestCollection {

    @Test
    public void testArrayList(){
        List<Integer> list = new ArrayList<Integer>();
        list.add( 1 );
        list.add( 2 );
        list.add( 3 );
        list.add( 4 );
        list.add( 5 );
        list.add( 6 );
        list.add( 7 );
        list.add( 8 );
        list.add( 9 );
        list.add( 10 );
        list.add( 11 );

        System.out.println("over");
    }
    @Test
    public void testLinkedList(){
        List<Integer> list = new LinkedList<Integer>();
        list.add( 1 );
        list.add( 2 );
        list.add( 3 );
        list.add( 4 );
        list.add( 5 );
        list.add( 6 );
        list.add( 7 );
        list.add( 8 );
        list.add( 9 );
        list.add( 10 );
        list.add( 11 );

        System.out.println("over");
    }

}

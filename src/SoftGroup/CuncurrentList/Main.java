package SoftGroup.CuncurrentList;

import java.util.*;
import java.util.List;

/**
 * Created by Марiна on 07.04.2017.
 */
public class Main {


    public static void main(String[] args) {

        List<Integer> myList = new MyCuncurrnetList<Integer>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
       // System.out.println(myList.size());
        Iterator<Integer> iter = myList.iterator();
        while (iter.hasNext())
            System.out.print("[" +iter.next()+"] ");
        myList.remove(2);
        while (iter.hasNext())
            System.out.print("[" +iter.next()+"] ");
        List<Integer> myList2 = new MyCuncurrnetList<>();
        myList2.add(45);
        myList2.add(56);
        myList2.add(6);
        myList2.add(3);
        myList2.add(5);
        myList2.add(1);
        myList2.add(222);
        myList2.add(3);
        myList2.add(67);
        myList.addAll(1,myList2);
        myList.add(9,6745);


        //myList.remove(6745);
        Iterator<Integer> iter2 = myList.iterator();
        while (iter2.hasNext())
            System.out.print("[" +iter2.next()+"] ");

        System.out.println();
        List<String> stringList = new MyCuncurrnetList<>();
        stringList.add("sd");
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        //stringList.remove("c");
        stringList.remove(2);
        Iterator<String> itter3 = stringList.iterator();
        while (itter3.hasNext())
            System.out.print("[" +itter3.next()+"] ");

        List<Integer> intListOrigin= new ArrayList<>();
        intListOrigin.add(2);
        intListOrigin.add(4);
        intListOrigin.add(5);
        intListOrigin.add(5);
        intListOrigin.add(223);


        System.out.println();
        Integer[] a = new Integer[2];
        //System.out.println(intListOrigin.toArray());
        myList2.retainAll(intListOrigin);

        Iterator<Integer> iter4 = myList2.iterator();
        while (iter4.hasNext())
            System.out.print("[" +iter4.next()+"] ");

       a=myList2.toArray(a);
        for (Integer number : a) {
            System.out.println("Number = " + number);
        }


        //System.out.println(myList.lastIndexOf(3));


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Iterator


        List<Integer> subList= myList.subList(1, 7);
        Iterator<Integer> iter3 = subList.iterator();
        while (iter3.hasNext())
            System.out.print("[" +iter3.next()+"] ");
        //POMYLKA!!!!

       Integer i = 2;
       myList.remove( i);
        while (iter.hasNext())
            System.out.print("[" +iter.next()+"] ");
        System.out.println(myList.contains((Integer) 2));







    }





}

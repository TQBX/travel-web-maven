import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Summerday
 */
public class emptyListTest {

    @Test
    public void test(){

        List<Integer> list = Collections.emptyList();
        list.add(5);
        System.out.println(list);//java.lang.UnsupportedOperationException

        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println("dede");
        }


        //List<Integer> list1 = new ArrayList<>();
        //list1.add(1);
        //list1.add(1);
        //list1.add(1);
        //list1.add(1);
        //list1.add(1);
        //System.out.println(list1);
        //list = list1;
        //System.out.println(list);
    }
    @Test
    public void test1(){

        List<Integer> list1 = new ArrayList<>();
        System.out.println(list1.size());

        List<Integer> list2 = null;
        System.out.println(list2.size());

    }
}

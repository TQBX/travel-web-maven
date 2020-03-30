import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Summerday
 */
public class testForEach {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        list.add(p1);
        list.add(p2);
        for (Person person : list) {
            person.setUsername("p3");
        }
        System.out.println(list);





    }
}

class Person{
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                '}';
    }
}
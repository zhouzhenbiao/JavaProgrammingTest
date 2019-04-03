package chapter17;

import java.io.*;
import java.util.Date;

public class TestClassDataScope implements Serializable{
    /**
     * name
     */
    private String name;

    /**
     * age
     */
    private int age;

    private transient Date birth;

    public TestClassDataScope() {
        this(0, "not name", new Date());
    }

    public TestClassDataScope(int age, String name, Date birth) {
        this.age = age;
        this.name = name;
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public Date getBirth() {
        return birth;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name : " + name + "\nage : " + age + "\nbirth : " + birth;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        TestClassDataScope Test = new TestClassDataScope();
//        TestClassDataScope Test = new TestClassDataScope(18, "ranjiangwei", new Date());
//        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("D:/temp.txt", true));
//        output.writeObject(Test);
//        output.close();
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("D:/temp.txt"));
        TestClassDataScope newTest = (TestClassDataScope) input.readObject();
        System.out.println(newTest.toString());
        TestClassDataScope newTest1 = (TestClassDataScope) input.readObject();
        System.out.println(newTest1.toString());
        TestClassDataScope newTest2 = (TestClassDataScope) input.readObject();
        System.out.println(newTest2.toString());
        input.close();
    }
}

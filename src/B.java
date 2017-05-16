/*
import java.lang.reflect.Field;

class F {

    public F (int i) {

        this.i = i;
int
    }

    int i;

}

class Test {

    public static void main(String[] args)  {

        F a = new F(5);

        Class c = a.getClass();

        Field[] publicFields = c.getFields();

        for (Field field : publicFields) {

            Class fieldType = field.getType();

            System.out.println("Name: " + field.getName());

            System.out.println("Type: " + fieldType.getName());

        }

    }

}*/

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;


import javax.swing.*;

public class A extends JFrame {

    private JButton yes = new JButton("yes");

    public A()  {

        this.add(yes);

        this.add(yes);

        this.add(new JButton("no"));

    }
    public static void main(String[] args) {

       A frame = new A();

        frame.setSize(200, 200);

        frame.setVisible(true);

    }
}


package top.dreamcenter.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class Main {
    private static void print (String result){
        System.out.println(result);
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        Model model = context.getBean(Model.class);

        print(model.testCommonMethod());
        print(model.testIgnoreMethod());
        print(model.testTipMethod());
        print(model.testSkipMethod());
        print(model.testStopMethod());

        print("end");
    }
}

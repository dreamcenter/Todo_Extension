package top.dreamcenter.test;

import top.dreamcenter.todo.Degree;
import top.dreamcenter.todo.TodoB;

public class Model {

    public String testCommonMethod() {
        System.out.println("-testCommonMethod");
        return "testCommonMethod-";
    }

    @TodoB(degree = Degree.IGNORE)   //TODO
    public String testIgnoreMethod() {
        System.out.println("-testIgnoreMethod");
        return "testIgnoreMethod-";
    }

    @TodoB(degree = Degree.TIP)     // TODO
    public String testTipMethod() {
        System.out.println("-testTipMethod");
        return "testTipMethod-";
    }

    @TodoB(degree = Degree.SKIP)
    public String testSkipMethod() {
        System.out.println("-testSkipMethod");
        return "testSkipMethod-";
    }

    @TodoB(progress = 99,degree = Degree.STOP)
    public String testStopMethod() {
        System.out.println("-testStopMethod");
        return "testStopMethod-";
    }

}

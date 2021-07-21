package top.dreamcenter.todo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class TodoMethodAspect {

    /**
     * check and find if @TODO_ annotation exist before each method
     * @param point
     * @return
     * @throws Throwable
     */
    public Object check(ProceedingJoinPoint point) throws Throwable {
        Class clazz = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();

        try {
            Method md = clazz.getDeclaredMethod(methodName,signature.getParameterTypes());
            if (md.isAnnotationPresent(TodoB.class)) {
                TodoB todoB = md.getAnnotation(TodoB.class);
                if (todoB.progress()>=100) return point.proceed();
                return dealWithDegree(todoB,signature,point);
            } else {
                return point.proceed();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return point.proceed();
        }
    }

    /**
     * to different degree, deal with different strategies
     * @param todoB
     * @param signature
     * @param point
     * @throws Throwable
     */
    private Object dealWithDegree (TodoB todoB, MethodSignature signature, ProceedingJoinPoint point) throws Throwable {
        String methodName = signature.getName();
        switch (todoB.degree()) {
            case IGNORE :
                return point.proceed();
            case TIP:
                System.out.println("{{{(>_<)}}} a method not realized!");
                System.out.println("TIP_>" + signature);
                return point.proceed();
            case SKIP:
                System.out.println("{{{(>_<)}}} a method not realized!");
                System.out.println("SKIP_>" + signature);
                return null;
            case STOP:
                throw new MethodNotRealizedException(methodName);
            default:return null;
        }
    }
}

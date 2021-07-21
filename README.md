# Todo Extension

---

## 功能

使用@**TodoB**注解来约束未完成函数的使用，搭配TODO使用，形成约束型的TODO函数



## 配置

### ※ spring模式   [推荐程度 ★ ★ ★ ★ ★]

1. 从release中下载todo-version.jar包

2. 导入该jar包和依赖的jar包(spring框架)到项目中

3. 书写配置文件

   ```xml
   <bean id="todoMethodAspect" class="top.dreamcenter.todo.TodoMethodAspect"/>
   <aop:config>
       <aop:pointcut id="cut" expression="@annotation(top.dreamcenter.todo.TodoB)"/>
       <aop:aspect ref="todoMethodAspect">
           <aop:around method="check" pointcut-ref="cut"/>
       </aop:aspect>
   </aop:config>
   ```

4. 之后便在需要todo的方法上加上@**Todo**注解并且后面给一条// **TODO** 注释即可! [加// TODO 是因为该注解还没形成生态，IDE给不了TODO的计划列表，当然加不加随你啦] 



## 参数

### @TodoB 注解

|  类型  |   名称   |   默认值   |                         描述                         |
| :----: | :------: | :--------: | :--------------------------------------------------: |
|  int   | progress |     0      | 表示方法的编写进度，≥100时表示编写完全结束，正常执行 |
| Degree |  degree  | Degree.TIP |    表示约束等级，详情可以见下面的Degree枚举类描述    |

### Degree枚举类

| 枚举元素 |                             描述                             |
| :------: | :----------------------------------------------------------: |
|  IGNORE  |     方法未完成时，**仍然执行**该方法且**不做出任何回应**     |
|   TIP    | 方法未完成时，先**给出提示**描述未完成，然后**仍然执行**该方法 |
|   SKIP   | 方法未完成时，先**给出提示**描述未完成，然后**跳过**该方法的执行 |
|   STOP   | 方法未完成时，**跳过**该方法的执行且抛出MethodNotRealizedException**异常** |



## 优雅的关闭Todo注解

* 可以去掉扫描注解的aop配置，只留下注解不会被扫描到，不会影响任何性能
* 可以在一个函数编写结束时随手删掉的啦

## 例子

```JAVA
@TodoB(degree = Degree.SKIP)
public String testSkipMethod() {
    System.out.println("-testSkipMethod");
    return "testSkipMethod-";
}
```

当然我上传到github上的仓库中也包含了一个test测试用例，

下载jar包直接找release里面的就行了，已经导出好了



## 创造这个注解的原因

### 场景再现

我们时常会遇到这种情况:

总是会在项目中加入一个个的**TODO**来告诫自己还有什么没有做，

但是，我们总是会忘记自己仍然有todo没有完成，

于是乎，调用了一个未完成的方法，结果也没有返回任何的错误,

结果找BUG找半天。

### 解决措施

鉴于此，就想通过一个优雅的方式来处理这种场景，

有朋友说给todo直接抛个异常就好了，但是我认为不然，

一方面如果这边抛出了异常，会有很多相关的方法会被干扰，

比如继承关系树可能都要修改，有的原本抛出小的异常现在却需要抛出大的异常。

另一方面，一个未完成的方法我们可能会有不同的约束条件。

想了一伙儿，决定用 **注解+AOP** 来实现, ♪(^∇^*)
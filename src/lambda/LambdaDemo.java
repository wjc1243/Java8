package lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {

    List<Employee> list = Arrays.asList(
            new Employee("zhangsan", 20, 100000.0),
            new Employee("lisi", 20, 200000.0),
            new Employee("wangwu", 30, 300000.0),
            new Employee("zhaoliu", 40, 400000.0),
            new Employee("tianqi", 50, 500000.0),
            new Employee("liuba", 60, 600000.0)
    );

    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r1.run();
        System.out.println("----------------");

        Runnable r2 = () -> System.out.println("Hello Lambda!");
        r2.run();
    }

    @Test
    public void test2(){
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello World!");
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (x, y) -> {return Integer.compare(x, y);};
        System.out.println(comparator.compare(1, 2));
    }

    @Test
    public void test5(){
        System.out.println(opration(88, (x) -> x * x));
    }

    private static Integer opration(Integer num, MyFun myFun){
        return myFun.getValue(num);
    }

    @Test
    public void test6(){
        Collections.sort(list, (x, y) -> {
         if(x.getAge() == y.getAge()){
             return x.getName().compareTo(y.getName());
         }else{
             return -Integer.compare(x.getAge(), y.getAge());
         }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void test7(){
        Consumer<String> con = System.out::println;
        con.accept("Hello World");
    }

    @Test
    public void test8(){
        Employee e = new Employee("zzz", 11, 25345.0);
        Supplier<String> sup = e::getName;
        System.out.println(sup.get());
    }

    @Test
    public void test9(){
        Supplier<Employee> sup = Employee::new;
        Employee e = sup.get();
        e.setAge(18);
        System.out.println(e);
    }

    @Test
    public void test10(){
        Stream<Employee> stream = list.stream().filter((e) -> {
            System.out.println("筛选年龄大于35的结果：");
            return e.getAge() > 35;
        });
        stream.limit(2).forEach(System.out::println);
    }

    @Test
    public void test11(){
        Stream<Employee> stream = list.stream().filter((e) -> {
            System.out.println("筛选年龄大于35的结果：");
            return e.getAge() > 35;
        });
        stream.skip(2).forEach(System.out::println);
    }

    @Test
    public void test12(){
        List<String> list2 = Arrays.asList("aaa", "bbb", "ccc");
        list2.stream().map((e) -> e.toUpperCase()).forEach(System.out::println);

        list.stream().map((e) -> e.getName()).forEach(System.out::println);
    }

    @Test
    public void test13(){
        System.out.println(
                list.stream().allMatch((e) -> e.getAge().equals(20))
        );

        System.out.println(
                list.stream().anyMatch((e) -> e.getAge().equals(20))
        );

        System.out.println(
                list.stream().noneMatch((e) -> e.getAge().equals(220))
        );

        Optional<Employee> first = list.stream().sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge())).findFirst();
        System.out.println(first.get());

        for (int i = 0; i < 10; i++) {
            Optional<Employee> any = list.stream().filter((e) -> e.getAge() == 20)
                    .findAny();
            System.out.println(any.get());
        }
    }

    @Test
    public void test14(){
        List<Integer> list2 = Arrays.asList(1,2,3,4,5);
        Integer reduce = list2.stream()
                .reduce(1, (x, y) -> x + y);
        System.out.println(reduce);

        List<String> list3 = Arrays.asList("a", "b", "c");
        String reduce1 = list3.stream()
                .reduce("2", (x, y) -> x + y);
        System.out.println(reduce1);

        System.out.println(list.stream()
                .map((e) -> e.getSalary())
                .reduce((x, y) -> x + y));
    }

    @Test
    public void test15(){
        list.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Map<Integer, List<Employee>> collect = list.stream().collect(Collectors.groupingBy(/*(e) -> e.getAge()*/Employee::getAge));
        System.out.println(collect);
    }
}

package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LambdaDemo2 {

    List<Employee> emps = Arrays.asList(
            new Employee("zhangsan", 20, 100000.0),
            new Employee("lisi", 20, 200000.0),
            new Employee("wangwu", 30, 300000.0),
            new Employee("zhaoliu", 40, 400000.0),
            new Employee("tianqi", 50, 500000.0),
            new Employee("liuba", 60, 600000.0)
    );

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> collect = list.stream()
                .map((e) -> e*e)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test2(){
        Optional<Integer> reduce = emps.stream()
                .map((e) -> 1)
                .reduce((x, y) -> x+y);
        System.out.println(reduce.get());
    }
}

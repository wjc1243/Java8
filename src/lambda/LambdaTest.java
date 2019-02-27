package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2,3));

        //获取年龄大于35的员工信息
        List<Employee> employees = Arrays.asList(
                new Employee("zhangsan", 18, 100000.0),
                new Employee("lisi", 20, 200000.0),
                new Employee("wangwu", 30, 300000.0),
                new Employee("zhaoliu", 40, 400000.0),
                new Employee("tianqi", 50, 500000.0),
                new Employee("liuba", 60, 600000.0)
        );

        //获取获取年龄大于35的员工信息
        List<Employee> employees1 = filterEmployees(employees, (e) -> e.getAge() > 35);
        employees1.forEach(System.out::println);

        System.out.println("------------------------");

        //获取前两个年龄大于35的员工信息
        employees.stream()
                .filter((a) -> a.getAge() > 35)
                //前两个
                .limit(2)
                .forEach(System.out::println);

        System.out.println("------------------------");

        //获取所有人的名字
        employees.stream()
                .map((e) -> e.getName())
                .limit(3)
                .forEach(System.out::println);
    }

    private static List<Employee> filterEmployees(List<Employee> list, EmployeeComparator<Employee> ec){
        List<Employee> res = new ArrayList<>();
        for (Employee employee : list) {
            if(ec.comparaEmp(employee)){
                res.add(employee);
            }
        }
        return res;
    }
}

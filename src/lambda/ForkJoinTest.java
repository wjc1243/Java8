package lambda;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    /**
     * Java 8 并行流
     */
    @Test
    public void test3(){
        Instant now = Instant.now();
        OptionalLong reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel()
                .reduce(Long::sum);
        System.out.println(reduce.getAsLong());
        Instant now2 = Instant.now();
        System.out.println(Duration.between(now, now2).toMillis());//186
    }

    /**
     * Java 7 ForkJoin
     */
    @Test
    public void test1(){
        Instant now1 = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant now2 = Instant.now();
        System.out.println(Duration.between(now1, now2).toMillis());//196
    }

    /**
     * 普通for
     */
    @Test
    public void test2(){
        Long sum2 = 0L;
        Instant now3 = Instant.now();
        for(int i = 0; i <= 100000000L; i++){
            sum2 += i;
        }
        System.out.println(sum2);
        Instant now4 = Instant.now();
        System.out.println(Duration.between(now3, now4).toMillis());//565
    }
}

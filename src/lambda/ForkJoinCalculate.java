package lambda;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long left;
    private long right;

    public ForkJoinCalculate(long left, long right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected Long compute() {
        long len = right - left;
        if(len <= 10000){
            long sum = 0;
            for(long i = left; i <= right; i++){
                sum += i;
            }
            return sum;
        }else{
            long mid = left + (right-left)/2;
            ForkJoinCalculate l = new ForkJoinCalculate(left, mid);
            l.fork();
            ForkJoinCalculate r = new ForkJoinCalculate(mid+1, right);
            r.fork();

            return l.join() + r.join();
        }
    }
}

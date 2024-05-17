package ex5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskObject implements Serializable, ITask {

    private int x;
    private List<Integer> primes;
    
    public TaskObject(){
        this.primes = new ArrayList<Integer>();
    }

    public int getX(){
        return this.x;
    }

    public List<Integer> getPrimes(){
        return this.primes;
    }

    public void setPrimes(List<Integer> primes){
        this.primes=primes;
    }

    @Override
    public void setExecNumber(int x) {
        this.x = x;
    }

    @Override
    public void exec() {
        boolean[] isPrime = new boolean[this.x + 1];
        Arrays.fill(isPrime, true);

        for (int p = 2; p * p <= this.x; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= this.x; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (int p = 2; p <= this.x; p++) {
            if (isPrime[p]) {
                this.primes.add(p);
            }
        }
    }

    @Override
    public int getResult() {
        return this.primes.get(this.primes.size()-1);
    }

}
/*
 * Copyright (c) 2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

package joiningthreads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3232L, 323232L, 123L, 1231345L, 23L, 3131L, 555L);

        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers){
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread t : threads){
            t.setDaemon(true);
            t.start();
        }

        for(Thread t : threads) {
            //t.join();
            t.join(2000); // timeout
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()){
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for "  + inputNumbers.get(i) + " is in progress");
            }
        }
    }


    private static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread (long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run (){
            this.result = factorial(inputNumber);
            this.isFinished = true;

        }

        private BigInteger factorial(long n) {
            BigInteger tempRes = BigInteger.ONE;

            for (long i = n; i > 0; i --){
                tempRes = tempRes.multiply(new BigInteger(Long.toString(i)));
            }
            return tempRes;

        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}



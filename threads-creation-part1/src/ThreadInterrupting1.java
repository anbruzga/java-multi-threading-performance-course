/*
 * Copyright (c) 2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import java.math.BigInteger;

public class ThreadInterrupting1 {

    public static void main(String[] args) {
        Thread t = new Thread(new BlockingTask());
        t.start();
        t.interrupt();


        BigInteger base = new BigInteger("20000");
        BigInteger pow = new BigInteger("10000000");
        Thread t1 = new Thread(new LongComputationTask(base, pow));
        t1.start();
        t1.interrupt();


    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;


        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+"^"+power+" = " +pow(base, power));
        }

        private BigInteger pow (BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)){
                if (Thread.currentThread().isInterrupted()){
                 //   return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}

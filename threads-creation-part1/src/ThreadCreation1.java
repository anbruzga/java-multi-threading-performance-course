/*
 * Copyright (c) 2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

public class ThreadCreation1 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are in thread: " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());

                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setPriority(Thread.MAX_PRIORITY );
        thread.setName("Misbehaving Thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Critical error in thread " + t.getName() + " error: " + e.getMessage());
            }


        });
        thread.start();

        Thread.sleep(1000);

//        Thread thread1 = new Thread(() -> {
//        });
//        thread1.start();

    }
}

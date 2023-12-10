/*
 * Copyright (c) 2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

public class ThreadCreation2 {

    public static void main(String[] args) {

        Thread t = new NewThread();
        t.start();
        System.out.println(t.getName());

    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("New Thread");
            this.setName("NewThread");
        }
    }
}

package org.example.concurrency;

public class ThreadRunnable implements Runnable {
    public void run() {
        System.out.println("Hello from a thread!");
    }
    public static void main(String args[]) {
        (new Thread(new ThreadRunnable())).start();
    }
}


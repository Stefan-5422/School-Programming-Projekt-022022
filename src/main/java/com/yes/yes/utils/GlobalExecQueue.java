package com.yes.yes.utils;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class GlobalExecQueue {
    private final static ArrayList<Runnable> queue = new ArrayList<>();

    private GlobalExecQueue() {
    }

    public static void schedule(Runnable runnable) {
        queue.add(runnable);
    }



    public static void run() {
        //Semaphore s = new Semaphore(0);
        Platform.runLater( () -> {
            for (Runnable runnable : queue) {
                synchronized (GlobalExecQueue.class) {
                    runnable.run();
                }
            }
          //  s.release();
        });
        /*try {
            //s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        queue.clear();
    }
}

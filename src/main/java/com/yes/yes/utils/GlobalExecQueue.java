package com.yes.yes.utils;

import javafx.application.Platform;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class GlobalExecQueue {
    private final static Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    private GlobalExecQueue() {
    }

    public static void schedule(Runnable runnable) {
        queue.add(runnable);
    }


    public static void run() {
        Semaphore s = new Semaphore(0);
        Platform.runLater(() -> {
            while (queue.size() > 0) {
                synchronized (GlobalExecQueue.class) {
                    Objects.requireNonNull(queue.poll()).run();
                }
            }
            s.release();
        });
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.clear();
    }
}

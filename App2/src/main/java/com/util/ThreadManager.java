package com.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static final int NUMBER_OF_THREADS = 10; // Số lượng luồng tối đa
    private static ExecutorService executorService;

    // Khởi tạo ExecutorService
    public static void initialize() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            System.out.println("Thread manager initialized with " + NUMBER_OF_THREADS + " threads.");
        }
    }

    // Phương thức để thực hiện một tác vụ trong luồng
    public static void execute(Runnable task) {
        if (executorService != null) {
            executorService.execute(task);
        } else {
            System.out.println("Thread manager is not initialized. Please initialize it first.");
        }
    }

    // Đóng ExecutorService
    public static void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            System.out.println("Thread manager shutdown.");
        }
    }
}

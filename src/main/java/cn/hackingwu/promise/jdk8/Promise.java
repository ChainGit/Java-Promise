package cn.hackingwu.promise.jdk8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

/**
 * @Author: Jason Wu
 * @Date: 2020/5/8
 * @Description:
 */
public class Promise {

    private static CompletableFuture[] supplyAsync(Supplier... suppliers){
        CompletableFuture[] futures = new CompletableFuture[suppliers.length];
        for (int i = 0; i < suppliers.length; i++) {
            futures[i] = CompletableFuture.supplyAsync(suppliers[i], getExecutor());
        }
        return futures;
    }

    private static CompletableFuture[] runAsync(Runnable... runnables){
        CompletableFuture[] futures = new CompletableFuture[runnables.length];
        for (int i = 0; i < runnables.length; i++) {
            futures[i] = CompletableFuture.runAsync(runnables[i], getExecutor());
        }
        return futures;
    }

    public static CompletableFuture all(Runnable... runnables){
        return CompletableFuture.allOf(runAsync(runnables));
    }

    public static CompletableFuture any(Runnable... runnables){
        return CompletableFuture.anyOf(runAsync(runnables));
    }


    public static CompletableFuture all(Supplier... suppliers){
        return CompletableFuture.allOf(supplyAsync(suppliers));
    }

    public static CompletableFuture any(Supplier... suppliers){
        return CompletableFuture.anyOf(supplyAsync(suppliers));
    }


    static Executor getExecutor(){
        return ForkJoinPool.commonPool();
    }
}

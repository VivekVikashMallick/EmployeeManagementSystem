package com.company.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class SingletonLifecycleDemo {
    public SingletonLifecycleDemo() {
        System.out.println("SingletonLifecycleDemo constructor called.");
    }

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: Singleton bean initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("@PreDestroy: Singleton bean is being destroyed.");
    }
}

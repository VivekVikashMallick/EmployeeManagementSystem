package com.company.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;
@Component
public class EmployeeIdGenerator {
    private final AtomicLong sequence = new AtomicLong(1000);

    public long nextId() {
        return sequence.incrementAndGet();
    }
}

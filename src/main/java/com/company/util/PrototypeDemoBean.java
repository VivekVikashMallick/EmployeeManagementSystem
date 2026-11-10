package com.company.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeDemoBean {
    public PrototypeDemoBean() {
        System.out.println("A new PrototypeDemoBean instance was created: " + this);
    }
}

package com.training.empmanager.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuditLogger {
    public void log() {
        System.out.println(System.currentTimeMillis() + " : " + this.hashCode());
    }
}

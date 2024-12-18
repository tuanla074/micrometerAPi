package com.example.demosleuth;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoSleuthController {

    private final MeterRegistry meterRegistry;

    public DemoSleuthController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/hello")
    public String hello() {
        Timer timer = meterRegistry.timer("example.timer");
        return timer.record(() -> "Hello, World!");
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Simulated exception for tracing!");
    }
}


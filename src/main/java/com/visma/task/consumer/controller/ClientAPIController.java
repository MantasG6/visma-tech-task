package com.visma.task.consumer.controller;

import com.visma.task.consumer.model.Item;
import com.visma.task.consumer.service.ReactiveProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ClientAPIController {

    private final ReactiveProcessingService processingService;

    @Autowired
    public ClientAPIController(ReactiveProcessingService processingService) {
        this.processingService = processingService;
    }

    @PostMapping("/api/v1/items")
    public Mono<Item> createItem() {
        return processingService.createItem();
    }
}

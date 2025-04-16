package com.visma.task.consumer.service;

import com.visma.task.consumer.model.Item;
import com.visma.task.consumer.model.Status;
import com.visma.task.consumer.model.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveProcessingService {

    private static final String INIT_URI = "/init";
    private static final String STATUS_URI = "/checkStatus/{uuid}";

    private final WebClient webClient;

    @Autowired
    public ReactiveProcessingService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Creates an item and returns it only when the status of creation is OK
     * @return Container of an Item for a future value
     */
    public Mono<Item> createItem() {
        return webClient
                .post()
                .uri(INIT_URI)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::pollUntilOK);
    }

    /**
     * Checks the status every 1 second and creates and Item to return when the status is OK
     * @param uuid UUID returned from the thirdpartyservice
     * @return Container of an Item for a future value
     */
    private Mono<Item> pollUntilOK(String uuid) {
        return Flux.interval(Duration.ofSeconds(1))
                .concatMap(tick -> checkStatus(uuid))
                .filter(status -> StatusType.OK == status.getStatusType())
                .next()
                .map(status -> new Item(status.getUuid(), status.getStatusType()));
    }

    /**
     * Queries thirdpartyservice for status
     * @param uuid UUID of the item
     * @return Status for the item with provided uuid
     */
    private Mono<Status> checkStatus(String uuid) {
        return webClient
                .get()
                .uri(STATUS_URI, uuid)
                .retrieve()
                .bodyToMono(Status.class);
    }
}

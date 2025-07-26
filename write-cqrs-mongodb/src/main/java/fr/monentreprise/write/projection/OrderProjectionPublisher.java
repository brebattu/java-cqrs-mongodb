package fr.monentreprise.write.projection;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderProjectionPublisher {

    private final MongoClient mongoClient;

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        MongoDatabase db = mongoClient.getDatabase("cqrs-write");
        MongoCollection<Document> ordersColl = db.getCollection("orders");

        MongoDatabase viewDb = mongoClient.getDatabase("cqrs-read");
        MongoCollection<Document> viewsColl = viewDb.getCollection("order_views");

        Flux.from(ordersColl.watch())
                .flatMap(event -> {
                    if ("insert".equals(event.getOperationType().getValue())) {
                        Document doc = event.getFullDocument();
                        String customerId = doc.getString("customerId");

                        Document orderSummary = new Document()
                                .append("orderId", doc.getObjectId("_id").toHexString())
                                .append("status", doc.getString("status"))
                                .append("total", doc.get("total"));

                        Document update = new Document("$push", new Document("orders", orderSummary));

                        return Mono.from(viewsColl.updateOne(
                                Filters.eq("_id", customerId),
                                update,
                                new UpdateOptions().upsert(true)
                        ));
                    }
                    return Mono.empty();
                })
                .subscribe();
    }
}


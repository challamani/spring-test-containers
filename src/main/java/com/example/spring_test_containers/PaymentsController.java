package com.example.spring_test_containers;

import com.example.spring_test_containers.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class PaymentsController {

    @Autowired
    private Producer producer;

    @PostMapping("/payment/{topic}")
    public ResponseEntity<String> createEvent(@RequestBody Object message,
                                              @PathVariable("topic") String topic) {

        if (producer.sendMessage(topic, message)) {
            return ResponseEntity
                    .accepted()
                    .body("successfully created");
        }

        return ResponseEntity
                .badRequest()
                .body("failed to send payment events");
    }
}

package io.newrav1k.devops.devopswebservice.controller;

import io.newrav1k.devops.devopswebservice.entity.Message;
import io.newrav1k.devops.devopswebservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    private final TransactionTemplate transactionTemplate;

    @PostMapping
    public Message saveMessage(@RequestParam String message) {
        return this.transactionTemplate.execute(status -> {
            Message messageEntity = new Message();
            messageEntity.setMessage(message);
            this.messageRepository.save(messageEntity);
            return messageEntity;
        });
    }

    @GetMapping("/{messageId}")
    public Message getMessage(@PathVariable UUID messageId) {
        return this.messageRepository.findById(messageId).orElse(null);
    }

}

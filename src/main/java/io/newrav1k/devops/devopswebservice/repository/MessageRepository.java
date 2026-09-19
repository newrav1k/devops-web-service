package io.newrav1k.devops.devopswebservice.repository;

import io.newrav1k.devops.devopswebservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

}

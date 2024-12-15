package com.app.exchange.repository;

import com.app.exchange.entity.UserEntity;
import com.app.exchange.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE " +
            "(m.fromUser = :user_1 AND m.toUser = :user_2) OR " +
            "(m.fromUser = :user_2 AND m.toUser = :user_1) " +
            "ORDER BY m.date")
    List<MessageEntity> getMessages(@Param("user_1") UserEntity user_1,
                                    @Param("user_2") UserEntity user_2);
}

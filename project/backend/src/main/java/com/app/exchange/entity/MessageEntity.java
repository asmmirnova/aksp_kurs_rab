package com.app.exchange.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(name = "message_seq", sequenceName = "message_sequence", allocationSize = 1)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_message_from_user"))
    private UserEntity fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_message_to_user"))
    private UserEntity toUser;
    public MessageEntity() {}
    public MessageEntity(String text, LocalDateTime date, UserEntity fromUser, UserEntity toUser) {
        this.text = text;
        this.date = date;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserEntity getFrom() {
        return fromUser;
    }

    public void setFrom(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getTo() {
        return toUser;
    }

    public void setTo(UserEntity toUser) {
        this.toUser = toUser;
    }
}

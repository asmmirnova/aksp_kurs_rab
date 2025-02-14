package com.app.exchange.controller;

import com.app.exchange.entity.MessageEntity;
import com.app.exchange.entity.UserEntity;
import com.app.exchange.repository.MessageRepository;
import com.app.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    UserEntity user = null;
    UserEntity other = null;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Boolean login(@RequestBody UserEntity userEntity) {
        String mail = userEntity.getMail();
        String password = userEntity.getPassword();
        user = userRepository.checkPassword(mail, password);
        return user != null;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signup(@RequestBody UserEntity userEntity) {
        String name = userEntity.getName();
        String mail = userEntity.getMail();
        String password = userEntity.getPassword();
        Boolean isTeacher = userEntity.getTeacher();
        user = new UserEntity(name, mail, password, isTeacher);
        userRepository.save(user);
    }

    @GetMapping("/check_login")
    @ResponseStatus(HttpStatus.OK)
    public Boolean checkLogin() {
        return user != null;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<String> teachers() {
        if (user.getTeacher() == Boolean.FALSE) return userRepository.getTeachers();
        else return userRepository.getStudents();
    }

    @PostMapping("/select_chat")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageEntity> selectChat(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        other = userRepository.getUser(name);
        return messageRepository.getMessages(user, other);
    }

    @PostMapping("/save_message")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody MessageEntity message) {
        String text = message.getText();
        LocalDateTime date = LocalDateTime.now();
        message = new MessageEntity(text, date, user, other);
        messageRepository.save(message);
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.OK)
    public void exit() {
        user = null;
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}

package com.app.exchange.repository;

import com.app.exchange.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE " +
            "(u.mail = :mail AND u.password = :password)")
    UserEntity checkPassword(@Param("mail") String mail,
                             @Param("password") String password);
    @Query("SELECT u.name FROM UserEntity u WHERE u.isTeacher = true")
    List<String> getTeachers();
    @Query("SELECT u.name FROM UserEntity u WHERE u.isTeacher = false")
    List<String> getStudents();
    @Query("SELECT u FROM UserEntity u WHERE u.name = :name")
    UserEntity getUser(@Param("name") String name);
}

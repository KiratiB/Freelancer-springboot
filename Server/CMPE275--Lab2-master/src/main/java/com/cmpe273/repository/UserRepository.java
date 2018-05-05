package com.cmpe273.repository;
import com.cmpe273.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByUserId(int userId);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.firstname = :firstname, u.lastname=:lastname, u.email=:email, u.prof_headline=:prof_headline, u.userskills =:userskills  where u.user_id =:user_id ")
    int updateProfile(@Param("user_id") int user_id, @Param("firstname") String firstname,@Param("lastname") String lastname,@Param("email") String email, @Param("prof_headline") String prof_headline, @Param("userskills") String userskills);

}

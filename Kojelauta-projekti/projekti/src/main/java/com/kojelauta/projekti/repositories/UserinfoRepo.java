package com.kojelauta.projekti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kojelauta.projekti.entities.Userinfo;

public interface UserinfoRepo extends JpaRepository<Userinfo, Integer> {
    @Query("select u from Userinfo u where user_id like :searched")
    List<Userinfo> searchUser(String searched);

    @Query("select u from Userinfo u where email like :email")
    List<Userinfo> findByEmail(String email);

    @Query("select u from Userinfo u where user_id like :userId and box_id like :boxId")
    Userinfo findByUserIdAndBoxId(String userId, int boxId);
}

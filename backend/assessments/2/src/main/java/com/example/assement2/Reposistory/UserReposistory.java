package com.example.assement2.Reposistory;

import com.example.assement2.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserReposistory extends JpaRepository<User,Integer> {
  //finding all users
  List<User> findAll();

}

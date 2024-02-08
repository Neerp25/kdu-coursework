package com.example.assement2.Reposistory;

import com.example.assement2.entity.Eventcatalog;
import com.example.assement2.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventcatalogReposistory extends JpaRepository<Eventcatalog,Integer> {

    //find all event
    @Override
    List<Eventcatalog> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Eventcatlog WHERE eventid = : eventid", nativeQuery = true)
    int deleteeventById(@Param("eventid") Integer eventid);
}

package com.example.assement2.service;

import com.example.assement2.Reposistory.EventcatalogReposistory;
import com.example.assement2.entity.Eventcatalog;
import com.example.assement2.exception.Usernotfoundexp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class Eventcatalogservice {
    private EventcatalogReposistory eventcatalogReposistory;

    @Autowired
    public Eventcatalogservice(EventcatalogReposistory eventcatalogReposistory){
        this.eventcatalogReposistory=eventcatalogReposistory;
    }
    public void addevent(Eventcatalog Event){
        eventcatalogReposistory.save(Event);
    }
    public List<Eventcatalog> findallevent(){
        try{
            return eventcatalogReposistory.findAll();
        }catch (Exception e) {
            throw new Usernotfoundexp("User Not found");
        }
    }
    public void deleteevent(Integer id) {
        eventcatalogReposistory.deleteById(id);

    }

}

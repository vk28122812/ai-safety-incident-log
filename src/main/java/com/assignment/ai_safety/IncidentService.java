package com.assignment.ai_safety;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository repo;

    public List<Incident> getAllIncidents(){
        return repo.findAll();
    }

    public Incident getIncident(int id){
        Optional<Incident> incident = repo.findById(id);
        if(!incident.isPresent()){
            throw new IncidentNotFoundException("Incident with id: " + id +" not found");
        }
        return repo.findById(id).get();
    }

    public Incident addIncident(Incident incident){
        incident.setId(0);
        return repo.save(incident);
    }

    public void deleteIncident(int id){
        Optional<Incident> optionalIncident = repo.findById(id);
        if(! optionalIncident.isPresent()){
            throw  new IncidentNotFoundException("Incident with id: " + id +" not found");
        }
        Incident incident = optionalIncident.get();
        repo.delete(incident);
    }

}

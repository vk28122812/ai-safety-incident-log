package com.assignment.ai_safety;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService service;

    @GetMapping()
    public List<Incident> getAllIncidents(){
        return service.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getIncident(@PathVariable int id){
        return service.getIncident(id);
    }

    @PostMapping
    public Incident addIncident(@Valid @RequestBody Incident incident){
        return service.addIncident(incident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIncident(@PathVariable int id) {
        service.deleteIncident(id);
        return ResponseEntity.ok().body(Map.of(
                "message", "Incident with id: " + id + " deleted successfully",
                "status", "success"
        ));
    }
}

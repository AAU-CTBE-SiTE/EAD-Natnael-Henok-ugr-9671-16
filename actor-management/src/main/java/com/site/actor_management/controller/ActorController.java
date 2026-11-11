package com.site.actor_management.controller;

import com.site.actor_management.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    // The "database": an in-memory list. Data is lost when the app stops.
    List<Actor> actors = new ArrayList<>();

    public ActorController() {
        actors.add(new Actor(1, "John Doe", "The Great Adventure"));
        actors.add(new Actor(2, "Jane Smith", "Mystery Island"));
    }

    // CRUD -- READ (all actors)
    @GetMapping("/actor")
    public List<Actor> getActorDetails() {
        return actors;
    }

    // CRUD -- READ (one actor)
    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") int id) {
        Optional<Actor> found = findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found.get());
    }

    // CRUD -- CREATE
    @PostMapping("/actor")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        actor.setId(nextId());   // the server picks the id
        actors.add(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(actor);
    }

    // CRUD -- UPDATE (PUT replaces the whole actor)
    @PutMapping("/actor/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") int id, @RequestBody Actor newData) {
        Optional<Actor> found = findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Actor actor = found.get();
        actor.setName(newData.getName());
        actor.setMovieName(newData.getMovieName());
        return ResponseEntity.ok(actor);
    }

    // CRUD -- UPDATE (PATCH changes only the fields that were sent)
    @PatchMapping("/actor/{id}")
    public ResponseEntity<Actor> patchActor(@PathVariable("id") int id, @RequestBody Actor changes) {
        Optional<Actor> found = findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Actor actor = found.get();
        if (changes.getName() != null) {
            actor.setName(changes.getName());
        }
        if (changes.getMovieName() != null) {
            actor.setMovieName(changes.getMovieName());
        }
        return ResponseEntity.ok(actor);
    }

    // CRUD -- DELETE
    @DeleteMapping("/actor/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") int id) {
        boolean removed = actors.removeIf(actor -> actor.getId() == id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // ---- helpers ----

    private Optional<Actor> findById(int id) {
        return actors.stream()
                .filter(actor -> actor.getId() == id)
                .findFirst();
    }

    private int nextId() {
        return actors.stream()
                .mapToInt(Actor::getId)
                .max()
                .orElse(0) + 1;
    }
}

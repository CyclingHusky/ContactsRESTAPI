package de.thgeyer.workshop_restapi.controller;


import de.thgeyer.workshop_restapi.model.Contact;
import de.thgeyer.workshop_restapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return repository.save(contact);
    }

    @GetMapping
    public List<Contact> getAll() {
        return (List<Contact>) repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable String id, @RequestBody Contact updated) {
        return repository.findById(id)
                .map(contact -> {
                    contact.setName(updated.getName());
                    contact.setEmail(updated.getEmail());
                    contact.setPhone(updated.getPhone());
                    return ResponseEntity.ok(repository.save(contact));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.crm.crm.model;

import java.net.URISyntaxException;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactsController {
  private ContactRepository repository;

  public ContactsController(ContactRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/contacts")
  public Collection<Contact> getContacts() {
    return (Collection<Contact>) repository.findAll();
  }

  @PostMapping("/contacts")
  public ResponseEntity<Contact> addContact(
    @Valid @RequestBody Contact contact
  )
    throws URISyntaxException {
    Contact result = repository.save(contact);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/contacts/{id}")
  public String deleteContact(@PathVariable Long id) {
    repository.deleteById(id);
    return "Deleted";
  }

  @PutMapping("/contacts/{id}")
  public ResponseEntity<Contact> updateContact(
    @PathVariable Long id,
    @Valid @RequestBody Contact contact
  )
    throws URISyntaxException {
    Contact result = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException());
    result.update(contact);
    repository.save(result);
    return ResponseEntity.ok().body(result);
  }
}

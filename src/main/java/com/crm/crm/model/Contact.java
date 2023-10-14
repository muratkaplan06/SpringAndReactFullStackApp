package com.crm.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Contact {
  @Id
  @GeneratedValue
  public Long id;

  public String firstname;
  public String lastname;
  public String email;

  public Contact() {}

  public Contact(String firstname, String lastname, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
  }

  public void update(Contact contact) {
    this.firstname = contact.firstname;
    this.lastname = contact.lastname;
    this.email = contact.email;
  }
}

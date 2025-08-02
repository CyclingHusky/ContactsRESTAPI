package de.thgeyer.workshop_restapi.repository;

import de.thgeyer.workshop_restapi.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, String>
{
}

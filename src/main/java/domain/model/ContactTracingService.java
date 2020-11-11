package domain.model;

import domain.db.PersonService;

import java.util.List;

public class ContactTracingService {
    private PersonService personDb = new PersonService();

    public ContactTracingService() {
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    private PersonService getPersonDb() {
        return personDb;
    }
}

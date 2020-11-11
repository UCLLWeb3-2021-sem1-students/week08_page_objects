package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
	private List<Person> persons = new ArrayList<>();
	
	public PersonService() {
		Person administrator = new Person("Ad", "Ministrator", "admin@ucll.be", "t");
		add(administrator);
	}
	
	public List<Person> getAll(){
		return persons;
	}

	public void add(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		persons.add(person);
	}
	
	public int getNumberOfPersons() {
		return persons.size();
	}
}

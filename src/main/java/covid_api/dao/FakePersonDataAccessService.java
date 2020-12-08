package covid_api.dao;

import covid_api.model.Person;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
	
	private static List<Person> DB = new ArrayList<>();
	
	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName()));
		return 1;
	}
	
	@Override
	public List<Person> getPersons(){
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		// TODO Auto-generated method stub
		return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
	}

	@Override
	public int deletePerson(UUID id) {
		// TODO Auto-generated method stub
		Optional<Person> personMaybe = selectPersonById(id);
		if(personMaybe.isEmpty()) return 0;
		DB.remove(personMaybe.get());
		return 1;
	}

	@Override
	public int updatePerson(UUID id, Person personToUpdate) {
		// TODO Auto-generated method stub
		return selectPersonById(id)
				.map(person->{
					int indexOfPersonToUpdate=DB.indexOf(person);
					if(indexOfPersonToUpdate>=0) {
						DB.set(indexOfPersonToUpdate, new Person(id, personToUpdate.getName()));
						return 1;
						}
					else return 0;
					}).orElse(0);
	}
	
}

package covid_api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import covid_api.dao.PersonDao;
import covid_api.model.Person;

@Service
public class PersonService {

	private final PersonDao personDao;
	
	@Autowired
	public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
		this.personDao=personDao;
	}
	
	public int addPerson(Person person) {		
		return personDao.insertPerson(person);
	}
	
	public List<Person> getAllPersons(){
		return personDao.getPersons();
	}
	
	public Optional<Person> getPersonById(UUID id){
		return personDao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id) {
		return personDao.deletePerson(id);
	}
	
	public int updatePerson(UUID id, Person person) {
		return personDao.updatePerson(id, person);
	}
}

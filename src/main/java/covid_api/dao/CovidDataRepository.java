package covid_api.dao;

import org.springframework.data.repository.CrudRepository;

import covid_api.model.CovidState;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CovidDataRepository extends CrudRepository<CovidState, Integer> {

}

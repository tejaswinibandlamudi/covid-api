package covid_api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import covid_api.dao.CovidDataRepository;
import covid_api.model.CovidState;

@RestController
@RequestMapping("/api/v2/state")
public class CovidController {
	@Autowired
	private CovidDataRepository covidDataRepository;
	
	@PostMapping(path="add")
	public @ResponseBody String addState (
			@RequestParam String state,
			@RequestParam int tested,
			@RequestParam int confirmed,
			@RequestParam int cured,
			@RequestParam int expired) {
		CovidState covidState = new CovidState();
		covidState.setState(state);
		covidState.setTested(tested);
		covidState.setConfirmed(confirmed);
		covidState.setCured(cured);
		covidState.setExpired(expired);
		covidDataRepository.save(covidState);
	    return "Saved";	
	}
	@GetMapping("/all")
	public @ResponseBody Iterable<CovidState> getAllStates(){
		return covidDataRepository.findAll();
	}
}

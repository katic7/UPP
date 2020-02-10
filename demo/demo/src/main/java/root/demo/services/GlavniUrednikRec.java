package root.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import root.demo.model.Rad;
import root.demo.repository.RadRepository;

public class GlavniUrednikRec implements JavaDelegate {

	@Autowired
	RadRepository radRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<String> recezenti = new ArrayList<String>();
		Rad r = radRepo.getOne((long)execution.getVariable("radId"));
		recezenti.add(r.getCasopis().getGlavniUrednik().getusername());
		execution.setVariable("recezentiRada", recezenti);
		execution.setVariable("recezFail", false);
		List<String> lista = new ArrayList<String>();
		execution.setVariable("komUredniku", lista);
		execution.setVariable("komAutoru", lista);
		execution.setVariable("zakljucci", lista);
		execution.setVariable("rokD", 1);
		execution.setVariable("rokH", 3);
		execution.setVariable("rokM", 10);
	}

}

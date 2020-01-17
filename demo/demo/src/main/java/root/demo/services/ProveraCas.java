package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Casopis;
import root.demo.repository.CasopisRepositoy;

@Service
public class ProveraCas implements JavaDelegate{

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	CasopisRepositoy casRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String issn = (String) execution.getVariable("issn");
		Casopis cas = casRepo.findOneByIssn(issn);
		 String processInstanceId = execution.getProcessInstanceId();
	      List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("sacuvajCas");
	      System.out.println(registration);
	      boolean bol = false;
	      for (FormSubmissionDto formField : registration) {
			if(formField.getFieldId().equals("pod_val")) {
				if(formField.getFieldValue().equals("true")) {
					bol=true;
				}
			}
		}
	      cas.setAktivan(bol);
	      casRepo.save(cas);
	      execution.setVariable("objavi", "true");
	}

}

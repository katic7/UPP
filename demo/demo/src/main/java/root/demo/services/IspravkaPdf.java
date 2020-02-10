package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class IspravkaPdf implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> pdf = (List<FormSubmissionDto>)execution.getVariable("noviPdf");
	      
	      for (FormSubmissionDto formField : pdf) {
	    	  if(formField.getFieldId().equals("ispravka_pdfa")) {
	    		  execution.setVariable("pdf", formField.getFieldValue());
	    	  }
	      }
	}

}

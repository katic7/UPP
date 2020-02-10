package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class OdabirCasopisa implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String processInstanceId = execution.getProcessInstanceId();
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("odabirCas");
	      System.out.println("USAOO ODABIRCAS");
	      execution.setVariable("autor_placa", false);
	      
	      for (FormSubmissionDto formField : registration) {
	    	  if(formField.getFieldId().equals("casopis")) {
	    		  for(String str:formField.getEnumi()) {
	    			  execution.setVariable("casopis_id", str);
	    			  System.out.println(str + "AAAAAAAAAAAAAAAAAA");
	    		  }
	    	  }
	      }
	}

}

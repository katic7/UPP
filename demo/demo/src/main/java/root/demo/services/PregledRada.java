package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class PregledRada implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PREGLED RADA");
		List<FormSubmissionDto> rad = (List<FormSubmissionDto>)execution.getVariable("pregledRada");
	
		for (FormSubmissionDto formField : rad) {
			if(formField.getFieldId().equals("odobren")) {
				if(formField.getFieldValue().equals("true")) {
					execution.setVariable("odobren", true);
				}else {
					execution.setVariable("odobren", false);
				}
			}
		}
	}
	

}

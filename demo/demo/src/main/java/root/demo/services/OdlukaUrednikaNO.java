package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class OdlukaUrednikaNO implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("USAO U ODLUKA UREDNIKA NO!");
		List<FormSubmissionDto> forms = (List<FormSubmissionDto>)execution.getVariable("urednikPregleda");
		for(FormSubmissionDto formField : forms) {
			if(formField.getFieldId().equals("prikladan_ured")) {
				for(String str: formField.getEnumi()) {
					execution.setVariable("prikladan_ured", str);
				}
			}else if(formField.getFieldId().equals("ispravakD")) {
				execution.setVariable("ispravakD", Long.parseLong(formField.getFieldValue()));
			}else if(formField.getFieldId().equals("ispravakH")) {
				execution.setVariable("ispravakH", Long.parseLong(formField.getFieldValue()));
			}else if(formField.getFieldId().equals("ispravakM")) {
				execution.setVariable("ispravakM", Long.parseLong(formField.getFieldValue()));
			}
		}
	}

}

package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class CuvanjeRecenzije implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("USAO U CUVANJE RECENZIJE");
		List<FormSubmissionDto> recez = (List<FormSubmissionDto>)execution.getVariable("pregledajRad");
		List<String> komUredniku = (List<String>)execution.getVariable("komUredniku");
		List<String> komAutoru = (List<String>)execution.getVariable("komAutoru");
		List<String> zakljucci = (List<String>)execution.getVariable("zakljucci");
		System.out.println("UZEO LISTE");
		for(FormSubmissionDto formField : recez) {
			if(formField.getFieldId().equals("komentar_rada")) {
				komAutoru.add(formField.getFieldValue());
				execution.setVariable("komAutoru", komAutoru);
			}else if(formField.getFieldId().equals("komentar_uredniku")) {
				komUredniku.add(formField.getFieldValue());
				execution.setVariable("komUredniku", komUredniku);
			}else if(formField.getFieldId().equals("zakljucak")) {
				zakljucci.add(formField.getFieldValue());
				execution.setVariable("zakljucci", zakljucci);
			} 
		}
		System.out.println("KRAJ CUVANJA REC");
	}

}


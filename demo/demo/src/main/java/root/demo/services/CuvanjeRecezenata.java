package root.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class CuvanjeRecezenata implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<String> recezenti = new ArrayList<String>();
		List<FormSubmissionDto> recez = (List<FormSubmissionDto>)execution.getVariable("cuvanjeRecez");
		for(FormSubmissionDto formField : recez) {
			if(formField.getFieldId().equals("recezenti")) {
				for(String s:formField.getEnumi()) {
					recezenti.add(s);
				}
			}
		}
		List<String> lista = new ArrayList<String>();
		execution.setVariable("recezentiRada", recezenti);
		execution.setVariable("recezFail", false);
		execution.setVariable("komUredniku", lista);
		execution.setVariable("komAutoru", lista);
		execution.setVariable("zakljucci", lista);
	}

}

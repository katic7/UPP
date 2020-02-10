package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;

@Service
public class CuvanjeProverePdf implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CUVANJEPROVEREPDF");
		List<FormSubmissionDto> rad = (List<FormSubmissionDto>)execution.getVariable("pregledPdf");
		System.out.println(rad.size() + "SIZEEEEEEEE");
		boolean dobarPDF = false;
		String kom = "";
		for (FormSubmissionDto formField : rad) {
			if(formField.getFieldId().equals("dobro_formatiran")) {
				if(formField.getFieldValue().equals("true")) {
					dobarPDF = true;
				}else {
					dobarPDF = false;
				}
			}else if(formField.getFieldId().equals("komentar")) {
				if(!formField.getFieldValue().equals("")) {
					kom = formField.getFieldValue();
				}
			}
		}
		if(dobarPDF == true) {
			//execution.setVariable("dobro_formatiran", true);
			System.out.println("DOBAR PDF");
		}else {
			//execution.setVariable("dobro_formatiran", false);
			execution.setVariable("pdfKomentar", kom);
		}
		
	}

}

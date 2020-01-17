package root.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Korisnik;
import root.demo.model.Roles;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.RoleRepository;

@Service
public class VerifikacijaRec implements JavaDelegate{
	
	@Autowired
	RuntimeService runtimeSerivce;

	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String processInstanceId = execution.getProcessInstanceId();
		String email = "";
		Set<Roles> roles = new HashSet<Roles>();
		boolean bol = false;
		Korisnik kor = new Korisnik();
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("odobriRec");
		for (FormSubmissionDto formField : registration) {
			if(formField.getFieldId().equals("email_kor")) {
				email = formField.getFieldValue();
			}
			if(formField.getFieldId().equals("odobri")) {
				if(formField.getFieldValue().equals("true")) {
					bol = true;
				}
			}
		}
		kor = korRepo.findOneByEmail(email);
		roles = kor.getRoles();
		if(bol) {
			roles.add(roleRepo.getRoleByName("RECEZENT"));
		}
		kor.setRoles(roles);
		System.out.println(kor.getEmail()+"MAIL!!");
		kor.setRecezent(bol);
		korRepo.save(kor);
	}
}

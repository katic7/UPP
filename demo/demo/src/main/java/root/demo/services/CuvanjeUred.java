package root.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Casopis;
import root.demo.model.Korisnik;
import root.demo.repository.CasopisRepositoy;
import root.demo.repository.KorisnikRepository;

@Service
public class CuvanjeUred implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	
	@Autowired
	CasopisRepositoy casRepo;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("recezentCas");
		String issn = (String) execution.getVariable("issn");
		Casopis cas = casRepo.findOneByIssn(issn);
		List<Korisnik> recezenti = new ArrayList<Korisnik>();
		List<Korisnik> urednici = new ArrayList<Korisnik>();
	      for (FormSubmissionDto formField : registration) {
			if(formField.getFieldId().equals("recezenti")) {
				for(String str:formField.getEnumi()) {
					Korisnik k = korRepo.getOne(Long.parseLong(str));
					recezenti.add(k);
				}
			}
			if(formField.getFieldId().equals("urednik")) {
				for(String str:formField.getEnumi()) {
					Korisnik k = korRepo.getOne(Long.parseLong(str));
					urednici.add(k);
				}
			}
			
	    }
	      cas.setRecezenti(recezenti);
	      cas.setUredniciNO(urednici);
	      casRepo.save(cas);
	}
	
}

package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Korisnik;
import root.demo.repository.KorisnikRepository;

@Service
public class AktiviranjeKor implements JavaDelegate{
	
	@Autowired
	KorisnikRepository korRepo;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("aktivacijaKor");
	      System.out.println(registration);
	      String mail = "";
	      String ver = "";
	      for (FormSubmissionDto formField : registration) {
				if(formField.getFieldId().equals("email")) {
					mail = formField.getFieldValue();
				}
				if(formField.getFieldId().equals("verifikacija")) {
					ver = formField.getFieldValue();
				}
	      }
	      System.out.println("AKTIVIRACNJE KOR " + mail);
	      Korisnik k = korRepo.findOneByEmail(mail);
	      //Korisnik k = korRepo.getOne(Long.parseLong("1"));
	      if(k.getAkt_kod().equals(ver)){
	    	  k.setAktiviran(true);
	      }else {
	    	  k.setAktiviran(false);
	      }
	      korRepo.save(k);
	}

}

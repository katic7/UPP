package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Casopis;
import root.demo.model.Korisnik;
import root.demo.model.NaucnaOblast;
import root.demo.model.Rad;
import root.demo.repository.CasopisRepositoy;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.repository.RadRepository;

@Service
public class CuvanjeRada implements JavaDelegate{

	@Autowired
	RadRepository radRepo;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
	CasopisRepositoy casRepo;
	
	@Autowired
	NaucnaOblastRepository noRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DSADASD");
		Long br = (long) 0;
		List<FormSubmissionDto> fields = (List<FormSubmissionDto>)execution.getVariable("cuvanjeRada");
		String processInstanceId = execution.getProcessInstanceId();
		Long idCas = Long.parseLong((String)execution.getVariable("casopis_id"));
		Long id = (long)execution.getVariable("autor");
		Rad r = new Rad();
		for (FormSubmissionDto formField : fields) {
			if(formField.getFieldId().equals("naslov")) {
				System.out.println(formField.getFieldValue());
				r.setNaziv(formField.getFieldValue());
			}else if(formField.getFieldId().equals("kljucni_pojmovi")) {
				System.out.println(formField.getFieldValue());
				r.setKljucneReci(formField.getFieldValue());
			}else if(formField.getFieldId().equals("apstrakt")) {
				System.out.println(formField.getFieldValue());
				r.setApstrakt(formField.getFieldValue());
			}else if(formField.getFieldId().equals("pdf")) {
				System.out.println(formField.getFieldValue());
				r.setPdf(formField.getFieldValue());
			}else if(formField.getFieldId().equals("norada")) {
				System.out.println("norada");
				for(String str:formField.getEnumi()) {
					System.out.println(str+"AAAAAAAA");
					NaucnaOblast no = noRepo.getOne(Long.parseLong(str));
					r.setNaucnaOblast(no);
				}
				
			}
		}
		Korisnik k = korRepo.getOne(id);
		r.setAutor(k);
		Casopis c = casRepo.getOne(idCas);
		r.setCasopis(c);
		r = radRepo.save(r);
		execution.setVariable("glavniUrednik", c.getGlavniUrednik().getusername());
		execution.setVariable("radId", r.getId());
	}

}

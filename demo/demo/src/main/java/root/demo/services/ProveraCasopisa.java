package root.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Casopis;
import root.demo.model.NacinPlacanja;
import root.demo.model.NaucnaOblast;
import root.demo.repository.CasopisRepositoy;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.NacinPlacanjaRepository;
import root.demo.repository.NaucnaOblastRepository;

@Service
public class ProveraCasopisa implements JavaDelegate{

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	CasopisRepositoy casRepo;
	
	@Autowired
	NaucnaOblastRepository naucnaRepo;
	
	@Autowired
	NacinPlacanjaRepository placanjeRepo;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String processInstanceId = execution.getProcessInstanceId();
		Casopis cas = new Casopis();
		List<NaucnaOblast> nc = new ArrayList<NaucnaOblast>();
		List<NacinPlacanja> nacini= new ArrayList<NacinPlacanja>();
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("casopis");
		
		for (FormSubmissionDto formField : registration) {
			System.out.println("IDEJE " +formField.getFieldId());
			if(formField.getFieldId().equals("naziv")) {
				cas.setNaziv(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("issn")) {
				cas.setIssn(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("naucne_oblasti")) {
				for(String str:formField.getEnumi()) {
					System.out.println(str+"AAAA");
					NaucnaOblast naucna = naucnaRepo.getOne(Long.parseLong(str));
					System.out.println(naucna.getNaziv()+"SSS");
					nc.add(naucna);
				}
			}
			if(formField.getFieldId().equals("naplata")) {
				for(String str:formField.getEnumi()) {
					System.out.println(str+"BBBB");
					NacinPlacanja np = placanjeRepo.getOne(Long.parseLong(str));
					nacini.add(np);
				}
			}
		}
		
		cas.setAktivan(false);
		cas.setNaciniPlacanja(nacini);
		cas.setNaucneOblasti(nc);
		//String email = execution.get
		//cas.setGlavniUrednik(korRepo.getByEmail());
		casRepo.save(cas);
		
	}

}

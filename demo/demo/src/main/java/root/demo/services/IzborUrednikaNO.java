package root.demo.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import root.demo.model.Casopis;
import root.demo.model.Korisnik;
import root.demo.model.NaucnaOblast;
import root.demo.model.Rad;
import root.demo.repository.CasopisRepositoy;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.RadRepository;

@Service
public class IzborUrednikaNO implements JavaDelegate {
	
	@Autowired
	RadRepository radRepo;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	CasopisRepositoy casRepo;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IZBOR UREDNIKA NAUCNE OBLASTI");
		Rad r = radRepo.getOne((long)execution.getVariable("radId"));
		Casopis cas = r.getCasopis();
		List<Korisnik> recezenti = new ArrayList<Korisnik>();
		boolean flag = false;
		
		for(Korisnik kor:cas.getUredniciNO()) {
			for(NaucnaOblast nc : kor.getNoblasti()) {
				if(nc.getNaziv().equals(r.getNaucnaOblast().getNaziv())) {
					r.setUrednikNO(kor);
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		if(flag) {
			sendEmailAutoru(r.getUrednikNO(), r.getNaziv());
			execution.setVariable("urednikNO", r.getUrednikNO().getusername());
		}else {
			System.out.println("NIJE GA NASAO!!!!!!!!!");
			execution.setVariable("urednikNO", r.getCasopis().getGlavniUrednik().getusername());
			
		}
		radRepo.save(r);
		execution.setVariable("urednikNO", r.getUrednikNO().getusername());
		
		for(Korisnik kor : cas.getRecezenti()) {
			for(NaucnaOblast no:kor.getNoblasti()) {
				if(no.getNaziv().equals(r.getNaucnaOblast().getNaziv())) {
					recezenti.add(kor);
					break;
				}
			}
		}
		System.out.println(recezenti.size() + "RECEZENATAAA");
		if(recezenti.size() >= 2) {
			System.out.println("IMA RECEZ");
			execution.setVariable("ima_recezenata", true);
		}else {
			System.out.println("NEMA RECEZ");
			execution.setVariable("ima_recezenata", false);
		}
	}
	
	private void sendEmailAutoru(Korisnik k, String rad) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Postovani "+ k.getIme() +" "+k.getPrezime()+",</h3>";
        htmlMsg += "<h3> Postali ste urednik naucne oblasti za rad: \"" +rad +"\"</h3>";
        htmlMsg += "<br/><h4>Hvala na poverenju,</h4>";
        htmlMsg += "<h4>Vasa Naucna centrala :)</h4>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(k.getEmail());
        helper.setSubject("Naučna centrala - novi rad");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }

}

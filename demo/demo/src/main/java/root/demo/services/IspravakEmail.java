package root.demo.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import root.demo.model.Korisnik;
import root.demo.model.Rad;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.RadRepository;

public class IspravakEmail implements JavaDelegate {

	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
	RadRepository radRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long id = (long)execution.getVariable("autor");
		Korisnik k = korRepo.getOne(id);
		Long l = (long)execution.getVariable("radId");
		Rad r = radRepo.getOne(l);
		r.setOdobren(true);
		radRepo.save(r);
		sendEmailAutoru(k, r.getNaziv());
		
	}

	
	private void sendEmailAutoru(Korisnik k, String rad) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Postovani "+ k.getIme() +" "+k.getPrezime()+",</h3>";
        htmlMsg += "<h3> Vas zahtev za kreiranje rada: \"" +rad +"\" je poslat na doradu. Molimo Vas da ispraviti greske.</h3>";
        htmlMsg += "<br/><h4>Hvala na poverenju,</h4>";
        htmlMsg += "<h4>Vasa Naucna centrala :)</h4>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(k.getEmail());
        helper.setSubject("Naučna centrala - zahtev poslat na doradu");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }


}

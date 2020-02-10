package root.demo.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import root.demo.model.Korisnik;
import root.demo.model.Rad;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.RadRepository;

@Service
public class EmailObavestenje implements JavaDelegate{

	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	RadRepository radRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String processInstanceId = execution.getProcessInstanceId();
		System.out.println("EMAIL");
		Long id = (long)execution.getVariable("autor");
		Korisnik k = korRepo.getOne(id);
		String mail = k.getEmail();
		Long l = (long)execution.getVariable("radId");
		Rad r = radRepo.getOne(l);
		
		Korisnik glurednik = r.getCasopis().getGlavniUrednik();
		
		sendEmailAutoru(mail, k, r.getNaziv());
		sendEmailUredniku(glurednik.getEmail(), glurednik, r.getNaziv());
	}
	
	private void sendEmailAutoru(String emailOfUser, Korisnik k, String rad) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Postovani "+ k.getIme() +" "+k.getPrezime()+",</h3>";
        htmlMsg += "<h3> Vas zahtev za kreiranje rada: \"" +rad +"\" je uspesno zabelezen i poslat na pregled</h3>";
        htmlMsg += "<br/><h4>Hvala na poverenju,</h4>";
        htmlMsg += "<h4>Vasa Naucna centrala :)</h4>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(emailOfUser);
        helper.setSubject("Naučna centrala - zahtev kreiran");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }
	
	private void sendEmailUredniku(String emailOfUser, Korisnik k, String rad) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Postovani "+ k.getIme() +" "+k.getPrezime()+",</h3>";
        htmlMsg += "<h3> Kreiran je novi zahtev za objavu rada:<u>"+rad+"</u> i ceka da ga pregledate.</h3>";
        htmlMsg += "<br/><h4>Hvala na poverenju,</h4>";
        htmlMsg += "<h4>Vasa Naucna centrala :)</h4>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(emailOfUser);
        helper.setSubject("Naučna centrala - Zahtev za pregled rada");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }
}

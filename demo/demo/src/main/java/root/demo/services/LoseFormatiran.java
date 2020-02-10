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
public class LoseFormatiran implements JavaDelegate {

	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	RadRepository radRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long id = (long)execution.getVariable("autor");
		Korisnik k = korRepo.getOne(id);
		Long l = (long)execution.getVariable("radId");
		Rad r = radRepo.getOne(l);
		Long dan = (long)execution.getVariable("rok_dani");
		Long sat = (long)execution.getVariable("rok_sati");
		Long min = (long)execution.getVariable("rok_min");
		sendEmailAutoru(k, r.getNaziv(), dan, sat, min);
	}
	
	private void sendEmailAutoru(Korisnik k, String rad,Long dan, Long sat, Long min) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Postovani "+ k.getIme() +" "+k.getPrezime()+",</h3>";
        htmlMsg += "<h3> Vas rad: \"" +rad +"\" je pregledan i nije dobro formatiran. Imate rok da rad ispravite i posaljete od:"+dan+" dana, "+sat+" sati, "+ min+" minuta"+"</h3>";
        htmlMsg += "<h4> Ukoliko rad ne ispravite Vas zahtev ce biti odbijen</h4>";
        htmlMsg += "<br/><h4>Hvala na poverenju,</h4>";
        htmlMsg += "<h4>Vasa Naucna centrala :)</h4>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(k.getEmail());
        helper.setSubject("Naučna centrala - zahtev lose formatiran");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }

}

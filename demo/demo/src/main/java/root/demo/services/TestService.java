package root.demo.services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.naming.factory.SendMailFactory;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import root.demo.dto.FormSubmissionDto;
import root.demo.model.Korisnik;
import root.demo.model.NaucnaOblast;
import root.demo.model.Role;
import root.demo.model.RoleName;
import root.demo.model.Roles;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.repository.RoleRepository;

@Service
public class TestService implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	NaucnaOblastRepository naucnaRepo;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 String var = "Pera";
		 Set<Role> roles = new HashSet<Role>();
		 Korisnik kor = new Korisnik();
		 Set<NaucnaOblast> nc = new HashSet<NaucnaOblast>();
		 String processInstanceId = execution.getProcessInstanceId();
	      var = var.toUpperCase();
	      execution.setVariable("input", var);
	      List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registracija");
	      System.out.println(registration);
	      User user = identityService.newUser("");
	      for (FormSubmissionDto formField : registration) {
			if(formField.getFieldId().equals("kor_ime")) {
				user.setId(formField.getFieldValue());
				kor.setusername(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("naucne_oblasti")) {
				for(String str:formField.getEnumi()) {
					System.out.println(str+"AAAA");
					NaucnaOblast naucna = naucnaRepo.getOne(Long.parseLong(str));
					System.out.println(naucna.getNaziv()+"SSS");
					nc.add(naucna);
				}
			}
			if(formField.getFieldId().equals("lozinka")) {
				user.setPassword(formField.getFieldValue());
				kor.setpassword(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("ime")) {
				kor.setIme(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("prezime")) {
				kor.setPrezime(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("grad")) {
				kor.setGrad(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("drzava")) {
				kor.setDrzava(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("titula")) {
				kor.setTitula(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("email")) {
				kor.setEmail(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("recezent")){
				System.out.println("USAO"+formField.getFieldValue());
				if(formField.getFieldValue().equals("true")) {
					kor.setHoce_rec(true);
					runtimeService.setVariable(processInstanceId, "recezent", "true");
				}else {
					kor.setHoce_rec(false);
					runtimeService.setVariable(processInstanceId, "recezent", "false");
				}
			}
	      }
	      String act_code = RandomStringUtils.randomAlphanumeric(17).toUpperCase();
		  kor.setAkt_kod(act_code);
		  roles.add(roleRepo.findByName(RoleName.USER));
		  kor.setRoles(roles);
		  kor.setNoblasti(nc);
		  kor.setAktiviran(false);
	      korRepo.save(kor);
	      identityService.saveUser(user);
	      
	      sendEmail(kor.getEmail(), kor.getAkt_kod());
	      
	}
	
	private void sendEmail(String emailOfUser, String linkUrl) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "";
        htmlMsg += "<h3> Vas aktivacioni kod je: " + linkUrl + "</h3>";
        mimeMessage.setContent(htmlMsg, "text/html");


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        helper.setTo(emailOfUser);
        helper.setSubject("Science Centre Activation Account Support");
        javaMailSender.send(mimeMessage);

        System.out.println("Email poslat!");
    }
	
}

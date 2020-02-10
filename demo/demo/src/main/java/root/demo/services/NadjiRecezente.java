package root.demo.services;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.Korisnik;
import root.demo.model.Rad;
import root.demo.repository.RadRepository;

@Service
public class NadjiRecezente implements TaskListener {

	@Autowired
	RadRepository radRepo;
	
	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices().getFormService().getTaskFormData(delegateTask.getId());
		Rad r = radRepo.getOne((long)delegateTask.getVariable("radId"));
		List<Korisnik> recezenti = r.getCasopis().getRecezenti();
		List<FormField> formFields = taskFormData.getFormFields();
		
		if(formFields!=null) {
			for(FormField field:formFields) {
				if(field.getId().equals("recezenti")) {
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
			        items.clear();
			        for(Korisnik k:recezenti) {
			        	String str = k.getIme() + " " + k.getPrezime();
			        	items.put(k.getusername(), str);
			        }
				}
			}
		}

	}
}

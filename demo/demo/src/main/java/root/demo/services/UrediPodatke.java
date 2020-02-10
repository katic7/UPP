package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class UrediPodatke implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("USAO U UREDI PODATKE");
		List<String> lista = (List<String>)execution.getVariable("komUredniku");
		String newStr = "";
		for(String str : lista) {
			newStr += str + " | ";
		}
		execution.setVariable("komentar_uredniku", newStr);
		System.out.println("IZASAO IZ UREDI PODATKE");
	}

}

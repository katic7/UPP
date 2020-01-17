package root.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.cmd.GetDeploymentResourceNamesCmd;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.dto.FormFieldsDto;
import root.demo.dto.FormSubmissionDto;
import root.demo.dto.TaskDto;

@Controller
@RequestMapping("/welcome")
public class DummyController {
	@Autowired
	IdentityService identityService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	RepositoryService repositoryService;
	
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(path = "/getTask/{id}", produces = "application/json")  //da
    public @ResponseBody FormFieldsDto getTask(@PathVariable String id) {
		Task task = taskService.createTaskQuery().taskId(id).singleResult();
		TaskFormData tfData = formService.getTaskFormData(task.getId());
		
		List<FormField> properites = tfData.getFormFields();
		for(FormField fp : properites) {
			System.out.println(fp.getId() + fp.getType());
		}
		
		return new FormFieldsDto(task.getId(),task.getProcessInstanceId(),properites);
	}
	@GetMapping(path = "/get/{str}", produces = "application/json")  //da
    public @ResponseBody FormFieldsDto startTask(@PathVariable String str) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(str);

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@GetMapping(path = "/getMyTasks/{username}", produces = "application/json") //getujem taskove za npr admina
	public @ResponseBody ResponseEntity<List<TaskDto>> getMyTasks(@PathVariable String username){
		List<Task> tasks = new ArrayList<Task>();
		tasks.addAll(taskService.createTaskQuery().processDefinitionKey("registracija").taskAssignee(username).list());
		tasks.addAll(taskService.createTaskQuery().processDefinitionKey("kreiranje_casopisa").taskAssignee(username).list());
		
		List<TaskDto> taskDTO = new ArrayList<TaskDto>();
		for(Task task : tasks) {
			TaskDto ts = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			taskDTO.add(ts);
		}
		return new ResponseEntity<List<TaskDto>>(taskDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/tasks/{processInstanceId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> get(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
        return new ResponseEntity(dtos,  HttpStatus.OK);
    }
	
	@GetMapping(path = "/getUserTask/{id}", produces = "application/json")
    public @ResponseBody FormFieldsDto getUserTask(@PathVariable String id) {
		
		Task task = taskService.createTaskQuery().processInstanceId(id).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), id, properties);
    }
	
	@PostMapping(path = "/post/{taskId}/{str}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId, @PathVariable String str) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		    // list all running/unsuspended instances of the process
//		    ProcessInstance processInstance =
//		        runtimeService.createProcessInstanceQuery()
//		            .processDefinitionKey("Process_1")
//		            .active() // we only want the unsuspended process instances
//		            .list().get(0);
		
//			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		
		
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, str, dto);
		formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/tasks/claim/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity claim(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String user = (String) runtimeService.getVariable(processInstanceId, "username");
		taskService.claim(taskId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/tasks/complete/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> complete(@PathVariable String taskId) {
		Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(taskId);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(taskTemp.getProcessInstanceId()).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
        return new ResponseEntity<List<TaskDto>>(dtos, HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}
}

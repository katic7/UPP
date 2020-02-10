package root.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import root.demo.model.Rad;
import root.demo.service.RadService;

@Controller
@RequestMapping("/rad")
public class RadController {
	
	@Autowired
	RadService radService;
	
	@Autowired
	RuntimeService runtimeService;

	@GetMapping("/setAutora/{processid}/{id}")
	public ResponseEntity<?> setAutora(@PathVariable String processid, @PathVariable Long id){
		runtimeService.setVariable(processid, "autor", id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadPDF/", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> uploadFile(@RequestParam("File") MultipartFile request) {
		System.out.print("pogodio pdf");
		String returnValue ="";
		//Rad r = new Rad();
		//r = radService.storeFile(request);
		try {
			returnValue = saveImage(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(returnValue, HttpStatus.OK);

	}

	

	public String saveImage(MultipartFile file) throws IOException {
		String folder = "src/main/resources/";
		byte[] bytes = file.getBytes();
		Path path = Paths.get(folder + file.getOriginalFilename());
		System.out.println(path.toAbsolutePath());
		Files.write(path, bytes);
		return path.toAbsolutePath().toString();
	}
}

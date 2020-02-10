package root.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import root.demo.model.Rad;
import root.demo.repository.RadRepository;

@Service
public class RadService {

	@Autowired
	RadRepository radRepo;
	
	public Rad storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
		if(fileName.contains("..")) {
		    //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}

		Rad rad = new Rad();
		rad.setApstrakt("apst");
		rad.setKljucneReci("rec");
		rad.setNaziv("asdas");
		rad.setPdf("pdf");
         // rad.setData(file.getBytes());

		return radRepo.save(rad);
    }

}

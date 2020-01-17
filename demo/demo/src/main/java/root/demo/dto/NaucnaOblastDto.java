package root.demo.dto;

import java.util.ArrayList;
import java.util.List;

import root.demo.model.NaucnaOblast;

public class NaucnaOblastDto {
	
	private Long id;
	private String naziv;
	
	public NaucnaOblastDto() {}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}

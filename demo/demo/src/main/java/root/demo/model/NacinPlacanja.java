package root.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NacinPlacanja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nacin;
	
	public NacinPlacanja() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNacin_placanja() {
		return nacin;
	}

	public void setNacin_placanja(String nacin_placanja) {
		this.nacin = nacin_placanja;
	}
	
	
}

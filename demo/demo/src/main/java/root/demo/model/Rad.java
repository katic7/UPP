package root.demo.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Rad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column(nullable = false)
	private boolean odobren;
	
	@ManyToOne
	private NaucnaOblast naucnaOblast;
	
	@Column(nullable = false)
	private String kljucneReci;

	@Column(nullable = false)
	private String apstrakt;

	@Column(nullable = false)
	private String pdf;
	
	@ManyToOne
	private Korisnik autor;
	
	@ManyToOne
	private Korisnik urednikNO;
	
	@ManyToOne
	private Casopis casopis;
}

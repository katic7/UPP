package root.demo.model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

@Entity
public class Casopis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@NaturalId
	@Column(nullable = false, unique = true)
	private String issn;
	
	@Column()
	private boolean aktivan;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="naucne_oblasti_cas",
	joinColumns = @JoinColumn(name="casopis_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="naucna_oblast_id", referencedColumnName="id"))
	private List<NaucnaOblast> naucneOblasti;
	
	@OneToOne(mappedBy="glavniUredCas")
	private Korisnik glavniUrednik;
	
	@OneToMany(mappedBy = "urednikNOCas")
	private List<Korisnik> uredniciNO;
	
	@OneToMany(mappedBy="casopis")
	private List<Rad> rad;
	
	@ManyToMany
	@JoinTable(name="recezenti_casopisa",
	joinColumns = @JoinColumn(name="casopis_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="recez_id", referencedColumnName="id"))
	private List<Korisnik> recezenti;
	
	@ManyToMany
	@JoinTable(name="nacini_placanja",
	joinColumns = @JoinColumn(name="casopis_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="tipPlacanja_id", referencedColumnName="id"))
	private List<NacinPlacanja> naciniPlacanja;
	
	public Casopis() {
		
	}

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

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public List<NaucnaOblast> getNaucneOblasti() {
		return naucneOblasti;
	}

	public void setNaucneOblasti(List<NaucnaOblast> naucneOblasti) {
		this.naucneOblasti = naucneOblasti;
	}

	public Korisnik getGlavniUrednik() {
		return glavniUrednik;
	}

	public void setGlavniUrednik(Korisnik glavniUrednik) {
		this.glavniUrednik = glavniUrednik;
	}

	public List<Korisnik> getUredniciNO() {
		return uredniciNO;
	}

	public void setUredniciNO(List<Korisnik> uredniciNO) {
		this.uredniciNO = uredniciNO;
	}

	public List<Korisnik> getRecezenti() {
		return recezenti;
	}

	public void setRecezenti(List<Korisnik> recezenti) {
		this.recezenti = recezenti;
	}

	public List<NacinPlacanja> getNaciniPlacanja() {
		return naciniPlacanja;
	}

	public void setNaciniPlacanja(List<NacinPlacanja> naciniPlacanja) {
		this.naciniPlacanja = naciniPlacanja;
	}

	public List<Rad> getRad() {
		return rad;
	}

	public void setRad(List<Rad> rad) {
		this.rad = rad;
	}
	
	
}

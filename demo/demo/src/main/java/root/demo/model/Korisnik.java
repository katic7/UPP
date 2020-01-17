package root.demo.model;

import java.util.Set;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ime;
	
	@Column 
	private String prezime;
	
	@Column
	private String grad;
	
	@Column
	private String drzava;
	
	@Column
	private String titula;
	
	@Column
	private String email;
	
	@Column
	private String kor_ime;
	
	@Column
	private String lozinka;
	
	@Column
	private boolean recezent;
	
	@Column
	private String akt_kod;
	
	@Column
	private boolean aktiviran;
	
	@Column
	private boolean hoce_rec;
	
	@ManyToOne
	private Casopis urednikNOCas;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="korisnik_oblasti",
	joinColumns = @JoinColumn(name="korisnik_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="oblast_id", referencedColumnName="id"))
	private Set<NaucnaOblast> noblasti;
	
	@OneToOne
	private Casopis glavniUredCas;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name="korinik_role",
	joinColumns = @JoinColumn(name="korisnik_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id"))
	private Set<Roles> roles;
	
	public Korisnik () {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKor_ime() {
		return kor_ime;
	}

	public void setKor_ime(String kor_ime) {
		this.kor_ime = kor_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isRecezent() {
		return recezent;
	}

	public void setRecezent(boolean recezent) {
		this.recezent = recezent;
	}

	public String getAkt_kod() {
		return akt_kod;
	}

	public void setAkt_kod(String akt_kod) {
		this.akt_kod = akt_kod;
	}

	public boolean isAktiviran() {
		return aktiviran;
	}

	public void setAktiviran(boolean aktiviran) {
		this.aktiviran = aktiviran;
	}

	public boolean isHoce_rec() {
		return hoce_rec;
	}

	public void setHoce_rec(boolean hoce_rec) {
		this.hoce_rec = hoce_rec;
	}

	public Casopis getUrednikNOCas() {
		return urednikNOCas;
	}

	public void setUrednikNOCas(Casopis urednikNOCas) {
		this.urednikNOCas = urednikNOCas;
	}

	public Casopis getGlavniUredCas() {
		return glavniUredCas;
	}

	public void setGlavniUredCas(Casopis glavniUredCas) {
		this.glavniUredCas = glavniUredCas;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Set<NaucnaOblast> getNoblasti() {
		return noblasti;
	}

	public void setNoblasti(Set<NaucnaOblast> noblasti) {
		this.noblasti = noblasti;
	}

	
	
	
}

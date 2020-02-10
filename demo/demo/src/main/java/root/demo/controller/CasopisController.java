package root.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.dto.CasopisDto;
import root.demo.dto.FormFieldsDto;
import root.demo.dto.KorisnikDto;
import root.demo.dto.NacinPlacanjaDto;
import root.demo.dto.NaucnaOblastDto;
import root.demo.model.Casopis;
import root.demo.model.Korisnik;
import root.demo.model.NacinPlacanja;
import root.demo.model.NaucnaOblast;
import root.demo.model.Role;
import root.demo.model.Roles;
import root.demo.repository.CasopisRepositoy;
import root.demo.repository.KorisnikRepository;
import root.demo.repository.NacinPlacanjaRepository;
import root.demo.repository.NaucnaOblastRepository;

@Controller
@RequestMapping("/casopis")
public class CasopisController {
	
	@Autowired
	NaucnaOblastRepository ncRepo;
	
	@Autowired
	NacinPlacanjaRepository npRepo;
	
	@Autowired
	KorisnikRepository korRepo;
	
	@Autowired
	CasopisRepositoy casRepo;
	
	@GetMapping(path = "/getNaucneOblasti", produces = "application/json")  
    public @ResponseBody List<NaucnaOblastDto> getNaucne() {
		List<NaucnaOblastDto> lista = new ArrayList<NaucnaOblastDto>();
		List<NaucnaOblast>naucna = new ArrayList<NaucnaOblast>();
		
		naucna = ncRepo.findAll();
		for(NaucnaOblast nc:naucna) {
			NaucnaOblastDto nco = new NaucnaOblastDto();
			nco.setId(nc.getId());
			nco.setNaziv(nc.getNaziv());
			lista.add(nco);
		}
		return lista;
	}
	
	@GetMapping(path = "/getRecez/{issn}/{rola}", produces = "application/json")  
    public @ResponseBody List<KorisnikDto> getRecezenti(@PathVariable String issn, @PathVariable String rola) {
		List<KorisnikDto> lista = new ArrayList<KorisnikDto>();
		List<Korisnik> recezenti = new ArrayList<Korisnik>();
		Casopis cas = casRepo.findOneByIssn(issn);
		List<NaucnaOblast> nacune = cas.getNaucneOblasti();
		
		List<Korisnik> korisnici = korRepo.findAll();
		for(Korisnik kor : korisnici) {
			boolean flag = false;
			for(Role ro : kor.getRoles()){
				if(ro.getName().equals(rola)) {
					flag = true;
					break;
				}
			}
			if(flag) {
				recezenti.add(kor);
			}
		}
		for (Korisnik kor : recezenti) {
			Set<NaucnaOblast> kor_oblasti = kor.getNoblasti();
			boolean fleg = false;
			for (NaucnaOblast sf : kor_oblasti) {
				
				for (NaucnaOblast nc : nacune) {
					if (nc.getId() == sf.getId()) {
						fleg = true;
						break;
					}
					
				}
				if (fleg == true) {
					KorisnikDto kdto = new KorisnikDto();
					kdto.setId(kor.getId());
					kdto.setIme(kor.getIme());
					kdto.setPrezime(kor.getPrezime());
					lista.add(kdto);
					fleg = false;
					break;
				}
			}
			
		}
		return lista;
	}
	
	@GetMapping(path = "/getNacinePlacanja", produces = "application/json")  
    public @ResponseBody List<NacinPlacanjaDto> getPlacanja() {
		List<NacinPlacanjaDto> lista = new ArrayList<NacinPlacanjaDto>();
		List<NacinPlacanja>nacin = new ArrayList<NacinPlacanja>();
		
		nacin = npRepo.findAll();
		for(NacinPlacanja nc:nacin) {
			NacinPlacanjaDto ncdto = new NacinPlacanjaDto();
			ncdto.setId(nc.getId());
			ncdto.setNacin_placanja(nc.getNacin_placanja());
			lista.add(ncdto);
		}
		return lista;
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public @ResponseBody List<CasopisDto> getAllCasopis(){
		List<CasopisDto> retVal = new ArrayList<CasopisDto>();
		List<Casopis> cas = casRepo.findAll();
		for(Casopis c:cas) {
			if(c.isAktivan()) {
				CasopisDto cDto = new CasopisDto();
				cDto.setId(c.getId());
				cDto.setNaziv(c.getNaziv());
				retVal.add(cDto);
			}
		}
		return retVal;
	}
}

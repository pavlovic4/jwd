package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Najam;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.support.AutomobilDTOToAutomobil;
import jwd.wafepa.support.AutomobilToAutomobilDTO;
import jwd.wafepa.web.dto.AutomobilDTO;

@RestController
@RequestMapping("/api/automobili")
public class ApiAutomobilController {

	@Autowired
	private AutomobilService automobilService;
	
	@Autowired
	private AutomobilToAutomobilDTO toDTO;
	
	@Autowired
	private AutomobilDTOToAutomobil toAutomobil;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AutomobilDTO>> get(
			@RequestParam(required=false) String model,
			@RequestParam(required=false) Integer minGod,
			@RequestParam(required=false) Double maxPot,
			@RequestParam(defaultValue="0") int page){
		
		Page<Automobil> automobili;
		
		if(model != null || minGod != null || maxPot != null) {
			automobili = automobilService.pretraga(model, minGod, maxPot, page);
		}else{
			automobili = automobilService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(automobili.getTotalPages()) );
		return  new ResponseEntity<>(
				toDTO.convert(automobili.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<AutomobilDTO> get(
			@PathVariable Long id){
		Automobil automobil = automobilService.findOne(id);
		
		if(automobil == null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(automobil),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AutomobilDTO> add(
			@Validated @RequestBody AutomobilDTO noviAuto){
		
		Automobil automobil = toAutomobil.convert(noviAuto); 
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toDTO.convert(automobil),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<AutomobilDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody AutomobilDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Automobil automobil = toAutomobil.convert(izmenjen); 
		automobilService.save(automobil);
		
		return new ResponseEntity<>(toDTO.convert(automobil),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<AutomobilDTO> delete(@PathVariable Long id){
		
		Automobil automobilZaBrisanje = automobilService.findOne(id);
		
		if (automobilZaBrisanje == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		automobilService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{automobilId}")
	public ResponseEntity<AutomobilDTO> iznajmi(@PathVariable Long automobilId) {
		Automobil automobil = automobilService.findOne(automobilId);
		Najam najam = null;
		
		if (automobil != null) {
			najam = automobilService.iznajmi(automobilId);
		}
		
		if (najam == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<AutomobilDTO>(toDTO.convert(automobil), HttpStatus.CREATED);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}

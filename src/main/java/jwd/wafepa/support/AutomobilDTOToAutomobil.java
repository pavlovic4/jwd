package jwd.wafepa.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.KompanijaService;
import jwd.wafepa.web.dto.AutomobilDTO;

@Component
public class AutomobilDTOToAutomobil 
	implements Converter<AutomobilDTO, Automobil>{

	@Autowired
	private AutomobilService automobilService;
	
	@Autowired
	private KompanijaService kompanijaService;
	
	@Override
	public Automobil convert(AutomobilDTO dto) {
		Automobil auto;
		
		if (dto.getId() == null) {
			auto = new Automobil();
			auto.setKompanija(kompanijaService.findOne(dto.getKompanijaId()));
		}else {
			auto = automobilService.findOne(dto.getId());
		}
		
		auto.setGodiste(dto.getGodiste());
		auto.setIznajmljen(dto.getIznajmljen());
		auto.setModel(dto.getModel());
		auto.setPotrosnja(dto.getPotrosnja());
		auto.setRegistracija(dto.getRegistracija());
			
		return auto;
	}

}

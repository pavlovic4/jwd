package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.web.dto.AutomobilDTO;

@Component
public class AutomobilToAutomobilDTO 
	implements Converter<Automobil, AutomobilDTO>{

	@Override
	public AutomobilDTO convert(Automobil auto) {
		AutomobilDTO dto = new AutomobilDTO();
		
		dto.setId(auto.getId());
		dto.setGodiste(auto.getGodiste());
		dto.setIznajmljen(auto.getIznajmljen());
		if (dto.getIznajmljen() == true) {
			dto.setIznajmljenTekst("DA");
		} else {
			dto.setIznajmljenTekst("NE");
		}
		dto.setKompanijaId(auto.getKompanija().getId());
		dto.setKompanijaNaziv(auto.getKompanija().getNaziv());
		dto.setModel(auto.getModel());
		dto.setRegistracija(auto.getRegistracija());
		dto.setPotrosnja(auto.getPotrosnja());
		
		return dto;
	}
	
	public List<AutomobilDTO> convert(List<Automobil> automobili) {
		List<AutomobilDTO> ret = new ArrayList<>();
		
		for (Automobil auto : automobili) {
			ret.add(convert(auto));
		}
		
		return ret;
	}

}

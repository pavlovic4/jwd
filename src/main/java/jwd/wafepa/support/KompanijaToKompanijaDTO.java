package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Kompanija;
import jwd.wafepa.web.dto.KompanijaDTO;

@Component
public class KompanijaToKompanijaDTO 
	implements Converter<Kompanija, KompanijaDTO> {

	@Override
	public KompanijaDTO convert(Kompanija kom) {
		KompanijaDTO dto = new KompanijaDTO();
		
		dto.setId(kom.getId());
		dto.setAdresa(kom.getAdresa());
		dto.setNaziv(kom.getNaziv());
		dto.setTelefon(kom.getTelefon());
		
		return dto;
	}
	
	public List<KompanijaDTO> convert(List<Kompanija> kompanije) {
		List<KompanijaDTO> ret = new ArrayList<>();
		
		for (Kompanija kompanija : kompanije) {
			ret.add(convert(kompanija));
		}
		
		return ret;
	}

}

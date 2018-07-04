package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Kompanija;
import jwd.wafepa.repository.KompanijaRepository;
import jwd.wafepa.service.KompanijaService;

@Service
public class JpaKompanijaService implements KompanijaService {

	@Autowired
	private KompanijaRepository kompanijaRepository;
	
	@Override
	public List<Kompanija> findAll() {
		return kompanijaRepository.findAll();
	}

	@Override
	public Kompanija findOne(Long id) {
		return kompanijaRepository.findOne(id);
	}

	@Override
	public void save(Kompanija kompanija) {
		kompanijaRepository.save(kompanija);
	}

	@Override
	public void remove(Long id) {
		kompanijaRepository.delete(id);
	}

}

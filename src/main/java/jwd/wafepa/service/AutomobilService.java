package jwd.wafepa.service;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Najam;

public interface AutomobilService {

	Page<Automobil> findAll(int pageNum);
	Automobil findOne(Long id);
	void save(Automobil automobil);
	void remove(Long id);
	Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId);
	
	Page<Automobil> pretraga(String model, Integer minGod, Double maxPot, int pageNum);
	
	Najam iznajmi(Long automobilId);
}

package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Najam;
import jwd.wafepa.repository.AutomobilRepository;
import jwd.wafepa.repository.NajamRepository;
import jwd.wafepa.service.AutomobilService;

@Service
public class JpaAutomobilService implements AutomobilService{

	@Autowired
	private AutomobilRepository automobilRepository;
	
	@Autowired
	private NajamRepository najamRepository;
	
	@Override
	public Page<Automobil> findAll(int pageNum) {
		return automobilRepository.findAll(new PageRequest(pageNum, 3));
	}

	@Override
	public Automobil findOne(Long id) {
		return automobilRepository.findOne(id);
	}

	@Override
	public void save(Automobil automobil) {
		automobilRepository.save(automobil);
	}

	@Override
	public void remove(Long id) {
		automobilRepository.delete(id);
	}

	@Override
	public Page<Automobil> findByKompanijaId(int pageNum, Long kompanijaId) {
		return automobilRepository.findByKompanijaId(kompanijaId, new PageRequest(pageNum, 2));
	}

	@Override
	public Page<Automobil> pretraga(String model, Integer minGod, Double maxPot, int pageNum) {
		if (model != null) {
			model = "%" + model + "%";
		}
		return automobilRepository.pretraga(model, minGod, maxPot, new PageRequest(pageNum, 2));
	}

	@Override
	public Najam iznajmi(Long automobilId) {
		Automobil automobil = automobilRepository.findOne(automobilId);
		
		if (automobil.getIznajmljen() == true) {
			return null;
		}
		automobil.setIznajmljen(true);
		automobilRepository.save(automobil);
		
		Najam najam = new Najam();
		najam.setAutomobil(automobil);
		najamRepository.save(najam);
		
		return najam;
	}
	
	

}

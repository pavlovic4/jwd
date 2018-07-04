package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Automobil;
import jwd.wafepa.model.Kompanija;
import jwd.wafepa.service.AutomobilService;
import jwd.wafepa.service.KompanijaService;

@Component
public class TestData {

	@Autowired
	private KompanijaService kompanijaService;
	
	@Autowired
	private AutomobilService automobilService;
	
	@PostConstruct
	public void init() {
		
		Kompanija k1 = new Kompanija("Avaco", "Bulevar Mihaila Pupina 10", "063/111-111");
		Kompanija k2 = new Kompanija("Europcar", "Somborski Bulevar 5", "063/222-222");
		Kompanija k3 = new Kompanija("Irent", "Bulevar Evrope 11", "063/333-333");
		
		kompanijaService.save(k1);
		kompanijaService.save(k2);
		kompanijaService.save(k3);
		
		Automobil a1 = new Automobil("Nissan Micra", "NS-221-DD", 1992, 7.5);
		Automobil a2 = new Automobil("Audi A4", "BG-345-EE", 2009, 6.5);
		Automobil a3 = new Automobil("Audi A3", "NS-123-AA", 2005, 6.9);
		Automobil a4 = new Automobil("BMW X6", "BG-345-BB", 2015, 9.5);
		Automobil a5 = new Automobil("Fiat 500L", "NS-546-CC", 2016, 7.7);
		
		a1.setKompanija(k1);
		a2.setKompanija(k1);
		a3.setKompanija(k1);
		a4.setKompanija(k2);
		a5.setKompanija(k3);
		
		automobilService.save(a1);
		automobilService.save(a2);
		automobilService.save(a3);
		automobilService.save(a4);
		automobilService.save(a5);
		
	}
}

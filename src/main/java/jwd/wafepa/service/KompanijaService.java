package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Kompanija;

public interface KompanijaService {

	List<Kompanija> findAll();
	Kompanija findOne(Long id);
	void save(Kompanija kompanija);
	void remove(Long id);
}

package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Automobil;

@Repository
public interface AutomobilRepository 
	extends JpaRepository<Automobil, Long> {

	Page<Automobil> findByKompanijaId(Long kompanijaId, Pageable pageRequest);
	
	@Query("SELECT a FROM Automobil a WHERE "
			+ "(:model IS NULL or a.model like :model ) AND "
			+ "(:minGod IS NULL OR a.godiste >= :minGod  ) AND "
			+ "(:maxPot IS NULL OR a.potrosnja <= :maxPot)"
			)
	Page<Automobil> pretraga(
			@Param("model") String model, 
			@Param("minGod") Integer minG, 
			@Param("maxPot") Double maxP,
			Pageable pageRequest);
}

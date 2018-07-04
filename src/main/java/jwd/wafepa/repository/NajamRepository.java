package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Najam;

@Repository
public interface NajamRepository 
	extends JpaRepository<Najam, Long>{

}

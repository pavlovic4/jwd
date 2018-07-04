package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_najam")
public class Najam {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Automobil automobil;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Automobil getAutomobil() {
		return automobil;
	}
	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}
	
}

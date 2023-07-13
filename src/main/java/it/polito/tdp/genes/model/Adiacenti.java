package it.polito.tdp.genes.model;

public class Adiacenti {
	String localization;
	Integer peso;
	
	public Adiacenti(String localization, Integer peso) {
		super();
		this.localization = localization;
		this.peso = peso;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	

}

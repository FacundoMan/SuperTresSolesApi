package com.springboot.TresSolesApi.Service;

import java.util.List;

import com.springboot.TresSolesApi.Modelo.Oferta;

public interface OfertaService {
	public List<Oferta> getAllOfertas();
	
	public Oferta getOfertaById(Long id);
	
	public void addOferta(Oferta oferta);
	
}

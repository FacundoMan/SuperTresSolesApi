package com.springboot.TresSolesApi.Implements;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.TresSolesApi.Modelo.Oferta;
import com.springboot.TresSolesApi.Repositorio.OfertaRepository;
import com.springboot.TresSolesApi.Service.OfertaService;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

	@Autowired
	OfertaRepository repository;
	
	@Override
	public List<Oferta> getAllOfertas() {
		return(List<Oferta>)repository.findAll();
	}

	@Override
	public Oferta getOfertaById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void addOferta(Oferta oferta) {
		repository.save(oferta);
		
	}

	

}

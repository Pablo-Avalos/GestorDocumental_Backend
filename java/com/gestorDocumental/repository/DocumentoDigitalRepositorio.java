package com.gestorDocumental.repository;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.entity.DocumentoDigital;


@Repository ("repositorio")
@EnableJpaRepositories("com.gestorDocumental.repository")
public interface DocumentoDigitalRepositorio extends JpaRepository<DocumentoDigital,Serializable>{
	public abstract DocumentoDigital findById(long id);
	public abstract List<DocumentoDigital> findByProcesoAndSubprocesoAndOperacion (String proceso,String subproceso,String operacion);
	public abstract List<DocumentoDigital> findByProcesoAndSubproceso(String proceso, String subproceso);
	
	public abstract List<DocumentoDigital> findByClienteObjeto(Cliente clienteDU);
	
}



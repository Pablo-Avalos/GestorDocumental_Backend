package com.gestorDocumental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;

/*
extends JpaRepository<TiposDocumentosDeSubProceso,Serializable>{
 */
public interface TiposDocumentosDeSubProcesoRepositorio{// extends JpaRepository<SubProceso,Serializable>{
	
	//@Query("SELECT f.subProceso FROM tiposDocumentosDeSubProceso f WHERE f.subProcesoObjeto = :parametro")
	//List<Object> obtenerTipoDeDocumento(@Param("subProcesoObjeto") SubProceso parametro);
	
}

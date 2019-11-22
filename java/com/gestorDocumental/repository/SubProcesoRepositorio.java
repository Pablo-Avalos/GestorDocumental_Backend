package com.gestorDocumental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;

public interface SubProcesoRepositorio extends JpaRepository<SubProceso,Serializable>{
	public abstract List<SubProceso> findAll();
	public abstract List<SubProceso> findByDescripcionproceso(String descripcion);
	public abstract List<SubProceso> findByProcesoObjetoId(long id);
	
	@Query("SELECT f.descripcion FROM SubProceso f")
	List<String> obtenerSubProcesos();
	
	@Query("SELECT f.descripcion,f.subproceso FROM SubProceso f WHERE f.procesoObjeto = :procesoObjeto")
	List<Object> obtenerSubProcesosDeProceso(@Param("procesoObjeto") Proceso parametro);
	
	
	@Query("SELECT f.tiposDocumentos FROM SubProceso f WHERE f.descripcion = :descripcion")
	List<Object> obtenerTipoDeDocumento(@Param("descripcion") String descripcion);
	
}
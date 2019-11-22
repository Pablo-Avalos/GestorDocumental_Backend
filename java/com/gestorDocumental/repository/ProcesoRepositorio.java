package com.gestorDocumental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorDocumental.entity.Proceso;

public interface ProcesoRepositorio extends JpaRepository<Proceso,Serializable>{
	public abstract List<Proceso> findAll();
	
	//@Query("SELECT f.descripcion,f.id FROM Proceso f")
	//List<Object> obtenerProcesos();
	//@Query("SELECT f.descripcion FROM proceso f")
	//List<Object> procesoByDescripcion();
}

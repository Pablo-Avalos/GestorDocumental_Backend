package com.gestorDocumental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestorDocumental.entity.TipoDocumento;

public interface TipoDocumentoRepositorio  extends JpaRepository<TipoDocumento,Serializable>{
	public abstract List<TipoDocumento> findAll();
	public abstract TipoDocumento findByDescripcion(String descripcion);
}

package com.gestorDocumental.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;
import com.gestorDocumental.entity.TipoDocumento;

public interface TipoDocumentoRepositorio  extends JpaRepository<TipoDocumento,Serializable>{
	public abstract List<TipoDocumento> findAll();
	//public abstract List<TipoDocumento> findByDescripcion();
	public abstract TipoDocumento findByDescripcion(String descripcion);
	//public abstract TipoDocumento deleteByDescripcion(String desdcripcion);
	//@Query("DELETE FROM TipoDocumento f WHERE f.DESCRIPCION = :descripcion")
	//public void borrarTipoDocumento(@Param("descripcion") String descripcion);
	//@Query("DELETE FROM TipoDocumento f WHERE f.tipoDocumentoId = :tipoDocumento AND subProcesoID = :l")
	//public abstract void borrarRelacion(@Param("tipoDocumentoId") TipoDocumento tipoDocumento, @Param("subProcesoId")SubProceso l);
}

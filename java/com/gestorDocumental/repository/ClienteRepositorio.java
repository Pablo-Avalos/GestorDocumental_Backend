package com.gestorDocumental.repository;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestorDocumental.entity.Cliente;
	
public interface ClienteRepositorio extends JpaRepository<Cliente,Serializable>{
	public abstract Cliente findByRazonSocial(String razonSocial);
	public abstract Cliente findByNumeroCliente(Integer nroCliente);
	
	@Query("SELECT f.numeroCliente FROM Cliente f")
	public abstract List<Object> obtenerNumerosCliente();
}

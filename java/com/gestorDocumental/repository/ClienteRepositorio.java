package com.gestorDocumental.repository;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestorDocumental.entity.Cliente;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;

//@Repository ("clienteRepositorio")
//@EnableJpaRepositories("com.gestorDocumental.repository")	
public interface ClienteRepositorio extends JpaRepository<Cliente,Serializable>{
//	public abstract ClienteEmpresa findByCUIT(long id);
	public abstract Cliente findByRazonSocial(String razonSocial);
	public abstract Cliente findByNumeroCliente(Integer clienteCUIT);
	//public abstract Cliente updateBySolicitud findByNumeroCliente(Integer clienteCUIT);
}

package com.blackjack.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.blackjack.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
	Conta findByCodigo(Long codigo);

	@Query("SELECT email FROM Conta ct WHERE LOWER(ct.email) = LOWER(:email)") 
	Page<Conta> procurarConta(String email, Pageable pageable);
	
	@Query("SELECT codigo FROM Conta ct WHERE LOWER(ct.email) = LOWER(:email) AND ct.senha = :senha") 
	Long fazerLogin(String email, String senha);
	
	@Query("SELECT nome, email, celular FROM Conta ct WHERE id = :id") 
	Page<Conta> buscarUsuario(Long id, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("update Conta c set c.nome = :nome, c.celular = :celular, c.email = :email, c.senha = :senha where c.codigo = :codigo")
	void updateConta(String nome, String celular, String email, String senha, long codigo);
}

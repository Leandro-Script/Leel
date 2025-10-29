package com.ifsp.Leel.Repository;

import org.springframework.stereotype.Repository;

import com.ifsp.Leel.Model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ClienteRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Cliente cliente) {
        em.persist(cliente);
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        return em.merge(cliente);
    }

    @Transactional
    public void delete(Long id) {
        em.createQuery(
                "DELETE FROM Cliente c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public Cliente findByNomeAndSenha(String nome, String senha) {
        try {
            return em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.nome = :nome AND c.senha = :senha", Cliente.class)
                    .setParameter("nome", nome)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}

package com.ifsp.Leel.Repository;

import org.springframework.stereotype.Repository;

import com.ifsp.Leel.Model.Vendedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class VendedorRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Vendedor vendedor) {
        em.persist(vendedor);
    }

    @Transactional
    public Vendedor update(Vendedor vendedor) {
        return em.merge(vendedor);
    }

    @Transactional
    public void delete(Long id) {
        em.createQuery(
                "DELETE FROM Vendedor v WHERE v.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}

package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Product;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;


@Transactional(SUPPORTS)
public class ProductRepository {

    @PersistenceContext(unitName = "productStorePU")
    private EntityManager em;

    @Inject
    private NumberGenerator generator;

    @Inject
    private TextUtil textUtil;

    public Product find(@NotNull  Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT b FROM Product b ORDER BY b.name DESC", Product.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Product b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(REQUIRED)
    public Product create(@NotNull Product product) {
        product.setIsbn(generator.generateNumber());
        product.setName(textUtil.sanitaze(product.getName()));
        em.persist(product);
        return product;
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Product.class, id));
    }
}

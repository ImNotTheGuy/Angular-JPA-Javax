/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.jpa.EntityManagerSingleton;
import com.example.models.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;

/**
 *
 * @author Imnottheguy
 */
public class CategoryDao {

    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();

    // CREATE
    public void create(Category category) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(category);
        tx.commit();

    }

    // READ
    // READ ALL
    public List<Category> list() {

        Query findAllQuery = entityManager.createQuery("select c from Category c");
        return findAllQuery.getResultList();
    }

    // READ ONE
    public Category findById(long id) {

        return entityManager.find(Category.class, id);
    }

    public List<Category> findByName(String name) {
        Query findByNameQuery = entityManager.createQuery("select c from Category c where c.name=:name")
                .setParameter("name", name);
        
        System.out.println(findByNameQuery.getResultList());
        return findByNameQuery.getResultList();
        
    }

    // UPDATE
    public void update(long id, Category category) {

        Category categoryToUpdate = findById(id);

        categoryToUpdate.setName(category.getName());

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(categoryToUpdate);
        tx.commit();

    }

    // DELETE
    public void deleteById(long id) {

        Query deleteByIdQuery = entityManager.createQuery("delete from Category c where c.id=:id")
                .setParameter("id", id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        int result = deleteByIdQuery.executeUpdate();
        if (result == 1) {
            System.out.println("Succesfully deleted category " + id);
        }
        tx.commit();
        entityManager.clear();

    }

}

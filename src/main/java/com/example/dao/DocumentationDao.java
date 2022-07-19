package com.example.dao;

import static com.example.dao.CategoryDao.entityManager;
import com.example.jpa.EntityManagerSingleton;
import com.example.models.Category;
import com.example.models.Documentation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class DocumentationDao {

    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();
    public static CategoryDao categoryDao = new CategoryDao();

    // CREATE
    public void create(String name, String url) {

        Category category;

        try {
            category = categoryDao.findByName(name).get(0);
        } catch (IndexOutOfBoundsException npExc) {
            System.out.println("Category did not exist, creating new...");
            Category newCategory = new Category();
            newCategory.setName(name);
            categoryDao.create(newCategory);
            category = categoryDao.findByName(name).get(0);
            System.out.println("Created new category: " + category);
        }

        Documentation documentation = new Documentation();
        documentation.setCategory(category);
        documentation.setUrl(url);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(documentation);
        tx.commit();

    }

    // READ
    
        // READ ALL
    public List<Documentation> list() {

        Query findAllQuery = entityManager.createQuery("select d from Documentation d");
        return findAllQuery.getResultList();
    }

        // READ ONE
    public Documentation findById(long id) {

        return entityManager.find(Documentation.class, id);
    }
    
        // READ ALL BY CATEGORY NAME
    
    public List<Documentation> findByCategoryName(String categoryName){
        
        List<Documentation> documentationList = new ArrayList<Documentation>();
        
        for (Documentation documentation: this.list()){
            if (documentation.getCategory().getName().equals(categoryName)){
                documentationList.add(documentation);
            }
        }
        
        return documentationList;
        
    }


    // UPDATE
    public void update(long id, Documentation documentation) {

        Documentation documentationToUpdate = findById(id);

        documentationToUpdate.setUrl(documentation.getUrl());
        documentationToUpdate.setCategory(documentation.getCategory());
        

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(documentationToUpdate);
        tx.commit();

    }

    // DELETE
    public void deleteById(long id) {

        Query deleteByIdQuery = entityManager.createQuery("delete from Documentation d where d.id=:id")
                .setParameter("id", id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        int result = deleteByIdQuery.executeUpdate();
        if (result == 1) {
            System.out.println("Succesfully deleted documentation " + id);
        }
        tx.commit();
        entityManager.clear();

    }

}

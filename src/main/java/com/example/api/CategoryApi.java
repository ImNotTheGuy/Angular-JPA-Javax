/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.api;

import com.example.dao.CategoryDao;
import com.example.dao.DocumentationDao;
import com.example.jpa.EntityManagerSingleton;
import com.example.models.Category;
import com.example.models.Documentation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Imnottheguy
 */
@Path("category")
public class CategoryApi {

    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();
    public static CategoryDao categoryDao = new CategoryDao();

    // CREATE
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Category category) {
        categoryDao.create(category);
    }

    // READ
      // READ ALL
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list() {
        return categoryDao.list();
    }

      // READ BY NAME
    @Path("byname/{name}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Category findByName(@PathParam("name") String name) {
        try {
            return categoryDao.findByName(name).get(0);
        } catch (Exception e) {
            System.out.println("Name " + name + " not found.");
            return null;
        }
    }

      // READ BY ID
    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Category findById(@PathParam("id") long id) {
        return categoryDao.findById(id);
    }

    // UPDATE
    @Path("/{id}")
    @PUT()
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") long id, Category category) {
        categoryDao.update(id, category);
    }

    // DELETE
    @Path("/{id}")
    @DELETE()
    public void delete(@PathParam("id") long id) {
        categoryDao.deleteById(id);
    }

}

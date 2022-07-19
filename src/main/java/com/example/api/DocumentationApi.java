/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.api;

import com.example.dao.DocumentationDao;
import com.example.jpa.EntityManagerSingleton;
import com.example.models.Documentation;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author Imnottheguy
 */
@Path("documentation")
public class DocumentationApi {

    // TODO: Controls
    // TODO: Response codes
    // TODO: Business / DAO seperation so that here we only call business methods
    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();
    public static DocumentationDao documentationDao = new DocumentationDao();

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(String jsonString) {

        System.out.println("POST in /documentation ...");
        JSONObject obj = new JSONObject(jsonString);
        String name = obj.getString("name");
        String url = obj.getString("url");

        documentationDao.create(name, url);

    }

    // READ
      // READ ALL 
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<Documentation> list() {
        return documentationDao.list();
    }

      // READ ONE BY ID
    @Path("{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Documentation read(
            @PathParam("id") long id,
            @Context ServletContext context,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws ServletException, IOException {

        Documentation doc =  documentationDao.findById(id);
        request.setAttribute("doc", doc);
        context.getRequestDispatcher("/WEB-INF/htmlPages/test.jsp").forward(request, response);
        return doc;
    }

      // READ ALL BY NAME
    @Path("/byname/{name}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<Documentation> readByCategoryName(@PathParam("name") String name){
            
        return documentationDao.findByCategoryName(name);
    }

    // UPDATE
    @Path("{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    public void udpate(@PathParam("id") long id, Documentation documentation) {
        documentationDao.update(id, documentation);
    }

    // DELETE
    @Path("{id}")
    @DELETE()
    public void udpate(@PathParam("id") long id) {
        documentationDao.deleteById(id);
    }

}

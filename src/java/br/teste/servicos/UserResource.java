package br.teste.servicos;

import br.teste.modelo.User;
import br.teste.modelo.UserDao;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PATCH;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    @GET
    @Produces("application/json")
    @Path("buscar/{id}")
    public String getUsuario(@PathParam("id") Long id) {

        User u = new User();
        u.setId(id);

        UserDao dao = new UserDao();
        u = dao.buscar(u);

        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(u);
    }

    @DELETE
    @Path("excluir/{id}")
    public String excluir(@PathParam("id") Long id) {

        User u = new User();
        u.setId(id);

        UserDao dao = new UserDao();
        u = dao.buscar(u);

        return dao.excluir(u);

    }

    @POST
    @Consumes({"application/json"})
    @Path("inserir")
    public String inserir(String content) {
        Gson g = new Gson();       
        User u = (User) g.fromJson(content, User.class);
        
        UserDao dao = new UserDao();
        return dao.inserir(u);
        
    }
    
    @PATCH
    @Consumes({"application/json"})
    @Path("alterar/{id}")
    public String alterar(@PathParam("id") Long id, String content) {
        
        User u = new User();
        u.setId(id);

        UserDao dao = new UserDao();
        
        Gson g = new Gson();       
        User usuario = (User) g.fromJson(content, User.class);

        return dao.alterar(id, usuario);
        
    }
    
}

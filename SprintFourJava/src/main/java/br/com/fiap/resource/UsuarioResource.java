package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<UsuarioTO> resultado = usuarioBO.findAll();
        if (resultado != null && !resultado.isEmpty()) {
            return Response.ok(resultado).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        UsuarioTO usuario = usuarioBO.findById(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(UsuarioTO usuario) {
        try {
            UsuarioTO savedUsuario = usuarioBO.save(usuario);
            return Response.status(Response.Status.CREATED).entity(savedUsuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UsuarioTO usuario) {
        usuario.setId(id); // Set the ID for the update
        UsuarioTO updatedUsuario = usuarioBO.update(usuario);
        if (updatedUsuario != null) {
            return Response.ok(updatedUsuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = usuarioBO.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

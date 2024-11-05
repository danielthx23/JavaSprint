package br.com.fiap.resource;

import br.com.fiap.bo.OficinaBO;
import br.com.fiap.to.OficinaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/oficinas")
public class OficinaResource {
    private OficinaBO oficinaBO = new OficinaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<OficinaTO> resultado = oficinaBO.findAll();
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
        OficinaTO oficina = oficinaBO.findById(id);
        if (oficina != null) {
            return Response.ok(oficina).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

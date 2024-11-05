package br.com.fiap.resource;

import br.com.fiap.bo.ServicoBO;
import br.com.fiap.to.ServicoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/servicos")
public class ServicoResource {
    private ServicoBO servicoBO = new ServicoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ServicoTO> resultado = servicoBO.findAll();
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
        ServicoTO resultado = servicoBO.findById(id);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ServicoTO servico) {
        ServicoTO savedServico = servicoBO.save(servico);
        return Response.status(Response.Status.CREATED).entity(savedServico).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ServicoTO servico) {
        servico.setId(id);
        ServicoTO updatedServico = servicoBO.update(servico);
        if (updatedServico != null) {
            return Response.ok(updatedServico).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = servicoBO.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

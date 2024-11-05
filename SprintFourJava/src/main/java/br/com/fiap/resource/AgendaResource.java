package br.com.fiap.resource;

import br.com.fiap.bo.AgendaBO;
import br.com.fiap.to.AgendaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/agendamentos")
public class AgendaResource {
    private AgendaBO agendaBO = new AgendaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
            ArrayList<AgendaTO> resultado = agendaBO.findAll();
            if (resultado != null && !resultado.isEmpty()) {
                return Response.ok(resultado).build();
            } else {
                return Response.noContent().build();
            }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AgendaTO agendamento) {
            AgendaTO savedAgendamento = agendaBO.save(agendamento);
            return Response.status(Response.Status.CREATED).entity(savedAgendamento).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, AgendaTO agendamento) {
            agendamento.setId(id);
            AgendaTO updatedAgendamento = agendaBO.update(agendamento);
            if (updatedAgendamento != null) {
                return Response.ok(updatedAgendamento).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
            boolean deleted = agendaBO.delete(id);
            if (deleted) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
    }

    @GET
    @Path("/cliente/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByClienteId(@PathParam("clienteId") Long clienteId) {
            ArrayList<AgendaTO> resultado = agendaBO.findByClienteID(clienteId);
            if (resultado != null && !resultado.isEmpty()) {
                return Response.ok(resultado).build();
            } else {
                return Response.noContent().build();
            }
    }

    @GET
    @Path("/oficina/{oficinaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByOficinaId(@PathParam("oficinaId") Long oficinaId) {
            ArrayList<AgendaTO> resultado = agendaBO.findByOficinaID(oficinaId);
            if (resultado != null && !resultado.isEmpty()) {
                return Response.ok(resultado).build();
            } else {
                return Response.noContent().build();
            }
    }
}

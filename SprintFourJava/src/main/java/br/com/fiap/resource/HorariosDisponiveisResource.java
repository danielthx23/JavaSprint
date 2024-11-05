package br.com.fiap.resource;

import br.com.fiap.bo.HorariosDisponiveisBO;
import br.com.fiap.to.HorarioDisponivelTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/horarios-disponiveis")
public class HorariosDisponiveisResource {
    private HorariosDisponiveisBO horariosDisponiveisBO = new HorariosDisponiveisBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<HorarioDisponivelTO> resultado = horariosDisponiveisBO.findAll();
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
        HorarioDisponivelTO resultado = horariosDisponiveisBO.findById(id);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/oficina/{oficinaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByOficinaId(@PathParam("oficinaId") Long oficinaId) {
        ArrayList<HorarioDisponivelTO> resultado = horariosDisponiveisBO.findByOficinaId(oficinaId);
        if (resultado != null && !resultado.isEmpty()) {
            return Response.ok(resultado).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(HorarioDisponivelTO horariosDisponiveis) {
        HorarioDisponivelTO savedHorarios = horariosDisponiveisBO.save(horariosDisponiveis);
        return Response.status(Response.Status.CREATED).entity(savedHorarios).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, HorarioDisponivelTO horariosDisponiveis) {
        horariosDisponiveis.setId(id);
        HorarioDisponivelTO updatedHorarios = horariosDisponiveisBO.update(horariosDisponiveis);
        if (updatedHorarios != null) {
            return Response.ok(updatedHorarios).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = horariosDisponiveisBO.delete(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

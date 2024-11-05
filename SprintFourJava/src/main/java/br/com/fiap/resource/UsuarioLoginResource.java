package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioLoginBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class UsuarioLoginResource {
    private UsuarioLoginBO usuarioLoginBO = new UsuarioLoginBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioTO usuario) {
        UsuarioTO authenticatedUser = usuarioLoginBO.handleLogin(usuario);
        if (authenticatedUser != null) {
            return Response.ok(authenticatedUser).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("CPF/CNPJ ou Senha inválidos!.")
                    .build();
        }
    }
}

package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsuarioLoginDAO extends Repository {
    public UsuarioTO login(UsuarioTO usuario) {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "FROM u.*, c.CPF_CLIENTE, o.NR_CNPJ OFIX_USUARIO u " +
                "LEFT JOIN OFIX_CLIENTE c ON c.OFIX_USUARIO_ID_USUARIO = u.ID_USUARIO " +
                "LEFT JOIN OFIX_OFICINA o ON o.OFIX_USUARIO_ID_USUARIO = u.ID_USUARIO " +
                "ORDER BY u.ID_USUARIO";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    if ((usuario.getCliente().getCpf().equalsIgnoreCase(rs.getString("CPF_CLIENTE"))
                            || usuario.getOficina().getCnpj().equalsIgnoreCase(rs.getString("NR_CNPJ")))
                            && usuario.getSenha().equalsIgnoreCase(rs.getString("NM_SENHA"))) {
                        usuario.setId(rs.getLong("ID_USUARIO"));
                        usuario.setNome(rs.getString("NM_USUARIO"));
                        usuario.setEmail(rs.getString("NM_EMAIL"));
                        usuario.setTipoConta(rs.getString("FL_CONTA"));
                        usuario.setSenha(rs.getString("NM_SENHA"));
                        usuario.setFoto(rs.getString("IMG_FOTO"));
                        usuario.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                        usuario.setToken("Bearer " + (usuario.getEmail() + usuario.getSenha()).hashCode());
                        return usuario;
                    }
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }
}

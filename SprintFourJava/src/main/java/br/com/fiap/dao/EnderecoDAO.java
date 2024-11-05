package br.com.fiap.dao;

import br.com.fiap.to.EnderecoTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends Repository {

    public List<EnderecoTO> findAll() {
        List<EnderecoTO> enderecos = new ArrayList<>();
        String sql = """
        SELECT ID_ENDERECO, NR_CEP, NR_ENDERECO, NM_BAIRRO, NM_ESTADO, NM_RUA, DS_COMPLEMENTO, OFIX_USUARIO_ID_USUARIO
        FROM OFIX_ENDERECO
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EnderecoTO endereco = new EnderecoTO();
                endereco.setId(rs.getLong("ID_ENDERECO"));
                endereco.setCep(rs.getInt("NR_CEP"));
                endereco.setNumero(rs.getInt("NR_ENDERECO"));
                endereco.setBairro(rs.getString("NM_BAIRRO"));
                endereco.setEstado(rs.getString("NM_ESTADO"));
                endereco.setRua(rs.getString("NM_RUA"));
                endereco.setComplemento(rs.getString("DS_COMPLEMENTO"));
                endereco.getUsuario().setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar endereços: " + e.getMessage());
        }

        return enderecos;
    }

    public EnderecoTO save(EnderecoTO endereco) {
        String sql = "INSERT INTO OFIX_ENDERECO (NR_CEP, NR_ENDERECO, NM_BAIRRO, NM_ESTADO, NM_RUA, DS_COMPLEMENTO, OFIX_USUARIO_ID_USUARIO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_ENDERECO"})) {
            ps.setInt(1, endereco.getCep());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getEstado());
            ps.setString(5, endereco.getRua());
            ps.setString(6, endereco.getComplemento());
            ps.setLong(7, endereco.getUsuario().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long enderecoId = rs.getLong(1);
                    endereco.setId(enderecoId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o endereço: " + e.getMessage());
        }

        return endereco;
    }

    public boolean update(EnderecoTO endereco) {
        String sql = "UPDATE OFIX_ENDERECO SET NR_CEP = ?, NR_ENDERECO = ?, NM_BAIRRO = ?, NM_ESTADO = ?, NM_RUA = ?, DS_COMPLEMENTO = ?, OFIX_USUARIO_ID_USUARIO = ? WHERE ID_ENDERECO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, endereco.getCep());
            ps.setInt(2, endereco.getNumero());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getEstado());
            ps.setString(5, endereco.getRua());
            ps.setString(6, endereco.getComplemento());
            ps.setLong(7, endereco.getUsuario().getId());
            ps.setLong(8, endereco.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(long enderecoId) {
        String sql = "DELETE FROM OFIX_ENDERECO WHERE ID_ENDERECO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, enderecoId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o endereço: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<EnderecoTO> findByUsuarioId(long usuarioId) {
        ArrayList<EnderecoTO> enderecos = new ArrayList<>();
        String sql = """
        SELECT ID_ENDERECO, NR_CEP, NR_ENDERECO, NM_BAIRRO, NM_ESTADO, NM_RUA, DS_COMPLEMENTO, OFIX_USUARIO_ID_USUARIO
        FROM OFIX_ENDERECO
        WHERE OFIX_USUARIO_ID_USUARIO = ?
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EnderecoTO endereco = new EnderecoTO();
                    endereco.setId(rs.getLong("ID_ENDERECO"));
                    endereco.setCep(rs.getInt("NR_CEP"));
                    endereco.setNumero(rs.getInt("NR_ENDERECO"));
                    endereco.setBairro(rs.getString("NM_BAIRRO"));
                    endereco.setEstado(rs.getString("NM_ESTADO"));
                    endereco.setRua(rs.getString("NM_RUA"));
                    endereco.setComplemento(rs.getString("DS_COMPLEMENTO"));
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(rs.getLong("OFIX_USUARIO_ID_USUARIO"));
                    endereco.setUsuario(usuario);
                    enderecos.add(endereco);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar endereços por ID de usuário: " + e.getMessage());
        }

        return enderecos;
    }

}

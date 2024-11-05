package br.com.fiap.dao;

import br.com.fiap.to.EnderecoTO;
import br.com.fiap.to.TelefoneTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Repository {

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_USUARIO";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                usuario.setId(rs.getLong("ID_USUARIO"));
                usuario.setNome(rs.getString("NM_USUARIO"));
                usuario.setTipoConta(rs.getString("FL_CONTA"));
                usuario.setEmail(rs.getString("NM_EMAIL"));
                usuario.setSenha(rs.getString("NM_SENHA"));
                usuario.setFoto(rs.getString("IMG_FOTO"));
                usuario.setToken(rs.getString("DS_TOKEM"));
                usuario.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO OFIX_USUARIO (NM_USUARIO, FL_CONTA, NM_EMAIL, NM_SENHA, IMG_FOTO, DS_TOKEM, DT_CADASTRO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_USUARIO"})) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTipoConta());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getFoto());
            ps.setString(6, usuario.getToken());
            ps.setTimestamp(7, usuario.getDataDeCadastro());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o usuário: " + e.getMessage());
        }
        return usuario;
    }


    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE OFIX_USUARIO SET NM_USUARIO = ?, FL_CONTA = ?, NM_EMAIL = ?, NM_SENHA = ?, IMG_FOTO = ?, DS_TOKEM = ?, DT_CADASTRO = ? WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTipoConta());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getFoto());
            ps.setString(6, usuario.getToken());
            ps.setObject(7, usuario.getDataDeCadastro());
            ps.setLong(8, usuario.getId());

            return usuario;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuário: " + e.getMessage());
            return null;
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM OFIX_USUARIO WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o usuário: " + e.getMessage());
            return false;
        }
    }

    public UsuarioTO findById(Long id) {
        UsuarioTO usuario = null;
        String sql = "SELECT ID_USUARIO, NM_USUARIO, FL_CONTA, NM_EMAIL, NM_SENHA, IMG_FOTO, DS_TOKEM, DT_CADASTRO FROM OFIX_USUARIO WHERE ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioTO();
                usuario.setId(rs.getLong("ID_USUARIO"));
                usuario.setNome(rs.getString("NM_USUARIO"));
                usuario.setTipoConta(rs.getString("FL_CONTA"));
                usuario.setEmail(rs.getString("NM_EMAIL"));
                usuario.setSenha(rs.getString("NM_SENHA"));
                usuario.setFoto(rs.getString("IMG_FOTO"));
                usuario.setToken(rs.getString("DS_TOKEM"));
                usuario.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o usuário: " + e.getMessage());
        }

        return usuario;
    }

    public List<EnderecoTO> findEnderecosByUsuarioId(Long usuarioId) {
        List<EnderecoTO> enderecos = new ArrayList<>();
        String sql = "SELECT e.ID_ENDERECO, e.NR_CEP, e.NR_ENDERECO, e.NM_BAIRRO, e.NM_ESTADO, e.NM_RUA, e.DS_COMPLEMENTO " +
                "FROM OFIX_ENDERECO e WHERE e.OFIX_USUARIO_ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EnderecoTO endereco = new EnderecoTO();
                endereco.setId(rs.getLong("ID_ENDERECO"));
                endereco.setCep(rs.getInt("NR_CEP"));
                endereco.setNumero(rs.getInt("NR_ENDERECO"));
                endereco.setBairro(rs.getString("NM_BAIRRO"));
                endereco.setEstado(rs.getString("NM_ESTADO"));
                endereco.setRua(rs.getString("NM_RUA"));
                endereco.setComplemento(rs.getString("DS_COMPLEMENTO"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar endereços: " + e.getMessage());
        }

        return enderecos;
    }

    public List<TelefoneTO> findTelefonesByUsuarioId(Long usuarioId) {
        List<TelefoneTO> telefones = new ArrayList<>();
        String sql = "SELECT ID_TELEFONE, NR_TELEFONE, NR_DDD, NR_DDI, NM_LEMBRETE FROM OFIX_TELEFONE WHERE OFIX_USUARIO_ID_USUARIO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TelefoneTO telefone = new TelefoneTO();
                telefone.setId(rs.getLong("ID_TELEFONE"));
                telefone.setNumero(rs.getInt("NR_TELEFONE"));
                telefone.setDDD(rs.getInt("NR_DDD"));
                telefone.setDDI(rs.getInt("NR_DDI"));
                telefone.setLembrete(rs.getString("NM_LEMBRETE"));
                telefones.add(telefone);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar telefones: " + e.getMessage());
        }

        return telefones;
    }
}

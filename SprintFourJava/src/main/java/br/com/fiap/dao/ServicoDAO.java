package br.com.fiap.dao;

import br.com.fiap.to.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServicoDAO extends Repository {

    private final OficinaDAO oficinaDAO;
    private final UsuarioDAO usuarioDAO;

    public ServicoDAO() {
        this.oficinaDAO = new OficinaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public ArrayList<ServicoTO> findAll() {
        ArrayList<ServicoTO> servicos = new ArrayList<>();
        String sql = "SELECT ID_SERVICO, BL_DESCONTO, NR_DESCONTO, NR_PRECOMEDIA, NR_INICIOPRECO, NR_FIMPRECO, " +
                "DT_CADASTRO, BL_GRATUIDADE, DS_SERVICO, NM_SERVICO, NR_TEMPOREPARO, OFIX_OFICINA_ID_OFICINA " +
                "FROM OFIX_SERVICO ORDER BY ID_SERVICO";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ServicoTO servico = new ServicoTO();
                servico.setId(rs.getLong("ID_SERVICO"));
                servico.setDescontoAtivado("1".equalsIgnoreCase(rs.getString("BL_DESCONTO")));
                servico.setDescontoEmUnidades(rs.getDouble("NR_DESCONTO"));
                servico.setPrecoMedia(rs.getDouble("NR_PRECOMEDIA"));
                servico.setInicioFaixaDePreco(rs.getDouble("NR_INICIOPRECO"));
                servico.setFimFaixaDePreco(rs.getDouble("NR_FIMPRECO"));
                servico.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                servico.setGratuidade("1".equalsIgnoreCase(rs.getString("BL_GRATUIDADE")));
                servico.setDescricao(rs.getString("DS_SERVICO"));
                servico.setNome(rs.getString("NM_SERVICO"));
                servico.setTempoDeReparo(rs.getInt("NR_TEMPOREPARO"));

                OficinaTO oficina = oficinaDAO.findById(rs.getLong("OFIX_OFICINA_ID_OFICINA"));
                servico.setOficina(oficina);

                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return servicos;
    }

    public ServicoTO findById(Long id) {
        ServicoTO servico = null;
        String sql = "SELECT ID_SERVICO, BL_DESCONTO, NR_DESCONTO, NR_PRECOMEDIA, NR_INICIOPRECO, NR_FIMPRECO, " +
                "DT_CADASTRO, BL_GRATUIDADE, DS_SERVICO, NM_SERVICO, NR_TEMPOREPARO, OFIX_OFICINA_ID_OFICINA " +
                "FROM OFIX_SERVICO WHERE ID_SERVICO = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                servico = new ServicoTO();
                servico.setId(rs.getLong("ID_SERVICO"));
                servico.setDescontoAtivado("1".equalsIgnoreCase(rs.getString("BL_DESCONTO")));
                servico.setDescontoEmUnidades(rs.getDouble("NR_DESCONTO"));
                servico.setPrecoMedia(rs.getDouble("NR_PRECOMEDIA"));
                servico.setInicioFaixaDePreco(rs.getDouble("NR_INICIOPRECO"));
                servico.setFimFaixaDePreco(rs.getDouble("NR_FIMPRECO"));
                servico.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                servico.setGratuidade("1".equalsIgnoreCase(rs.getString("BL_GRATUIDADE")));
                servico.setDescricao(rs.getString("DS_SERVICO"));
                servico.setNome(rs.getString("NM_SERVICO"));
                servico.setTempoDeReparo(rs.getInt("NR_TEMPOREPARO"));

                OficinaTO oficina = oficinaDAO.findById(rs.getLong("OFIX_OFICINA_ID_OFICINA"));
                servico.setOficina(oficina);

                if (oficina != null) {
                    UsuarioTO usuario = usuarioDAO.findById(oficina.getUsuario().getId());
                    oficina.setUsuario(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return servico;
    }

    public ServicoTO save(ServicoTO servico) {
        String sql = "INSERT INTO OFIX_SERVICO (BL_DESCONTO, NR_DESCONTO, NR_PRECOMEDIA, NR_INICIOPRECO, NR_FIMPRECO, " +
                "DT_CADASTRO, BL_GRATUIDADE, DS_SERVICO, NM_SERVICO, NR_TEMPOREPARO, OFIX_OFICINA_ID_OFICINA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, servico.isDescontoAtivado());
            ps.setDouble(2, servico.getDescontoEmUnidades());
            ps.setDouble(3, servico.getPrecoMedia());
            ps.setDouble(4, servico.getInicioFaixaDePreco());
            ps.setDouble(5, servico.getFimFaixaDePreco());
            ps.setObject(6, servico.getDataDeCadastro());
            ps.setBoolean(7, servico.isGratuidade());
            ps.setString(8, servico.getDescricao());
            ps.setString(9, servico.getNome());
            ps.setInt(10, servico.getTempoDeReparo());
            ps.setLong(11, servico.getOficina().getId());

            if (ps.executeUpdate() > 0) {
                return servico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM OFIX_SERVICO WHERE ID_SERVICO = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ServicoTO update(ServicoTO servico) {
        String sql = "UPDATE OFIX_SERVICO SET BL_DESCONTO = ?, NR_DESCONTO = ?, NR_PRECOMEDIA = ?, NR_INICIOPRECO = ?, " +
                "NR_FIMPRECO = ?, DT_CADASTRO = ?, BL_GRATUIDADE = ?, DS_SERVICO = ?, NM_SERVICO = ?, NR_TEMPOREPARO = ? " +
                "WHERE ID_SERVICO = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, servico.isDescontoAtivado());
            ps.setDouble(2, servico.getDescontoEmUnidades());
            ps.setDouble(3, servico.getPrecoMedia());
            ps.setDouble(4, servico.getInicioFaixaDePreco());
            ps.setDouble(5, servico.getFimFaixaDePreco());
            ps.setObject(6, servico.getDataDeCadastro());
            ps.setBoolean(7, servico.isGratuidade());
            ps.setString(8, servico.getDescricao());
            ps.setString(9, servico.getNome());
            ps.setInt(10, servico.getTempoDeReparo());
            ps.setLong(11, servico.getId());
            if (ps.executeUpdate() > 0) {
                return servico;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public ArrayList<ServicoTO> findByOficinaId(Long oficinaId) {
        ArrayList<ServicoTO> servicos = new ArrayList<>();
        String sql = "SELECT ID_SERVICO, BL_DESCONTO, NR_DESCONTO, NR_PRECOMEDIA, NR_INICIOPRECO, NR_FIMPRECO, " +
                "DT_CADASTRO, BL_GRATUIDADE, DS_SERVICO, NM_SERVICO, NR_TEMPOREPARO " +
                "FROM OFIX_SERVICO WHERE OFIX_OFICINA_ID_OFICINA = ? ORDER BY ID_SERVICO";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, oficinaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ServicoTO servico = new ServicoTO();
                servico.setId(rs.getLong("ID_SERVICO"));
                servico.setDescontoAtivado("1".equalsIgnoreCase(rs.getString("BL_DESCONTO")));
                servico.setDescontoEmUnidades(rs.getDouble("NR_DESCONTO"));
                servico.setPrecoMedia(rs.getDouble("NR_PRECOMEDIA"));
                servico.setInicioFaixaDePreco(rs.getDouble("NR_INICIOPRECO"));
                servico.setFimFaixaDePreco(rs.getDouble("NR_FIMPRECO"));
                servico.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                servico.setGratuidade("1".equalsIgnoreCase(rs.getString("BL_GRATUIDADE")));
                servico.setDescricao(rs.getString("DS_SERVICO"));
                servico.setNome(rs.getString("NM_SERVICO"));
                servico.setTempoDeReparo(rs.getInt("NR_TEMPOREPARO"));

                OficinaTO oficina = oficinaDAO.findById(oficinaId);
                servico.setOficina(oficina);

                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviços por ID da oficina: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return servicos;
    }

}

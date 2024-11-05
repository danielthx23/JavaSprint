package br.com.fiap.dao;

import br.com.fiap.to.AgendaTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AgendaDAO extends Repository {

    public AgendaTO save(AgendaTO agendamento) {
        String sql = "INSERT INTO OFIX_AGENDAMENTO (DT_INICIO, DT_ESTTERM, DT_TERMINO, DT_CADASTRO, FL_STATUS, NR_PRECOEST, NR_DESCONTOS, NR_PRECO_FINAL, DS_CANCELAMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_AGENDA"})) {

            ps.setObject(1, agendamento.getDataInicio());
            ps.setObject(2, agendamento.getDataEstimadaDeTermino());
            ps.setObject(3, agendamento.getDataDeTermino());
            ps.setObject(4, agendamento.getDataDeCadastro());
            ps.setString(5, agendamento.getStatus());
            ps.setDouble(6, agendamento.getPrecoEstimado());
            ps.setDouble(7, agendamento.getDescontos());
            ps.setDouble(8, agendamento.getPrecoFinal());
            ps.setString(9, agendamento.getRazaoDeCancelamento());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agendamento.setId(generatedKeys.getLong(1));
                }
            }

            return agendamento;
        } catch (SQLException e) {
            System.out.println("Erro ao criar agendamento: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<AgendaTO> findAll() {
        ArrayList<AgendaTO> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM OFIX_AGENDAMENTO";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AgendaTO agendamento = new AgendaTO();
                agendamento.setId(rs.getLong("ID_AGENDA"));
                agendamento.setDataInicio(rs.getTimestamp("DT_INICIO"));
                agendamento.setDataEstimadaDeTermino(rs.getTimestamp("DT_ESTTERM"));
                agendamento.setDataDeTermino(rs.getTimestamp("DT_TERMINO"));
                agendamento.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                agendamento.setStatus(rs.getString("FL_STATUS"));
                agendamento.setPrecoEstimado(rs.getDouble("NR_PRECOEST"));
                agendamento.setDescontos(rs.getDouble("NR_DESCONTOS"));
                agendamento.setPrecoFinal(rs.getDouble("NR_PRECO_FINAL"));
                agendamento.setRazaoDeCancelamento(rs.getString("DS_CANCELAMENTO"));

                agendamentos.add(agendamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar agendamentos: " + e.getMessage());
        }

        return agendamentos;
    }

    public void update(AgendaTO agendamento) {
        String sql = "UPDATE OFIX_AGENDAMENTO SET DT_INICIO = ?, DT_ESTTERM = ?, DT_TERMINO = ?, DT_CADASTRO = ?, FL_STATUS = ?, NR_PRECOEST = ?, NR_DESCONTOS = ?, NR_PRECO_FINAL = ?, DS_CANCELAMENTO = ? WHERE ID_AGENDA = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, agendamento.getDataInicio());
            ps.setObject(2, agendamento.getDataEstimadaDeTermino());
            ps.setObject(3, agendamento.getDataDeTermino());
            ps.setObject(4, agendamento.getDataDeCadastro());
            ps.setString(5, agendamento.getStatus());
            ps.setDouble(6, agendamento.getPrecoEstimado());
            ps.setDouble(7, agendamento.getDescontos());
            ps.setDouble(8, agendamento.getPrecoFinal());
            ps.setString(9, agendamento.getRazaoDeCancelamento());
            ps.setLong(10, agendamento.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar agendamento: " + e.getMessage());
        }
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM OFIX_AGENDAMENTO WHERE ID_AGENDA = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar agendamento: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<AgendaTO> findByClienteId(Long clienteId) {
        ArrayList<AgendaTO> agendas = new ArrayList<>();
        String sql = "SELECT a.* FROM OFIX_AGENDAMENTO a " +
                "JOIN OFIX_DIAG d ON a.ID_AGENDA = d.OFIX_AGENDAMENTO_ID_AGENDA " +
                "JOIN OFIX_VEICULO v ON d.OFIX_VEICULO_ID_VEICULO = v.ID_VEICULO " +
                "JOIN OFIX_CLIENTE c ON v.OFIX_CLIENTE_ID_CLIENTE = c.ID_CLIENTE " +
                "WHERE c.ID_CLIENTE = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AgendaTO agendamento = new AgendaTO();
                agendamento.setId(rs.getLong("ID_AGENDA"));
                agendamento.setDataInicio(rs.getTimestamp("DT_INICIO"));
                agendamento.setDataEstimadaDeTermino(rs.getTimestamp("DT_ESTTERM"));
                agendamento.setDataDeTermino(rs.getTimestamp("DT_TERMINO"));
                agendamento.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                agendamento.setStatus(rs.getString("FL_STATUS"));
                agendamento.setPrecoEstimado(rs.getDouble("NR_PRECOEST"));
                agendamento.setDescontos(rs.getDouble("NR_DESCONTOS"));
                agendamento.setPrecoFinal(rs.getDouble("NR_PRECO_FINAL"));
                agendamento.setRazaoDeCancelamento(rs.getString("DS_CANCELAMENTO"));

                agendas.add(agendamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamentos por clienteId: " + e.getMessage());
        }

        return agendas;
    }

    public ArrayList<AgendaTO> findByOficinaId(Long oficinaId) {
        ArrayList<AgendaTO> agendas = new ArrayList<>();
        String sql = "SELECT a.* FROM OFIX_AGENDAMENTO a " +
                "JOIN OFIX_SA sa ON a.ID_AGENDA = sa.OFIX_AGENDAMENTO_ID_AGENDA " +
                "JOIN OFIX_SERVICO s ON sa.OFIX_SERVICO_ID_SERVICO = s.ID_SERVICO " +
                "JOIN OFIX_OFICINA o ON s.OFIX_OFICINA_ID_OFICINA = o.ID_OFICINA " +
                "WHERE o.ID_OFICINA = ? " +
                "UNION " +
                "SELECT a.* FROM OFIX_AGENDAMENTO a " +
                "JOIN OFIX_SP sp ON a.ID_AGENDA = sp.OFIX_AGENDAMENTO_ID_AGENDA " +
                "JOIN OFIX_SERVICO s ON sp.OFIX_SERVICO_ID_SERVICO = s.ID_SERVICO " +
                "JOIN OFIX_OFICINA o ON s.OFIX_OFICINA_ID_OFICINA = o.ID_OFICINA " +
                "WHERE o.ID_OFICINA = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, oficinaId);
            ps.setLong(2, oficinaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AgendaTO agendamento = new AgendaTO();
                agendamento.setId(rs.getLong("ID_AGENDA"));
                agendamento.setDataInicio(rs.getTimestamp("DT_INICIO"));
                agendamento.setDataEstimadaDeTermino(rs.getTimestamp("DT_ESTTERM"));
                agendamento.setDataDeTermino(rs.getTimestamp("DT_TERMINO"));
                agendamento.setDataDeCadastro(rs.getTimestamp("DT_CADASTRO"));
                agendamento.setStatus(rs.getString("FL_STATUS"));
                agendamento.setPrecoEstimado(rs.getDouble("NR_PRECOEST"));
                agendamento.setDescontos(rs.getDouble("NR_DESCONTOS"));
                agendamento.setPrecoFinal(rs.getDouble("NR_PRECO_FINAL"));
                agendamento.setRazaoDeCancelamento(rs.getString("DS_CANCELAMENTO"));

                agendas.add(agendamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamentos por oficinaId: " + e.getMessage());
        }

        return agendas;
    }

}

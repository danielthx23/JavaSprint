package br.com.fiap.dao;

import br.com.fiap.to.AgendaTO;
import br.com.fiap.to.DiagnosticoTO;
import br.com.fiap.to.VeiculoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAO extends Repository {

    public ArrayList<DiagnosticoTO> findAll() {
        ArrayList<DiagnosticoTO> diagnosticos = new ArrayList<>();
        String sql = "SELECT ID_DIAG, DS_PROBLEMA, DS_PI, NR_CUSTOESP, NR_TEMPOESP, OFIX_VEICULO_ID_VEICULO, OFIX_AGENDAMENTO_ID_AGENDA FROM OFIX_DIAG";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DiagnosticoTO diagnostico = new DiagnosticoTO();
                diagnostico.setId(rs.getLong("ID_DIAG"));
                diagnostico.setDescricaoProblema(rs.getString("DS_PROBLEMA"));
                diagnostico.setProblemaIdentificado(rs.getString("DS_PI"));
                diagnostico.setCustoEstimavativa(rs.getDouble("NR_CUSTOESP"));
                diagnostico.setTempoEstimativa(rs.getInt("NR_TEMPOESP"));
                diagnostico.getVeiculo().setId(rs.getLong("OFIX_VEICULO_ID_VEICULO"));
                diagnostico.getAgenda().setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar diagnósticos: " + e.getMessage());
        }

        return diagnosticos;
    }

    public ArrayList<DiagnosticoTO> findByAgendamentoId(Long agendamentoId) {
        ArrayList<DiagnosticoTO> diagnosticos = new ArrayList<>();
        String sql = "SELECT ID_DIAG, DS_PROBLEMA, DS_PI, NR_CUSTOESP, NR_TEMPOESP, OFIX_VEICULO_ID_VEICULO FROM OFIX_DIAG WHERE OFIX_AGENDAMENTO_ID_AGENDA = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, agendamentoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DiagnosticoTO diagnostico = new DiagnosticoTO();

                diagnostico.setId(rs.getLong("ID_DIAG"));
                diagnostico.setDescricaoProblema(rs.getString("DS_PROBLEMA"));
                diagnostico.setProblemaIdentificado(rs.getString("DS_PI"));
                diagnostico.setCustoEstimavativa(rs.getDouble("NR_CUSTOESP"));
                diagnostico.setTempoEstimativa(rs.getInt("NR_TEMPOESP"));

                if (diagnostico.getVeiculo() == null) {
                    diagnostico.setVeiculo(new VeiculoTO());
                }
                if (diagnostico.getAgenda() == null) {
                    diagnostico.setAgenda(new AgendaTO());
                }

                // Setting IDs for nested objects
                diagnostico.getVeiculo().setId(rs.getLong("OFIX_VEICULO_ID_VEICULO"));
                diagnostico.getAgenda().setId(agendamentoId); // Setting Agendamento ID

                diagnosticos.add(diagnostico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar diagnósticos por agendamento ID: " + e.getMessage());
        }

        return diagnosticos;
    }

    public DiagnosticoTO save(DiagnosticoTO diagnostico) {
        String sql = "INSERT INTO OFIX_DIAG (DS_PROBLEMA, DS_PI, NR_CUSTOESP, NR_TEMPOESP, OFIX_VEICULO_ID_VEICULO, OFIX_AGENDAMENTO_ID_AGENDA) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_DIAG"})) {
            ps.setString(1, diagnostico.getDescricaoProblema());
            ps.setString(2, diagnostico.getProblemaIdentificado());
            ps.setDouble(3, diagnostico.getCustoEstimavativa());
            ps.setInt(4, diagnostico.getTempoEstimativa());
            ps.setLong(5, diagnostico.getVeiculo().getId());
            ps.setLong(6, diagnostico.getAgenda().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long diagnosticoId = rs.getLong(1);
                    diagnostico.setId(diagnosticoId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o diagnóstico: " + e.getMessage());
        }

        return diagnostico;
    }

    public DiagnosticoTO update(DiagnosticoTO diagnostico) {
        String sql = "UPDATE OFIX_DIAG SET DS_PROBLEMA = ?, DS_PI = ?, NR_CUSTOESP = ?, NR_TEMPOESP = ?, OFIX_VEICULO_ID_VEICULO = ?, OFIX_AGENDAMENTO_ID_AGENDA = ? WHERE ID_DIAG = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, diagnostico.getDescricaoProblema());
            ps.setString(2, diagnostico.getProblemaIdentificado());
            ps.setDouble(3, diagnostico.getCustoEstimavativa());
            ps.setInt(4, diagnostico.getTempoEstimativa());
            ps.setLong(5, diagnostico.getVeiculo().getId());
            ps.setLong(6, diagnostico.getAgenda().getId());
            ps.setLong(7, diagnostico.getId());

            return diagnostico;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o diagnóstico: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(long diagnosticoId) {
        String sql = "DELETE FROM OFIX_DIAG WHERE ID_DIAG = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, diagnosticoId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o diagnóstico: " + e.getMessage());
        }
        return false;
    }

    public DiagnosticoTO findById(long diagnosticoId) {
        DiagnosticoTO diagnostico = null;
        String sql = "SELECT ID_DIAG, DS_PROBLEMA, DS_PI, NR_CUSTOESP, NR_TEMPOESP, OFIX_VEICULO_ID_VEICULO, OFIX_AGENDAMENTO_ID_AGENDA FROM OFIX_DIAG WHERE ID_DIAG = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, diagnosticoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                diagnostico = new DiagnosticoTO();
                diagnostico.setId(rs.getLong("ID_DIAG"));
                diagnostico.setDescricaoProblema(rs.getString("DS_PROBLEMA"));
                diagnostico.setProblemaIdentificado(rs.getString("DS_PI"));
                diagnostico.setCustoEstimavativa(rs.getDouble("NR_CUSTOESP"));
                diagnostico.setTempoEstimativa(rs.getInt("NR_TEMPOESP"));

                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setId(rs.getLong("OFIX_VEICULO_ID_VEICULO"));
                diagnostico.setVeiculo(veiculo);

                AgendaTO agenda = new AgendaTO();
                agenda.setId(rs.getLong("OFIX_AGENDAMENTO_ID_AGENDA"));
                diagnostico.setAgenda(agenda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar diagnóstico por ID: " + e.getMessage());
        }

        return diagnostico;
    }

}

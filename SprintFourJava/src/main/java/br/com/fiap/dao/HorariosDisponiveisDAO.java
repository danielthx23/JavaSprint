package br.com.fiap.dao;

import br.com.fiap.to.HorarioDisponivelTO;
import br.com.fiap.to.OficinaTO; // Assuming OficinaTO is the class representing Oficina
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HorariosDisponiveisDAO extends Repository {

    public ArrayList<HorarioDisponivelTO> findAll() {
        ArrayList<HorarioDisponivelTO> horarios = new ArrayList<>();
        String sql = "SELECT ID_HD, HR_ABERTURA, BL_HORAAGENDADA, HR_FECHAMENTO, OFIX_OFICINA_ID_OFICINA FROM OFIX_HD";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HorarioDisponivelTO horario = new HorarioDisponivelTO();
                horario.setId(rs.getLong("ID_HD"));
                horario.setHorarioAbertura(rs.getTimestamp("HR_ABERTURA"));
                horario.setHorarioAgendado("1".equalsIgnoreCase(rs.getString("BL_HORAAGENDADA")));
                horario.setHorarioFechadura(rs.getTimestamp("HR_FECHAMENTO"));

                // Initialize OficinaTO if it is null before setting the ID
                if (horario.getOficina() == null) {
                    horario.setOficina(new OficinaTO()); // Initialize the OficinaTO
                }
                horario.getOficina().setId(rs.getLong("OFIX_OFICINA_ID_OFICINA"));
                horarios.add(horario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar horários disponíveis: " + e.getMessage());
        }

        return horarios;
    }

    public HorarioDisponivelTO findById(long id) {
        HorarioDisponivelTO horario = null;
        String sql = "SELECT ID_HD, HR_ABERTURA, BL_HORAAGENDADA, HR_FECHAMENTO, OFIX_OFICINA_ID_OFICINA FROM OFIX_HD WHERE ID_HD = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                horario = new HorarioDisponivelTO();
                horario.setId(rs.getLong("ID_HD"));
                horario.setHorarioAbertura(rs.getTimestamp("HR_ABERTURA"));
                horario.setHorarioAgendado("1".equalsIgnoreCase(rs.getString("BL_HORAAGENDADA")));
                horario.setHorarioFechadura(rs.getTimestamp("HR_FECHAMENTO"));

                if (horario.getOficina() == null) {
                    horario.setOficina(new OficinaTO());
                }
                horario.getOficina().setId(rs.getLong("OFIX_OFICINA_ID_OFICINA"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar horário disponível por ID: " + e.getMessage());
        }

        return horario;
    }

    public ArrayList<HorarioDisponivelTO> findByOficinaId(long oficinaId) {
        ArrayList<HorarioDisponivelTO> horarios = new ArrayList<>();
        String sql = """
        SELECT ID_HD, HR_ABERTURA, BL_HORAAGENDADA, HR_FECHAMENTO
        FROM OFIX_HD
        WHERE OFIX_OFICINA_ID_OFICINA = ?
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, oficinaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HorarioDisponivelTO horario = new HorarioDisponivelTO();
                horario.setId(rs.getLong("ID_HD"));
                horario.setHorarioAbertura(rs.getTimestamp("HR_ABERTURA"));
                horario.setHorarioAgendado("1".equalsIgnoreCase(rs.getString("BL_HORAAGENDADA")));
                horario.setHorarioFechadura(rs.getTimestamp("HR_FECHAMENTO"));

                if (horario.getOficina() == null) {
                    horario.setOficina(new OficinaTO());
                }
                horarios.add(horario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar horários disponíveis por ID da oficina: " + e.getMessage());
        }

        return horarios;
    }

    public HorarioDisponivelTO save(HorarioDisponivelTO horario) {
        String sql = "INSERT INTO OFIX_HD (HR_ABERTURA, BL_HORAAGENDADA, HR_FECHAMENTO, OFIX_OFICINA_ID_OFICINA) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql, new String[]{"ID_HD"})) {
            ps.setObject(1, horario.getHorarioAbertura());
            ps.setBoolean(2, horario.isHorarioAgendado());
            ps.setObject(3, horario.getHorarioFechadura());
            ps.setLong(4, horario.getOficina().getId());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    long horarioId = rs.getLong(1);
                    horario.setId(horarioId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o horário disponível: " + e.getMessage());
        }

        return horario;
    }

    public HorarioDisponivelTO update(HorarioDisponivelTO horario) {
        String sql = "UPDATE OFIX_HD SET HR_ABERTURA = ?, BL_HORAAGENDADA = ?, HR_FECHAMENTO = ?, OFIX_OFICINA_ID_OFICINA = ? WHERE ID_HD = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setObject(1, horario.getHorarioAbertura());
            ps.setBoolean(2, horario.isHorarioAgendado());
            ps.setObject(3, horario.getHorarioFechadura());
            ps.setLong(4, horario.getOficina().getId());
            ps.setLong(5, horario.getId());

            if (ps.executeUpdate() > 0) {
                return horario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o horário disponível: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(long horarioId) {
        String sql = "DELETE FROM OFIX_HD WHERE ID_HD = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, horarioId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o horário disponível: " + e.getMessage());
        }
        return false;
    }
}

package br.com.fiap.bo;

import br.com.fiap.dao.HorariosDisponiveisDAO;
import br.com.fiap.to.HorarioDisponivelTO;

import java.util.ArrayList;

public class HorariosDisponiveisBO {
    private HorariosDisponiveisDAO horariosDisponiveisDAO;

    public ArrayList<HorarioDisponivelTO> findAll() {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.findAll();
    }

    public HorarioDisponivelTO findById(long id) {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.findById(id);
    }

    public ArrayList<HorarioDisponivelTO> findByOficinaId(long oficinaId) {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.findByOficinaId(oficinaId);
    }

    public HorarioDisponivelTO save(HorarioDisponivelTO horariosDisponiveis) {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.save(horariosDisponiveis);
    }

    public HorarioDisponivelTO update(HorarioDisponivelTO horariosDisponiveis) {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.update(horariosDisponiveis);
    }

    public boolean delete(long id) {
        horariosDisponiveisDAO = new HorariosDisponiveisDAO();
        return horariosDisponiveisDAO.delete(id);
    }
}

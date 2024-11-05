package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HorarioDisponivelTO {

    private Long id;

    @NotNull(message = "O horário de abertura é obrigatório!")
    private Timestamp horarioAbertura;

    @NotNull(message = "O horário de fechamento é obrigatório!")
    private Timestamp horarioFechadura;

    @NotNull(message = "O atributo de horario agendado (boolean) é obrigatório!")
    private boolean horarioAgendado;

    private OficinaTO oficina;

    public HorarioDisponivelTO() {
    }

    public HorarioDisponivelTO(Long id, Timestamp horarioAbertura, Timestamp horarioFechadura, boolean horarioAgendado, OficinaTO oficina) {
        this.id = id;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechadura = horarioFechadura;
        this.horarioAgendado = horarioAgendado;
        this.oficina = oficina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O horário de abertura é obrigatório!") Timestamp getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(@NotNull(message = "O horário de abertura é obrigatório!") Timestamp horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public @NotNull(message = "O horário de fechamento é obrigatório!") Timestamp getHorarioFechadura() {
        return horarioFechadura;
    }

    public void setHorarioFechadura(@NotNull(message = "O horário de fechamento é obrigatório!") Timestamp horarioFechadura) {
        this.horarioFechadura = horarioFechadura;
    }

    @NotNull(message = "O atributo de horario agendado (boolean) é obrigatório!")
    public boolean isHorarioAgendado() {
        return horarioAgendado;
    }

    public void setHorarioAgendado(@NotNull(message = "O atributo de horario agendado (boolean) é obrigatório!") boolean horarioAgendado) {
        this.horarioAgendado = horarioAgendado;
    }

    public OficinaTO getOficina() {
        return oficina;
    }

    public void setOficina(OficinaTO oficina) {
        this.oficina = oficina;
    }
}

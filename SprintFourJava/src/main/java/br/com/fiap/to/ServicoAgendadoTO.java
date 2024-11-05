package br.com.fiap.to;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;
public class ServicoAgendadoTO {
    private Long id;
    @NotNull
    @PositiveOrZero
    private double precoTotal;
    @NotNull
    private Timestamp dataDeCadastro;
    private AgendaTO agendamento;
    private ServicoTO servico;

    public ServicoAgendadoTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @PositiveOrZero
    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(@NotNull @PositiveOrZero double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public @NotNull Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public AgendaTO getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendaTO agendamento) {
        this.agendamento = agendamento;
    }

    public ServicoTO getServico() {
        return servico;
    }

    public void setServico(ServicoTO servico) {
        this.servico = servico;
    }

    public ServicoAgendadoTO(Long id, double precoTotal, Timestamp dataDeCadastro, AgendaTO agendamento, ServicoTO servico) {
        this.id = id;
        this.precoTotal = precoTotal;
        this.dataDeCadastro = dataDeCadastro;
        this.agendamento = agendamento;
        this.servico = servico;
    }
}
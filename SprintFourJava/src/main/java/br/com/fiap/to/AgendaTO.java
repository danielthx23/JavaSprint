package br.com.fiap.to;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AgendaTO {

    private Long id;

    @NotNull(message = "O atributo de data de inicio é obrigatória!")
    @FutureOrPresent(message = "O atributo de data de inicio tem que ser no futuro ou presente")
    private Timestamp dataInicio;

    @NotNull(message = "O atributo de data de termino estimada é obrigatória!")
    @FutureOrPresent(message = "O atributo de data de termino estimada tem que ser no futuro ou presente")
    private Timestamp dataEstimadaDeTermino;

    @FutureOrPresent(message = "O atributo de data de termino tem que ser no futuro ou presente")
    private Timestamp dataDeTermino;

    @NotNull(message = "O atributo de data de casdastro é obrigatória!")
    @FutureOrPresent(message = "O atributo de data de cadastro tem que ser no futuro ou presente")
    private Timestamp dataDeCadastro;

    @NotBlank(message = "O atributo de status é obrigatório!")
    @Size(min = 1, max = 1, message = "O atributo de status deve contem apenas um caractere, Exemplos: 'P' de Pendente, 'F' de Finalizado e 'C' de Cancelado")
    private String status;

    @NotNull(message = "O atributo de preço estimado é obrigatório!")
    private double precoEstimado;

    @NotNull(message = "O atributo de descontos é obrigatório!")
    private double descontos;

    @NotNull(message = "O atributo de preço final é obrigatório!")
    private double precoFinal;

    @Size(min = 10, max = 1000, message = "O atributo da razão do cancelamento deve conter entre 10 a 1000 caracteres")
    private String razaoDeCancelamento;

    private List<DiagnosticoTO> diagnosticos;

    private List<ServicoAgendadoTO> ServicosAgendados;

    private List<ServicoProvisionadoTO> ServicosProvisionados;

    public AgendaTO() {
    }

    public AgendaTO(Long id, Timestamp dataInicio, Timestamp dataEstimadaDeTermino, Timestamp dataDeTermino, Timestamp dataDeCadastro, String status, double precoEstimado, double descontos, double precoFinal, String razaoDeCancelamento, List<DiagnosticoTO> diagnosticos, List<ServicoAgendadoTO> servicosAgendados, List<ServicoProvisionadoTO> servicosProvisionados) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataEstimadaDeTermino = dataEstimadaDeTermino;
        this.dataDeTermino = dataDeTermino;
        this.dataDeCadastro = dataDeCadastro;
        this.status = status;
        this.precoEstimado = precoEstimado;
        this.descontos = descontos;
        this.precoFinal = precoFinal;
        this.razaoDeCancelamento = razaoDeCancelamento;
        this.diagnosticos = diagnosticos;
        ServicosAgendados = servicosAgendados;
        ServicosProvisionados = servicosProvisionados;
    }

    public List<ServicoProvisionadoTO> getServicosProvisionados() {
        return ServicosProvisionados;
    }

    public void setServicosProvisionados(List<ServicoProvisionadoTO> servicosProvisionados) {
        ServicosProvisionados = servicosProvisionados;
    }

    public List<ServicoAgendadoTO> getServicosAgendados() {
        return ServicosAgendados;
    }

    public void setServicosAgendados(List<ServicoAgendadoTO> servicosAgendados) {
        ServicosAgendados = servicosAgendados;
    }

    public List<DiagnosticoTO> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<DiagnosticoTO> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public @Size(min = 10, max = 1000, message = "O atributo da razão do cancelamento deve conter entre 10 a 1000 caracteres") String getRazaoDeCancelamento() {
        return razaoDeCancelamento;
    }

    public void setRazaoDeCancelamento(@Size(min = 10, max = 1000, message = "O atributo da razão do cancelamento deve conter entre 10 a 1000 caracteres") String razaoDeCancelamento) {
        this.razaoDeCancelamento = razaoDeCancelamento;
    }

    @NotNull(message = "O atributo de preço final é obrigatório!")
    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(@NotNull(message = "O atributo de preço final é obrigatório!") double precoFinal) {
        this.precoFinal = precoFinal;
    }

    @NotNull(message = "O atributo de descontos é obrigatório!")
    public double getDescontos() {
        return descontos;
    }

    public void setDescontos(@NotNull(message = "O atributo de descontos é obrigatório!") double descontos) {
        this.descontos = descontos;
    }

    @NotNull(message = "O atributo de preço estimado é obrigatório!")
    public double getPrecoEstimado() {
        return precoEstimado;
    }

    public void setPrecoEstimado(@NotNull(message = "O atributo de preço estimado é obrigatório!") double precoEstimado) {
        this.precoEstimado = precoEstimado;
    }

    public @NotBlank(message = "O atributo de status é obrigatório!") @Size(min = 1, max = 1, message = "O atributo de status deve contem apenas um caractere, Exemplos: 'P' de Pendente, 'F' de Finalizado e 'C' de Cancelado") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "O atributo de status é obrigatório!") @Size(min = 1, max = 1, message = "O atributo de status deve contem apenas um caractere, Exemplos: 'P' de Pendente, 'F' de Finalizado e 'C' de Cancelado") String status) {
        this.status = status;
    }

    public @NotNull(message = "O atributo de data de casdastro é obrigatória!") @FutureOrPresent(message = "O atributo de data de cadastro tem que ser no futuro ou presente") Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull(message = "O atributo de data de casdastro é obrigatória!") @FutureOrPresent(message = "O atributo de data de cadastro tem que ser no futuro ou presente") Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public @FutureOrPresent(message = "O atributo de data de termino tem que ser no futuro ou presente") Timestamp getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDataDeTermino(@FutureOrPresent(message = "O atributo de data de termino tem que ser no futuro ou presente") Timestamp dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public @NotNull(message = "O atributo de data de termino estimada é obrigatória!") @FutureOrPresent(message = "O atributo de data de termino estimada tem que ser no futuro ou presente") Timestamp getDataEstimadaDeTermino() {
        return dataEstimadaDeTermino;
    }

    public void setDataEstimadaDeTermino(@NotNull(message = "O atributo de data de termino estimada é obrigatória!") @FutureOrPresent(message = "O atributo de data de termino estimada tem que ser no futuro ou presente") Timestamp dataEstimadaDeTermino) {
        this.dataEstimadaDeTermino = dataEstimadaDeTermino;
    }

    public @NotNull(message = "O atributo de data de inicio é obrigatória!") @FutureOrPresent(message = "O atributo de data de inicio tem que ser no futuro ou presente") Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(@NotNull(message = "O atributo de data de inicio é obrigatória!") @FutureOrPresent(message = "O atributo de data de inicio tem que ser no futuro ou presente") Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean matchesHorarioDisponivel(List<HorarioDisponivelTO> horariosDisponiveis) {
        if (dataInicio == null || horariosDisponiveis == null) {
            return false;
        }

        for (HorarioDisponivelTO horario : horariosDisponiveis) {
            if (dataInicio.equals(horario.getHorarioAbertura())) {
                return true;
            }
        }

        return false;
    }
}

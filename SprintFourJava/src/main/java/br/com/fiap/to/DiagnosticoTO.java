package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class DiagnosticoTO {

    private Long id;

    @NotBlank(message = "O atributo de descrição do problema é obrigatório!")
    @Size(min = 8, max = 500, message = "O atributo de descrição do problema deve conter entre 8 a 500 caracteres")
    private String descricaoProblema;

    @NotBlank(message = "O atributo de problema identificado é obrigatório!")
    @Size(min = 4, max = 100, message = "O atributo de problema identificado deve conter entre 4 a 100 caracteres")
    private String problemaIdentificado;

    @NotNull(message = "O atributo de estimativa de custo é obrigatório!")
    @PositiveOrZero(message = "O atributo de estimativa de custo deve ser positivo ou zero")
    private double custoEstimavativa;

    @NotNull(message = "O atributo de estimativa de tempo é obrigatório!")
    @PositiveOrZero(message = "O atributo de estimativa de tempo deve ser positivo ou zero")
    private int tempoEstimativa;

    private VeiculoTO veiculo;

    private AgendaTO agenda;

    public DiagnosticoTO() {
    }

    public DiagnosticoTO(Long id, String descricaoProblema, String problemaIdentificado, double custoEstimavativa, int tempoEstimativa, VeiculoTO veiculo, AgendaTO agenda) {
        this.id = id;
        this.descricaoProblema = descricaoProblema;
        this.problemaIdentificado = problemaIdentificado;
        this.custoEstimavativa = custoEstimavativa;
        this.tempoEstimativa = tempoEstimativa;
        this.veiculo = veiculo;
        this.agenda = agenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo de descrição do problema é obrigatório!") @Size(min = 8, max = 500, message = "O atributo de descrição do problema deve conter entre 8 a 500 caracteres") String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(@NotBlank(message = "O atributo de descrição do problema é obrigatório!") @Size(min = 8, max = 500, message = "O atributo de descrição do problema deve conter entre 8 a 500 caracteres") String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public @NotBlank(message = "O atributo de problema identificado é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de problema identificado deve conter entre 4 a 100 caracteres") String getProblemaIdentificado() {
        return problemaIdentificado;
    }

    public void setProblemaIdentificado(@NotBlank(message = "O atributo de problema identificado é obrigatório!") @Size(min = 4, max = 100, message = "O atributo de problema identificado deve conter entre 4 a 100 caracteres") String problemaIdentificado) {
        this.problemaIdentificado = problemaIdentificado;
    }

    @NotNull(message = "O atributo de estimativa de custo é obrigatório!")
    @PositiveOrZero(message = "O atributo de estimativa de custo deve ser positivo ou zero")
    public double getCustoEstimavativa() {
        return custoEstimavativa;
    }

    public void setCustoEstimavativa(@NotNull(message = "O atributo de estimativa de custo é obrigatório!") @PositiveOrZero(message = "O atributo de estimativa de custo deve ser positivo ou zero") double custoEstimavativa) {
        this.custoEstimavativa = custoEstimavativa;
    }

    @NotNull(message = "O atributo de estimativa de tempo é obrigatório!")
    @PositiveOrZero(message = "O atributo de estimativa de tempo deve ser positivo ou zero")
    public int getTempoEstimativa() {
        return tempoEstimativa;
    }

    public void setTempoEstimativa(@NotNull(message = "O atributo de estimativa de tempo é obrigatório!") @PositiveOrZero(message = "O atributo de estimativa de tempo deve ser positivo ou zero") int tempoEstimativa) {
        this.tempoEstimativa = tempoEstimativa;
    }

    public VeiculoTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoTO veiculo) {
        this.veiculo = veiculo;
    }

    public AgendaTO getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaTO agenda) {
        this.agenda = agenda;
    }
}

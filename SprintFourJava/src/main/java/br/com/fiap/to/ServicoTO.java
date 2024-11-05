package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ServicoTO {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 100)
    private String nome;

    @Size(min = 4, max = 500)
    private String descricao;

    @NotNull
    private boolean gratuidade;

    @PositiveOrZero
    private int descontoEmPorcentagem;

    @PositiveOrZero
    private double descontoEmUnidades;

    @NotNull
    private boolean descontoAtivado;

    @NotNull
    @PositiveOrZero
    private double precoMedia;

    @NotNull
    @PositiveOrZero
    private double inicioFaixaDePreco;

    @NotNull
    @PositiveOrZero
    private double fimFaixaDePreco;

    @NotNull
    @PositiveOrZero
    private int tempoDeReparo;

    @NotNull
    private Timestamp dataDeCadastro;

    private OficinaTO oficina;

    private List<ServicoAgendadoTO> ServicosAgendados;

    private List<ServicoProvisionadoTO> ServicosProvisionados;

    public ServicoTO() {
    }

    public ServicoTO(Long id, String nome, String descricao, boolean gratuidade, int descontoEmPorcentagem, double descontoEmUnidades, boolean descontoAtivado, double precoMedia, double inicioFaixaDePreco, double fimFaixaDePreco, int tempoDeReparo, Timestamp dataDeCadastro, OficinaTO oficina, List<ServicoAgendadoTO> servicosAgendados, List<ServicoProvisionadoTO> servicosProvisionados) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.gratuidade = gratuidade;
        this.descontoEmPorcentagem = descontoEmPorcentagem;
        this.descontoEmUnidades = descontoEmUnidades;
        this.descontoAtivado = descontoAtivado;
        this.precoMedia = precoMedia;
        this.inicioFaixaDePreco = inicioFaixaDePreco;
        this.fimFaixaDePreco = fimFaixaDePreco;
        this.tempoDeReparo = tempoDeReparo;
        this.dataDeCadastro = dataDeCadastro;
        this.oficina = oficina;
        ServicosAgendados = servicosAgendados;
        ServicosProvisionados = servicosProvisionados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 4, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 4, max = 100) String nome) {
        this.nome = nome;
    }

    public @Size(min = 4, max = 500) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(min = 4, max = 500) String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    public boolean isGratuidade() {
        return gratuidade;
    }

    public void setGratuidade(@NotNull boolean gratuidade) {
        this.gratuidade = gratuidade;
    }

    @PositiveOrZero
    public int getDescontoEmPorcentagem() {
        return descontoEmPorcentagem;
    }

    public void setDescontoEmPorcentagem(@PositiveOrZero int descontoEmPorcentagem) {
        this.descontoEmPorcentagem = descontoEmPorcentagem;
    }

    @PositiveOrZero
    public double getDescontoEmUnidades() {
        return descontoEmUnidades;
    }

    public void setDescontoEmUnidades(@PositiveOrZero double descontoEmUnidades) {
        this.descontoEmUnidades = descontoEmUnidades;
    }

    @NotNull
    public boolean isDescontoAtivado() {
        return descontoAtivado;
    }

    public void setDescontoAtivado(@NotNull boolean descontoAtivado) {
        this.descontoAtivado = descontoAtivado;
    }

    @NotNull
    @PositiveOrZero
    public double getPrecoMedia() {
        return precoMedia;
    }

    public void setPrecoMedia(@NotNull @PositiveOrZero double precoMedia) {
        this.precoMedia = precoMedia;
    }

    @NotNull
    @PositiveOrZero
    public double getInicioFaixaDePreco() {
        return inicioFaixaDePreco;
    }

    public void setInicioFaixaDePreco(@NotNull @PositiveOrZero double inicioFaixaDePreco) {
        this.inicioFaixaDePreco = inicioFaixaDePreco;
    }

    @NotNull
    @PositiveOrZero
    public double getFimFaixaDePreco() {
        return fimFaixaDePreco;
    }

    public void setFimFaixaDePreco(@NotNull @PositiveOrZero double fimFaixaDePreco) {
        this.fimFaixaDePreco = fimFaixaDePreco;
    }

    @NotNull
    @PositiveOrZero
    public int getTempoDeReparo() {
        return tempoDeReparo;
    }

    public void setTempoDeReparo(@NotNull @PositiveOrZero int tempoDeReparo) {
        this.tempoDeReparo = tempoDeReparo;
    }

    public @NotNull Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public OficinaTO getOficina() {
        return oficina;
    }

    public void setOficina(OficinaTO oficina) {
        this.oficina = oficina;
    }

    public List<ServicoAgendadoTO> getServicosAgendados() {
        return ServicosAgendados;
    }

    public void setServicosAgendados(List<ServicoAgendadoTO> servicosAgendados) {
        ServicosAgendados = servicosAgendados;
    }

    public List<ServicoProvisionadoTO> getServicosProvisionados() {
        return ServicosProvisionados;
    }

    public void setServicosProvisionados(List<ServicoProvisionadoTO> servicosProvisionados) {
        ServicosProvisionados = servicosProvisionados;
    }
}

package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class VeiculoTO {

    private Long id;

    @NotBlank(message = "O atributo de placa é obrigatório!")
    @Size(min = 4, max = 12, message = "O atributo de placa deve conter entre 4 a 12 caracteres")
    private String placa;

    @NotBlank(message = "O atributo de quilometragem é obrigatório!")
    @PositiveOrZero(message = "O atributo quilometragem não pode ser negativo!")
    private int quilometragem;

    @NotBlank(message = "O atributo de ano de fabricação é obrigatório!")
    @PastOrPresent(message = "O atributo de ano de fabricação não pode ser uma data no futuro!")
    private LocalDate anoDeFabricacao;

    private int alteracoes;

    @NotNull(message = "O atributo de marca e modelo é obrigatório!")
    private MarcaModeloTO marcaModelo;

    private ClienteTO cliente;

    private List<DiagnosticoTO> diagnosticos;

    public VeiculoTO() {
    }

    public VeiculoTO(Long id, String placa, int quilometragem, LocalDate anoDeFabricacao, int alteracoes, MarcaModeloTO marcaModelo, ClienteTO cliente, List<DiagnosticoTO> diagnosticos) {
        this.id = id;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.anoDeFabricacao = anoDeFabricacao;
        this.alteracoes = alteracoes;
        this.marcaModelo = marcaModelo;
        this.cliente = cliente;
        this.diagnosticos = diagnosticos;
    }

    public ClienteTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo de placa é obrigatório!") @Size(min = 4, max = 12, message = "O atributo de placa deve conter entre 4 a 12 caracteres") String getPlaca() {
        return placa;
    }

    public void setPlaca(@NotBlank(message = "O atributo de placa é obrigatório!") @Size(min = 4, max = 12, message = "O atributo de placa deve conter entre 4 a 12 caracteres") String placa) {
        this.placa = placa;
    }

    @NotBlank(message = "O atributo de quilometragem é obrigatório!")
    @PositiveOrZero(message = "O atributo quilometragem não pode ser negativo!")
    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(@NotBlank(message = "O atributo de quilometragem é obrigatório!") @PositiveOrZero(message = "O atributo quilometragem não pode ser negativo!") int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public @NotBlank(message = "O atributo de ano de fabricação é obrigatório!") @PastOrPresent(message = "O atributo de ano de fabricação não pode ser uma data no futuro!") LocalDate getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(@NotBlank(message = "O atributo de ano de fabricação é obrigatório!") @PastOrPresent(message = "O atributo de ano de fabricação não pode ser uma data no futuro!") LocalDate anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public @Size(min = 4, max = 1000, message = "O atributo de alterações deve conter entre 4 a 1000 caracteres") int getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(@Size(min = 4, max = 1000, message = "O atributo de alterações deve conter entre 4 a 1000 caracteres") int alteracoes) {
        this.alteracoes = alteracoes;
    }

    public @NotNull(message = "O atributo de marca e modelo é obrigatório!") MarcaModeloTO getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(@NotNull(message = "O atributo de marca e modelo é obrigatório!") MarcaModeloTO marcaModelo) {
        this.marcaModelo = marcaModelo;
    }

    public List<DiagnosticoTO> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<DiagnosticoTO> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }
}

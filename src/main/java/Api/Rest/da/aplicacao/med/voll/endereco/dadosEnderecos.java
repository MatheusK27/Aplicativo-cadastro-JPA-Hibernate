package Api.Rest.da.aplicacao.med.voll.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record dadosEnderecos(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @Pattern(regexp = "\\d{9}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,

        String complemento,

        String numero) {
}

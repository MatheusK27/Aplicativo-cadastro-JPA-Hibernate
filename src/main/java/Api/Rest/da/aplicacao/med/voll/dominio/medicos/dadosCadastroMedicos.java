package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

import Api.Rest.da.aplicacao.med.voll.dominio.endereco.dadosEnderecos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record dadosCadastroMedicos(
                                   @NotBlank
                                   String nome,

                                   @NotBlank
                                   @Email
                                   String email,
                                   @NotBlank
                                   @Pattern(regexp = "\\d{4,6}")
                                   String crm,
                                   @NotBlank
                                   String telefone,

                                   @NotNull
                                   Especialidades especialidades,
                                   @NotNull
                                   @Valid
                                   dadosEnderecos endereco) {
}

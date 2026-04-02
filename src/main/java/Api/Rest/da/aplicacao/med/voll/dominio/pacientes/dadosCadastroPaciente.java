package Api.Rest.da.aplicacao.med.voll.dominio.pacientes;
import Api.Rest.da.aplicacao.med.voll.dominio.endereco.dadosEnderecos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record dadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,

        @Valid
        dadosEnderecos endereco) {
}

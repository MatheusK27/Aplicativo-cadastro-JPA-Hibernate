package Api.Rest.da.aplicacao.med.voll.dominio.pacientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record dadosAtualizarPacientes(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email

) {


}

package Api.Rest.da.aplicacao.med.voll.medicos;

import Api.Rest.da.aplicacao.med.voll.endereco.dadosEnderecos;
import jakarta.validation.constraints.NotNull;

public record dadosAtualizarMedicos (
        @NotNull Long id,
        String nome,
        String telefone,
        dadosEnderecos endereco){


}

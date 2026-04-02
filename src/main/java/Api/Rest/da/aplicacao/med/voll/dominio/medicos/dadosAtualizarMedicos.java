package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

import Api.Rest.da.aplicacao.med.voll.dominio.endereco.dadosEnderecos;
import jakarta.validation.constraints.NotNull;

public record dadosAtualizarMedicos (
        @NotNull Long id,
        String nome,
        String telefone,
        dadosEnderecos endereco){


}

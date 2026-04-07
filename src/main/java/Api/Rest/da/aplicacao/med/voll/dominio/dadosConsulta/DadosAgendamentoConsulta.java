package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;

import Api.Rest.da.aplicacao.med.voll.dominio.medicos.Especialidades;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

         @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidades especialidades
) {
}

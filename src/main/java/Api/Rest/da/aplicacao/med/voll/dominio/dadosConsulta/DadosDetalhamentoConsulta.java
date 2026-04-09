package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta dados ) {
        this(dados.getId(),dados.getMedico().getId(),dados.getPaciente().getId(),dados.getData());
    }
}

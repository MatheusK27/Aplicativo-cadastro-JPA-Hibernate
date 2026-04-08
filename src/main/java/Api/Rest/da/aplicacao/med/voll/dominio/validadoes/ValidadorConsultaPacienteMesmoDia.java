package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;


import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.ConsultaRepository;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;

import java.time.LocalDateTime;

public class ValidadorConsultaPacienteMesmoDia {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var paciente= repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (paciente){
        throw new ValidacaoException("Paciente não pode ter 2 consultas diárias");
        }

    }

}

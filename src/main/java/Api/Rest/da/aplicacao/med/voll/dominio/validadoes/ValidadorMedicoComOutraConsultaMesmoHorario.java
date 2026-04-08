package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.ConsultaRepository;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaMesmoHorario {


    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medico= repository.existsMedicoIdAndData(dados.idMedico(), dados.data());
        if (medico ){
            throw new ValidacaoException("Médico não disponivél pra esse horáriO");
        }
    }
}

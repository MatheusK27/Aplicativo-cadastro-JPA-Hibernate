package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.MedicoRepository;

public class ValidadorMedicoInativo {

    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medico = repository.findAtivoById(dados.idMedico());
        if(!medico){
            throw  new ValidacaoException("Médico excluido do banco de dados");
        }
    }
}
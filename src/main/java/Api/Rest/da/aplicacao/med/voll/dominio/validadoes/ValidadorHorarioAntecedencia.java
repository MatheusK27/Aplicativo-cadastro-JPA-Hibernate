package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDate;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta= dados.data();

        var agora = LocalDate.now();
        var diferencaMin= Duration.between(agora,dataConsulta).toMinutes();

        if (diferencaMin<30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia");
        }


    }
}

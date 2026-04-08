package Api.Rest.da.aplicacao.med.voll.dominio.validadoes;

import Api.Rest.da.aplicacao.med.voll.dominio.ValidacaoException;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamento {


    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo= dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antes7= dataConsulta.getHour()<7;
        var depois18= dataConsulta.getHour()>=18;
        if(domingo||antes7||depois18){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }
}

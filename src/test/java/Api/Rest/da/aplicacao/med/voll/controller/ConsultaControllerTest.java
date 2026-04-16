package Api.Rest.da.aplicacao.med.voll.controller;


import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.AgendaDeConsultas;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosAgendamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosDetalhamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.Especialidades;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockitoBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informeçoes estão erradas")
    @WithMockUser
    void agendarCenario1() throws Exception {
      var response=  mvc.perform(post("/consultas"))
                .andReturn().getResponse();

      assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());


    }


    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informeçoes estão validas")
    @WithMockUser
    void agendarCenanerio2() throws Exception {

        var data= LocalDateTime.now().plusDays(1);
        var especialidade= Especialidades.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l,5l,data);

        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);
        var response=  mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson.write(
                                new DadosAgendamentoConsulta(2l, 5L, data, especialidade)
                        ).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado= dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
package test;

import Api.Rest.da.aplicacao.med.voll.controller.MedicoController;
import Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta.DadosDetalhamentoConsulta;
import Api.Rest.da.aplicacao.med.voll.dominio.endereco.Endereco;
import Api.Rest.da.aplicacao.med.voll.dominio.endereco.dadosEnderecos;
import Api.Rest.da.aplicacao.med.voll.dominio.medicos.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<dadosCadastroMedicos> json;

    @Autowired
    private JacksonTester<dadosDetalhamentoMedicos> jsonDet;

    @MockitoBean
    private MedicoRepository repository;



    @Test
    @DisplayName("Deveria retornar errp http 400 quando informações estão invalidas")
    void cadastrarCenario1() throws Exception {
        var response= mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar errp http 201 quando informações estão validas")
    @WithMockUser
    void cadastrarCenario2() throws Exception {
        var cadastro = new dadosCadastroMedicos("medico", "medico@hotmail.com",
                "61998706365","12334560", Especialidades.CARDIOLOGIA,dadosEndereco());

        when(repository.save(any())).thenReturn(new Medico(cadastro));

        var response= mvc.perform(post("/medicos").contentType(MediaType.APPLICATION_JSON)
                .content(json.write(cadastro).getJson())).andReturn().getResponse();

        var dadosDetalhamento= new dadosDetalhamentoMedicos(
                null,
                cadastro.nome(),
                cadastro.email(),
                cadastro.crm(),
                cadastro.telefone(),
                cadastro.especialidades()


                );
        var jsonEsperado= json.write(cadastro).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);



    }

    private dadosEnderecos dadosEndereco() {
        return new dadosEnderecos(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
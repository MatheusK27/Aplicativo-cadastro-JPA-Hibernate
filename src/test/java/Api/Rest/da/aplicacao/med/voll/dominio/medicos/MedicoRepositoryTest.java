package Api.Rest.da.aplicacao.med.voll.dominio.medicos;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;



import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.temporal.TemporalAdjusters;


import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null o único médico cadastrado não está disponivel na data")
    void escolherMedicoAleatoriLivreNaDataCenario1() {

        var proximaSegunda = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var medicoLivre=  repository.escolherMedicoAleatoriLivreNaData(Especialidades.CARDIOLOGIA, LocalDate.from(proximaSegunda));
        assertThat(medicoLivre).isNull();

    }




}
package Api.Rest.da.aplicacao.med.voll.dominio.dadosConsulta;


import Api.Rest.da.aplicacao.med.voll.dominio.medicos.Medico;
import Api.Rest.da.aplicacao.med.voll.dominio.pacientes.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;
}

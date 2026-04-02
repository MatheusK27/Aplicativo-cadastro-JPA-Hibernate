package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
   Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}

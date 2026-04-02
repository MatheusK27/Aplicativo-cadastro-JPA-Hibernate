package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

public record dadosListagemMedicos(Long id,String nome, String email, String crm, Especialidades especialidades) {

    public dadosListagemMedicos(Medico medico) {
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());

    }
}

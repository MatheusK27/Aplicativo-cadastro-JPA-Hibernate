package Api.Rest.da.aplicacao.med.voll.dominio.medicos;

public record dadosDetalhamentoMedicos(Long id, String nome,String email,String crm, String telefone,Especialidades especialidade) {
  public dadosDetalhamentoMedicos(Medico medico){
      this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getTelefone(), medico.getEspecialidade());
  }
}

package Api.Rest.da.aplicacao.med.voll.dominio.pacientes;

public record  dadosDetalhamentoPaciente( Long id,String nome,String telefone,String email,String cpf){

    public dadosDetalhamentoPaciente ( Paciente paciente ) {
        this(paciente.getId(),paciente.getNome(),paciente.getTelefone(),paciente.getEmail(),paciente.getCpf());
    }
}




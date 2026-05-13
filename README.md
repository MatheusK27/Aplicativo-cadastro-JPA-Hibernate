# 🏥 Sistema de Clínica Médica

Sistema backend para gerenciamento de clínica médica com controle de acesso,
cadastro de médicos, pacientes e agendamento de consultas.

---

## 🚀 Tecnologias

| Tecnologia | Descrição |
|---|---|
| Java | Linguagem principal |
| Spring Boot | Framework backend |
| JPA / Hibernate | Persistência de dados |
| MySQL | Banco de dados |
| Lombok | Redução de boilerplate |

---

## ✅ Funcionalidades

- 🔐 Login com perfil Admin e Usuário comum
- 👨‍⚕️ Cadastro de médicos
- 🧑 Cadastro de pacientes
- 👤 Cadastro de usuários
- 📅 Agendamento de consultas
- 📋 Detalhamento de consultas
- ❌ Cancelamento de consultas
- 📏 Regras de negócio aplicadas

---

## ⚙️ Como executar

### Clone o projeto
git clone https://github.com/MatheusK27/Aplicativo-cadastro-JPA-Hibernate

### Configure o banco
Crie um banco MySQL chamado `clinica_medica` e ajuste o `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/clinica_medica
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

### Execute
./mvnw spring-boot:run

---

## 👨‍💻 Autor
Matheus Klein

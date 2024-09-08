package Programa;

import java.sql.*;
public class SetupBanco {
	public static void main(String[] args) {
		String host = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "290600";

        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(host, user, password);

            st = con.createStatement();

            st.execute("DROP DATABASE IF EXISTS sistema");
            st.execute("CREATE DATABASE sistema");
            st.execute("USE sistema");

            System.out.println("Banco criado com sucesso!");

            st.execute(
                    "CREATE TABLE funcionario (id_fun INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nome_fun VARCHAR(255) NOT NULL, idade_fun INT NOT NULL DEFAULT '0', cpf_fun VARCHAR(11) NOT NULL UNIQUE, email_fun VARCHAR(255) NOT NULL DEFAULT 'Desconhecido', cargo_fun VARCHAR(255) NOT NULL DEFAULT 'Desconhecido', CONSTRAINT ck_cpf CHECK (LENGTH(cpf_fun) = 11 ));");
            
            st.execute ("CREATE TABLE user ("
                    + "iduser INT PRIMARY KEY AUTO_INCREMENT, "
                    + "nomeuser VARCHAR(255) UNIQUE NOT NULL, "
                    + "senhauser VARCHAR(255) NOT NULL, "
                    + "tipo VARCHAR(50) NOT NULL DEFAULT 'Normal'"
                    + ");");
            
        
                    String sqlInserts = "INSERT INTO funcionario (nome_fun, idade_fun, cpf_fun, email_fun, cargo_fun) VALUES "
                    + "('Ana Oliveira', 28, '12345678901', 'ana.oliveira@email.com', 'Analista de Sistemas'), "
                    + "('Carlos Silva', 35, '23456789012', 'carlos.silva@email.com', 'Gerente de Projetos'), "
                    + "('Juliana Costa', 30, '34567890123', 'juliana.costa@email.com', 'Desenvolvedora'), "
                    + "('Roberto Santos', 45, '45678901234', 'roberto.santos@email.com', 'Coordenador de TI'), "
                    + "('Fernanda Lima', 40, '56789012345', 'fernanda.lima@email.com', 'Analista de Marketing'), "
                    + "('Paulo Mendes', 32, '67890123456', 'paulo.mendes@email.com', 'Consultor de Vendas'), "
                    + "('Mariana Alves', 29, '78901234567', 'mariana.alves@email.com', 'Designer Gráfico'), "
                    + "('Lucas Pereira', 38, '89012345678', 'lucas.pereira@email.com', 'Engenheiro de Software'), "
                    + "('Tatiane Rodrigues', 31, '90123456789', 'tatiane.rodrigues@email.com', 'Assistente Administrativo'), "
                    + "('Pedro Souza', 26, '01234567890', 'pedro.souza@email.com', 'Estagiário de TI'), "
                    + "('Camila Martins', 27, '12345678910', 'camila.martins@email.com', 'Analista Financeiro'), "
                    + "('João Batista', 50, '23456789021', 'joao.batista@email.com', 'Diretor de Operações'), "
                    + "('Sofia Pires', 33, '34567890132', 'sofia.pires@email.com', 'Especialista em Recursos Humanos'), "
                    + "('Diego Castro', 37, '45678901243', 'diego.castro@email.com', 'Gerente de Vendas'), "
                    + "('Letícia Campos', 34, '56789012354', 'leticia.campos@email.com', 'Analista de Suporte'), "
                    + "('André Lima', 42, '67890123465', 'andre.lima@email.com', 'Programador'), "
                    + "('Eliane Rocha', 41, '78901234576', 'eliane.rocha@email.com', 'Coordenadora de Eventos'), "
                    + "('Marcos Azevedo', 29, '89012345687', 'marcos.azevedo@email.com', 'Desenvolvedor Web'), "
                    + "('Claudia Andrade', 36, '90123456798', 'claudia.andrade@email.com', 'Gestora de Projetos'), "
                    + "('Ricardo Costa', 39, '01234567891', 'ricardo.costa@email.com', 'Analista de Sistemas Pleno'), "
                    + "('Gabriela Santos', 32, '12345678902', 'gabriela.santos@email.com', 'Consultora de Marketing'), "
                    + "('Fernando Silva', 44, '23456789013', 'fernando.silva@email.com', 'Administrador de Banco de Dados');";
                    
                    
                st.execute(sqlInserts);
                System.out.println("Dados inseridos com sucesso!");
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if ((st != null) && (!st.isClosed())) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if ((con != null) && (!con.isClosed())) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

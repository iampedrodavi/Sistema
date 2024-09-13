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
                    "CREATE TABLE funcionario (id_fun INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nome_fun VARCHAR(255) NOT NULL, idade_fun INT NOT NULL DEFAULT '0', cpf_fun VARCHAR(11) NOT NULL UNIQUE, email_fun VARCHAR(255) NOT NULL DEFAULT 'Desconhecido', cargo_fun VARCHAR(255) NOT NULL DEFAULT 'Desconhecido',salario_fun INT NOT NULL DEFAULT '1502', CONSTRAINT ck_cpf CHECK (LENGTH(cpf_fun) = 11 ));");
            
            st.execute ("CREATE TABLE user ("
                    + "iduser INT PRIMARY KEY AUTO_INCREMENT, "
                    + "nomeuser VARCHAR(255) UNIQUE NOT NULL, "
                    + "senhauser VARCHAR(255) NOT NULL, "
                    + "tipo VARCHAR(50) NOT NULL DEFAULT 'Normal'"
                    + ");");
            st.execute("INSERT INTO user (nomeuser, senhauser, tipo) VALUES ('123', '123', 'Adm');");
        
                    String sqlInserts =
                    		"INSERT INTO funcionario (nome_fun, idade_fun, cpf_fun, email_fun, cargo_fun, salario_fun) VALUES\r\n"
                    		+ "('Ana Silva', 29, '12345678901', 'ana.silva@exemplo.com', 'Analista', 2000),\r\n"
                    		+ "('Bruno Costa', 34, '12345678902', 'bruno.costa@exemplo.com', 'Desenvolvedor', 2500),\r\n"
                    		+ "('Carlos Oliveira', 40, '12345678903', 'carlos.oliveira@exemplo.com', 'Gerente', 3000),\r\n"
                    		+ "('Daniela Santos', 27, '12345678904', 'daniela.santos@exemplo.com', 'Analista', 2200),\r\n"
                    		+ "('Eduardo Lima', 30, '12345678905', 'eduardo.lima@exemplo.com', 'Designer', 2100),\r\n"
                    		+ "('Fernanda Pereira', 32, '12345678906', 'fernanda.pereira@exemplo.com', 'Desenvolvedor', 2600),\r\n"
                    		+ "('Gabriel Almeida', 25, '12345678907', 'gabriel.almeida@exemplo.com', 'Estagiário', 1500),\r\n"
                    		+ "('Helena Ferreira', 28, '12345678908', 'helena.ferreira@exemplo.com', 'Analista', 2300),\r\n"
                    		+ "('Igor Martins', 36, '12345678909', 'igor.martins@exemplo.com', 'Gerente', 3200),\r\n"
                    		+ "('Juliana Souza', 31, '12345678910', 'juliana.souza@exemplo.com', 'Desenvolvedor', 2700),\r\n"
                    		+ "('Karina Rocha', 29, '12345678911', 'karina.rocha@exemplo.com', 'Designer', 2200),\r\n"
                    		+ "('Luiz Santos', 40, '12345678912', 'luiz.santos@exemplo.com', 'Analista', 2400),\r\n"
                    		+ "('Mariana Lima', 33, '12345678913', 'mariana.lima@exemplo.com', 'Desenvolvedor', 2500),\r\n"
                    		+ "('Nicolas Costa', 27, '12345678914', 'nicolas.costa@exemplo.com', 'Estagiário', 1600),\r\n"
                    		+ "('Olga Silva', 35, '12345678915', 'olga.silva@exemplo.com', 'Gerente', 3100),\r\n"
                    		+ "('Paula Santos', 30, '12345678916', 'paula.santos@exemplo.com', 'Designer', 2400),\r\n"
                    		+ "('Quiteria Rodrigues', 26, '12345678917', 'quiteria.rodrigues@exemplo.com', 'Analista', 2200),\r\n"
                    		+ "('Rafael Almeida', 28, '12345678918', 'rafael.almeida@exemplo.com', 'Desenvolvedor', 2600),\r\n"
                    		+ "('Sabrina Pereira', 31, '12345678919', 'sabrina.pereira@exemplo.com', 'Analista', 2500),\r\n"
                    		+ "('Tiago Martins', 32, '12345678920', 'tiago.martins@exemplo.com', 'Gerente', 3300),\r\n"
                    		+ "('Ursula Lima', 30, '12345678921', 'ursula.lima@exemplo.com', 'Designer', 2300),\r\n"
                    		+ "('Valter Souza', 33, '12345678922', 'valter.souza@exemplo.com', 'Desenvolvedor', 2400),\r\n"
                    		+ "('Wanda Ferreira', 27, '12345678923', 'wanda.ferreira@exemplo.com', 'Estagiário', 1550),\r\n"
                    		+ "('Xuxa Rocha', 34, '12345678924', 'xuxa.rocha@exemplo.com', 'Gerente', 3100),\r\n"
                    		+ "('Yara Costa', 28, '12345678925', 'yara.costa@exemplo.com', 'Designer', 2200),\r\n"
                    		+ "('Zé Almeida', 26, '12345678926', 'ze.almeida@exemplo.com', 'Analista', 2300),\r\n"
                    		+ "('Alice Silva', 31, '12345678927', 'alice.silva@exemplo.com', 'Desenvolvedor', 2700),\r\n"
                    		+ "('Bruno Oliveira', 29, '12345678928', 'bruno.oliveira@exemplo.com', 'Designer', 2400),\r\n"
                    		+ "('Carla Santos', 32, '12345678929', 'carla.santos@exemplo.com', 'Analista', 2500),\r\n"
                    		+ "('Diego Ferreira', 35, '12345678930', 'diego.ferreira@exemplo.com', 'Gerente', 3200),\r\n"
                    		+ "('Eva Lima', 30, '12345678931', 'eva.lima@exemplo.com', 'Desenvolvedor', 2600),\r\n"
                    		+ "('Felipe Rocha', 28, '12345678932', 'felipe.rocha@exemplo.com', 'Estagiário', 1500),\r\n"
                    		+ "('Gabriela Costa', 34, '12345678933', 'gabriela.costa@exemplo.com', 'Analista', 2300),\r\n"
                    		+ "('Henrique Silva', 26, '12345678934', 'henrique.silva@exemplo.com', 'Designer', 2200),\r\n"
                    		+ "('Isabela Pereira', 33, '12345678935', 'isabela.pereira@exemplo.com', 'Desenvolvedor', 2500),\r\n"
                    		+ "('João Almeida', 29, '12345678936', 'joao.almeida@exemplo.com', 'Gerente', 3100),\r\n"
                    		+ "('Karen Martins', 31, '12345678937', 'karen.martins@exemplo.com', 'Analista', 2400),\r\n"
                    		+ "('Lucas Souza', 27, '12345678938', 'lucas.souza@exemplo.com', 'Estagiário', 1600),\r\n"
                    		+ "('Marcio Ferreira', 35, '12345678939', 'marcio.ferreira@exemplo.com', 'Desenvolvedor', 2700),\r\n"
                    		+ "('Natália Lima', 30, '12345678940', 'natalia.lima@exemplo.com', 'Designer', 2300),\r\n"
                    		+ "('Otávio Costa', 28, '12345678941', 'otavio.costa@exemplo.com', 'Gerente', 3000),\r\n"
                    		+ "('Priscila Rocha', 32, '12345678942', 'priscila.rocha@exemplo.com', 'Analista', 2500),\r\n"
                    		+ "('Quincy Silva', 29, '12345678943', 'quincy.silva@exemplo.com', 'Desenvolvedor', 2600),\r\n"
                    		+ "('Roberta Pereira', 31, '12345678944', 'roberta.pereira@exemplo.com', 'Designer', 2400),\r\n"
                    		+ "('Samuel Almeida', 27, '12345678945', 'samuel.almeida@exemplo.com', 'Estagiário', 1550),\r\n"
                    		+ "('Tatiane Costa', 30, '12345678946', 'tatiane.costa@exemplo.com', 'Gerente', 3200),\r\n"
                    		+ "('Ulysses Rocha', 34, '12345678947', 'ulysses.rocha@exemplo.com', 'Analista', 2400),\r\n"
                    		+ "('Vanessa Lima', 26, '12345678948', 'vanessa.lima@exemplo.com', 'Desenvolvedor', 2500),\r\n"
                    		+ "('William Silva', 32, '12345678949', 'william.silva@exemplo.com', 'Designer', 2300),\r\n"
                    		+ "('Ximena Costa', 28, '12345678950', 'ximena.costa@exemplo.com', 'Estagiário', 1600);\r\n"
                    		+ "";
                    
                    
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

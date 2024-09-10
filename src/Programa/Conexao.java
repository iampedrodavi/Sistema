package Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Conexao {
	private String host;
    private String user;
    private String password;

    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public Conexao(String host, String user, String password){
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public void conectar() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(host, user, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (st != null && !st.isClosed()) {
                st.close();
            }
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Funcionario> buscarFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        conectar();
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM funcionario");

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFun(rs.getInt("id_fun"));
                funcionario.setNomeFun(rs.getString("nome_fun"));
                funcionario.setIdadeFun(rs.getInt("idade_fun"));
                funcionario.setCpfFun(rs.getString("cpf_fun"));
                funcionario.setEmailFun(rs.getString("email_fun"));
                funcionario.setCargoFun(rs.getString("cargo_fun"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        desconectar();
        return funcionarios;
    }

    public void inserirNovoFuncionario(Funcionario funcionario){
        conectar();
        try {
            pst = con.prepareStatement("INSERT INTO funcionario (nome_fun, idade_fun, cpf_fun, email_fun, cargo_fun) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, funcionario.getNomeFun());
            pst.setInt(2, funcionario.getIdadeFun());
            pst.setString(3, funcionario.getCpfFun());
            pst.setString(4, funcionario.getEmailFun());
            pst.setString(5, funcionario.getCargoFun());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        desconectar();
    }
    
    public boolean verificarCpfExistente(String cpf) {
        conectar(); 
        String sql = "SELECT COUNT(*) FROM funcionario WHERE cpf_fun = ?"; 
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, cpf);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();  
        }
        return false;
    }

    public boolean verificarUsuarioExistente(String nome) {
        conectar(); 
        String sql = "SELECT COUNT(*) FROM user WHERE nomeuser = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();  
        }
        return false;
    }
    
    public void inserirNovoUser(User user) {
        conectar();
        try {
            pst = con.prepareStatement("INSERT INTO user (nomeuser, senhauser, tipo) VALUES (?, ?, ?)");
            pst.setString(1, user.getNomeuser());
            pst.setString(2, user.getSenhauser());
            pst.setString(3, user.getTipo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public User buscarUser(String nomeuser, String senhauser) {
        conectar();
        User user = null;
        String sql = "SELECT * FROM sistema.user WHERE nomeuser = ? AND senhauser = ?";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeuser);
            pst.setString(2, senhauser);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                int iduser = rs.getInt("iduser");
                String tipo = rs.getString("tipo");
                user = new User(iduser, nomeuser, senhauser, tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return user;
    }

    
    public Funcionario buscarFuncionarioPorId(int id){
        Funcionario funcionario = null;
        conectar();
        try {
            pst = con.prepareStatement("SELECT * FROM funcionario WHERE id_fun = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFun(rs.getInt("id_fun"));
                funcionario.setNomeFun(rs.getString("nome_fun"));
                funcionario.setIdadeFun(rs.getInt("idade_fun"));
                funcionario.setCpfFun(rs.getString("cpf_fun"));
                funcionario.setEmailFun(rs.getString("email_fun"));
                funcionario.setCargoFun(rs.getString("cargo_fun"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        desconectar();
        return funcionario;
    }

    public void removerFuncionario(Funcionario funcionario){
        this.conectar();
        try {
            
            pst = con.prepareStatement("DELETE FROM funcionario WHERE id_fun = ?");
            pst.setInt(1, funcionario.getIdFun());
            pst.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                }
                this.desconectar();
                }
    
    public List<Funcionario> Aposentadoria() {
        this.conectar();
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            pst = con.prepareStatement("SELECT * FROM funcionario WHERE idade_fun >= 60;");
            rs = pst.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFun(rs.getInt("id_fun"));
                funcionario.setNomeFun(rs.getString("nome_fun"));
                funcionario.setIdadeFun(rs.getInt("idade_fun"));
                funcionario.setCpfFun(rs.getString("cpf_fun"));
                funcionario.setEmailFun(rs.getString("email_fun"));
                funcionario.setCargoFun(rs.getString("cargo_fun"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionários: " + e.getMessage());
        } finally {
            this.desconectar();
        }

        return funcionarios;
    }



    
    public void modificarFuncionario(Funcionario funcionario) {

        conectar();
        try {
            String atualizar = "UPDATE funcionario SET nome_fun = ?, idade_fun = ?, cpf_fun = ?, email_fun = ?, cargo_fun = ? WHERE id_fun = ?";
            pst = con.prepareStatement(atualizar);

            pst.setString(1, funcionario.getNomeFun());
            pst.setInt(2, funcionario.getIdadeFun());
            pst.setString(3, funcionario.getCpfFun());
            pst.setString(4, funcionario.getEmailFun());
            pst.setString(5, funcionario.getCargoFun());
            pst.setInt(6, funcionario.getIdFun());

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário foi encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o funcionário: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        List<Funcionario> funcionarios = new ArrayList<>();
        conectar();
        try {
            String sql = "SELECT * FROM funcionario WHERE nome_fun LIKE ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + nome + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFun(rs.getInt("id_fun"));
                funcionario.setNomeFun(rs.getString("nome_fun"));
                funcionario.setIdadeFun(rs.getInt("idade_fun"));
                funcionario.setCpfFun(rs.getString("cpf_fun"));
                funcionario.setEmailFun(rs.getString("email_fun"));
                funcionario.setCargoFun(rs.getString("cargo_fun"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        desconectar();
        return funcionarios;
    }

}
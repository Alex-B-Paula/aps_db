package aps.bd;
import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Drive do banco de dados nao encontrado: " + ex);
        }

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "12345");

        Statement statement = connection.createStatement();

        String sql = "select * from departamento";
        boolean resultado = statement.execute(sql);

        ResultSet resultSet = statement.getResultSet();

        while(resultSet.next()) {
            int id_departamento = resultSet.getInt("id_departamento");
            int id_gerente = resultSet.getInt("id_gerente");
            String nome = resultSet.getString("nome");
            String funcao = resultSet.getString("funcao");
            int sala = resultSet.getInt("sala");

            System.out.println("id_departamento: "+id_departamento+"| id_gerente: "+id_gerente+"| nome departamento: "+nome+"| função: "+funcao+"| sala: "+ sala);
        }

        sql = "select * from gerente g join departamento d on g.id_departamento = d.id_departamento";
        resultado = statement.execute(sql);
        resultSet = statement.getResultSet();

        while(resultSet.next()) {
            int id_gerente = resultSet.getInt("g.id_gerente");
            String nome_departamento = resultSet.getString("d.nome");
            String cpf = resultSet.getString("g.cpf");
            float salario = resultSet.getFloat("g.salario");
            String nome = resultSet.getString("g.nome");

            System.out.println("nome_departamento: "+nome_departamento+"| id_gerente: "+id_gerente+"| cpf: "+cpf+"| salario: "+salario+"| nome gerente: "+ nome);
        }

        sql = "select * from analista a join departamento d on d.id_departamento = a.id_departamento join gerente g on a.id_gerente = g.id_gerente";
        resultado = statement.execute(sql);
        resultSet = statement.getResultSet();

        while(resultSet.next()) {
            int id_analista = resultSet.getInt("id_analista");
            String nome_gerente = resultSet.getString("g.nome");
            String nome_departamento = resultSet.getString("d.nome");
            String cpf = resultSet.getString("a.cpf");
            String cargo = resultSet.getString("a.cargo");
            float salario = resultSet.getFloat("a.salario");
            String nome = resultSet.getString("a.nome");

            System.out.println("id_analista: "+id_analista+"| nome_departamento: "+nome_departamento+"| id_gerente: "+nome_gerente+"| cpf: "+cpf+"| salario: "+salario+"| nome: "+ nome);
        }

        statement.close();

        connection.close();
    }
}
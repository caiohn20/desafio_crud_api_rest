package br.teste.modelo;

import java.sql.PreparedStatement;
import br.teste.servicos.ConexaoMySQL;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private ConexaoMySQL conection;

    public UserDao() {
    }

    public String excluir(User user) {

        String sql = "DELETE FROM user where id=?";
        Boolean status = false;
        String msg = "";
        Retorno ret = new Retorno();
        String name = user.getName();

        PreparedStatement pst = ConexaoMySQL.getPreparedStatement(sql);

        try {

            pst.setLong(1, user.getId());
            System.out.println("Query delete " + pst);

            if (pst.executeUpdate() > 0) {
                status = true;
                msg = "Usuário " + name + " deletado com sucesso.";
                ret.setMsg(msg);
                ret.setStatus(status);
            }

        } catch (SQLException ex) {
            status = false;
            msg = "Usuário " + name + " não foi deletado.";
            ret.setMsg(msg);
            ret.setStatus(status);
        }

        Gson gson = new Gson();
        String json = gson.toJson(ret);

        return json;
    }

    public String inserir(User user) {

        Retorno ret = new Retorno();
        String sql = "INSERT INTO user(name,login,pass) VALUES (?,?,?)";
        Boolean status = false;
        PreparedStatement pst = ConexaoMySQL.getPreparedStatement(sql);

        try {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPass());

            System.out.println("Inserir " + pst);

            if (pst.executeUpdate() > 0) {
                status = true;
                String msg = "Usuário cadastrado com sucesso";
                ret.setMsg(msg);
                ret.setStatus(status);
                ret.setId(getId());
            }

        } catch (SQLException ex) {
            status = false;
            String msg = "Ocorreu um erro durante o cadastro";
            ret.setMsg(msg);
            ret.setStatus(status);
        }

        Gson gson = new Gson();
        String json = gson.toJson(ret);

        return json;
    }

    public User buscar(User user) {
        String sql = "SELECT * FROM user where id=?";
        User retorno = null;

        PreparedStatement pst = ConexaoMySQL.getPreparedStatement(sql);
        try {

            pst.setLong(1, user.getId());
            System.out.println("Select buscar " + pst);

            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = new User();
                retorno.setLogin(res.getString("login"));
                retorno.setName(res.getString("name"));
                retorno.setPass(res.getString("pass"));
                retorno.setId(res.getLong("id"));

            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário");
        }

        return retorno;
    }

    public Long getId() {
        String sql = "SELECT MAX(id) as id FROM user";
        Long retorno = null;

        PreparedStatement pst = ConexaoMySQL.getPreparedStatement(sql);
        try {

            ResultSet res = pst.executeQuery();

            if (res.next()) {
                retorno = res.getLong("id");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário");
        }

        return retorno;
    }

    public String alterar(Long id, User user) {

        Retorno ret = new Retorno();
        String sql = "UPDATE user set name = ?, login = ? , pass = ? where id = ?";
        Boolean status = false;
        PreparedStatement pst = ConexaoMySQL.getPreparedStatement(sql);
        String msg = "";

        try {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPass());
            pst.setLong(4, id);

            System.out.println("Alterar " + pst);

            if (pst.executeUpdate() > 0) {
                status = true;
                msg = "Usuário atualizado com sucesso";
            }

        } catch (SQLException ex) {
            status = false;
            msg = "Ocorreu um erro durante o cadastro";
        }

        ret.setMsg(msg);
        ret.setStatus(status);
        ret.setName(user.getName());
        ret.setLogin(user.getLogin());
        ret.setPass(user.getPass());

        Gson gson = new Gson();
        String json = gson.toJson(ret);

        return json;

    }

}

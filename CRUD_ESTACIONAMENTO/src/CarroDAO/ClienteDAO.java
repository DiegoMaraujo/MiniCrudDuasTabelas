/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarroDAO;



import CarroBean.CarroBean;
import CarroBean.ClienteBean;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Marcio
 */
public class ClienteDAO {

    private Connection con;
    
    int idNome;
    String nome;
    String sobreNome;
    String cpf;
    String rg;
    String telefone;
    String email;
    String data;
   
    
 public ClienteDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
 
 
 public String inserir(ClienteBean cliente) {
        
    String sql = "insert into cliente(nome,sobreNome,cpf,rg,telefone,email,data)values(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
           // ps.setInt(1, cliente.getIdNome());
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobreNome());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getRg());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getData());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
            }catch (SQLException e) {
                return e.getMessage();
        }
    }
 
 public String alterar(ClienteBean cliente){
        
  String sql = "update  cliente   set nome=?,  sobreNome=?, cpf=?, rg=?, telefone=?, email=?, data=? ";
        sql +=" where idNome=?";  
  
       
try {        
  
        PreparedStatement ps = con.prepareStatement(sql);
       
        
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getSobreNome());
        ps.setString(3, cliente.getCpf());
        ps.setString(4, cliente.getRg());
        ps.setString(5, cliente.getTelefone());
        ps.setString(6, cliente.getEmail());
        ps.setString(7, cliente.getData());
        ps.setInt(8, cliente.getIdNome() );
       
        
     if (ps.executeUpdate() > 0) {
            return "Alterado com sucesso.";
    } else {
            return "Erro ao alterar";
}
    } catch (SQLException e) {
            return e.getMessage();
        }
    }
 public String excluir(ClienteBean cliente){
     String sql = "delete from cliente WHERE idNome=?";   
   //String sql = "delete cliente.idNome=?, carro.fkCliente=? FROM carro INNER JOIN cliente  ON cliente.idNome = carro.fkCliente";
        
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, cliente.getIdNome());
            if (ps.executeUpdate() > 0) {
                    return "Excluído com sucesso.";
            } else {
                    return "Erro ao excluir";

                }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
 
   
public List<ClienteBean> listarTodo(){
               
    String sql = "select * from cliente ";
       
        ArrayList<ClienteBean> listaCliente = new ArrayList<ClienteBean>();
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                ClienteBean cb = new ClienteBean();
                
                cb.setIdNome(rs.getInt(1));
                cb.setNome(rs.getString(2));
                cb.setSobreNome(rs.getString(3));
                cb.setCpf(rs.getString(4));
                cb.setRg(rs.getString(5));
                cb.setTelefone(rs.getString(6));
                cb.setEmail(rs.getString(7));
                cb.setData(rs.getString(8));
                listaCliente.add(cb);
            }
            return listaCliente;
        } else {
            return null;
        }
    }catch (SQLException e) {
        return null;
    }
}

public  Vector PesquisaCli(String p) throws SQLException{
        
        Vector tb = new Vector();
       
       String sql = "select * from cliente where nome like '" + p +  "%'"; //puchando do banco de daddos

       PreparedStatement stmt = con.prepareStatement(sql);//fazendo a conecção com o banco
        ResultSet rs = stmt.executeQuery();//executando o metodo resut set
       
     
        while(rs.next()){//fazendo um enquando e puxando o proximo do banco 
            Vector usuarios = new Vector(); //fazendo um vetor com o nome usuarios
           
          
           usuarios.add(rs.getInt("idNome"));//pegando o nome do banco de dados e add no  vetor
           usuarios.add(rs.getString("nome"));
           usuarios.add(rs.getString("sobreNome"));
           usuarios.add(rs.getString("cpf"));
           usuarios.add(rs.getString("rg"));
           usuarios.add(rs.getString("telefone"));
           usuarios.add(rs.getString("email"));
           usuarios.add(rs.getString("data"));
           
                     tb.add(usuarios);
            
        }  
        return tb;
  
    }

 public ArrayList<ClienteBean> buscarTodo() throws SQLException {
 


    String sql =  ("SELECT "
                        
                        + "carro.placa,"
                        + " carro.cor, "
                        + "carro.descricao, "
                        + "carro.entrada, "
                        + "carro.saida, "
                        + "carro.valor, "
                        
                      
                        + "cliente.nome, "
                        + "cliente.sobreNome, "
                        + "cliente.cpf, "
                        + "cliente.rg, "
                        + "cliente.telefone, "
                        + "cliente.email, "
                        + "cliente.data"                
                   + " FROM  "
                        + " carro INNER JOIN cliente "
                           + "ON cliente.idNome = carro.fkCliente;"

           + ";"

       );
        
      ArrayList<ClienteBean> buscarTodo = new ArrayList<ClienteBean>();
   
      
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
 
        if (rs != null) {
         
         while (rs.next()) {
         ClienteBean cb = new ClienteBean();
  
                cb.setPlaca(rs.getString(1));
                cb.setCor(rs.getString(2));
                cb.setDescricao(rs.getString(3));
                cb.setEntrada(rs.getString(4));
                cb.setSaida(rs.getString(5));
                cb .setValor(rs.getString(6));
                
              
                cb .setNome(rs.getString(7));
                cb .setSobreNome(rs.getString(8));
                cb .setCpf(rs.getString(9));
                cb .setRg(rs.getString(10));
                cb .setTelefone(rs.getString(11));
                cb .setEmail(rs.getString(12));
                cb .setData(rs.getString(13));     
                buscarTodo.add(cb);
                    }
            return buscarTodo; 
        } else {
            return null;
        }

    }   

      
}

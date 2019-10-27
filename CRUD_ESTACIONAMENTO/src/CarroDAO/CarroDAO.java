/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarroDAO;
import CarroBean.CarroBean;
import CarroBean.ClienteBean;
import com.sun.xml.internal.bind.v2.model.core.ID;
import factory.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


/**
 *
 * @author Marcio
 * 
 */
public class CarroDAO {
    private Connection con;
    
     int idPlaca;
    String placa;
    String cor;
    String descricao;
    String entrada;
    String saida;
    String valor;
   int fkCliente;
    
public CarroDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
   
    
public String inserir(CarroBean carro) {
    
    String sql = "insert into carro(placa,cor,descricao,entrada,saida,valor,fkCliente)values(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            ps.setString(4, carro.getEntrada());
            ps.setString(5, carro.getSaida());
            ps.setString(6, carro.getValor());
            ps.setInt(7, carro.getFkCliente());

        int chave = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            chave = rs.getInt(1);
        }

        if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
            }catch (SQLException e) {
                return e.getMessage();
        }
    }

public String altera(CarroBean carro){
        
        String sql = "update carro set placa =?, cor =?, descricao =?, entrada =?, saida=?, valor =?,fkCliente =?";
        sql += " where  idPlaca=?";  
    //String sql = "update carro set cor = ?,descricao = ? , ";
       
try {
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, carro.getPlaca());
        ps.setString(2, carro.getCor());
        ps.setString(3, carro.getDescricao());
        ps.setString(4, carro.getEntrada());
        ps.setString(5, carro.getSaida());
        ps.setString(6, carro.getValor());
        ps.setInt(7, carro.getIdPlaca());
        
        if (ps.executeUpdate() > 0) {
            return "Alterado com sucesso.";
    } else {
            return "Erro ao alterar";
}
    } catch (SQLException e) {
            return e.getMessage();
        }
    }


public String excluir(CarroBean carro){
        
    String sql = "delete from carro where idPlaca =?";
        
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, carro.getIdPlaca());
            if (ps.executeUpdate() > 0) {
                    return "Excluído com sucesso.";
            } else {
                    return "Erro ao excluir";

                }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
public List<CarroBean> listarTodos() {
               
    String sql = "select * from carro ";
        List<CarroBean> listaCarro = (List<CarroBean>) new ArrayList<CarroBean>();
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                CarroBean cb = new CarroBean();
               
                cb.setIdPlaca(rs.getInt(1));
                cb.setPlaca(rs.getString(2));
                cb.setCor(rs.getString(3));
                cb.setDescricao(rs.getString(4));
                cb.setEntrada(rs.getString(5));
                cb.setSaida(rs.getString(6));
                cb.setValor(rs.getString(7));
                listaCarro.add(cb);
            }
            return listaCarro;
        } else {
            return null;
        }
    }catch (SQLException e) {
        return null;
    }
}
    
/*public List<CarroBean> Pesquisar(String p) throws SQLException{


         String sql = "select * from carro where placa like '" + p + "%'";
      
         List<CarroBean> Pesquisa = new ArrayList<CarroBean>();
          
         PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();

     
            if (rs != null) {
            while (rs.next()) {
                CarroBean cb = new CarroBean();
                
                cb.setPlaca(rs.getString("placa"));
                cb.setCor(rs.getString("cor"));
                cb.setDescricao(rs.getString("descricao"));
                Pesquisa.add(cb);
    }
        }
        return Pesquisa;

    }*/

    public  Vector Pesquisa(String p) throws SQLException{
        
        Vector tb = new Vector();
       
       String sql = "select * from carro where placa like '" + p +  "%'"; //puchando do banco de daddos

       PreparedStatement stmt = con.prepareStatement(sql);//fazendo a conecção com o banco
        ResultSet rs = stmt.executeQuery();//executando o metodo resut set
       
     
        while(rs.next()){//fazendo um enquando e puxando o proximo do banco 
            Vector usuarios = new Vector(); //fazendo um vetor com o nome usuarios
           
           usuarios.add(rs.getInt("idPlaca"));
           usuarios.add(rs.getString("placa"));//pegando o nome do banco de dados e add no  vetor
           usuarios.add(rs.getString("cor"));
           usuarios.add(rs.getString("descricao"));
           usuarios.add(rs.getString("entrada"));
           usuarios.add(rs.getString("saida"));
           usuarios.add(rs.getString("valor"));
                     tb.add(usuarios);
            
        }  
        return tb;
  
    }
}

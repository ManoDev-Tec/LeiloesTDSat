/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
//    public void cadastrarProduto (ProdutosDTO produto){
//        
//        
//        //conn = new conectaDAO().connectDB();
//        
//             
//              Connection conn = new conectaDAO().connectDB();
//               String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
//       
//               try {
//                   PreparedStatement pstm = conn.prepareStatement(sql);
//                   pstm.setString(1, produto.getNome());
//                   pstm.setInt(2, produto.getValor());
//                   pstm.setString(3, produto.getStatus());
//                   pstm.execute();
//                   pstm.close();
//               } catch (SQLException e) {
//                   System.out.println("Erro ao cadastrar produto: " + e.getMessage());
//               }     
//    }
//    
    public boolean cadastrarProduto(ProdutosDTO produto) {
    Connection conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, produto.getNome());
        pstm.setInt(2, produto.getValor());
        pstm.setString(3, produto.getStatus());
        pstm.execute();
        pstm.close();
        return true; // Cadastro realizado com sucesso
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        return false; // Falha no cadastro
    }
}
   
    
//    public ArrayList<ProdutosDTO> listarProdutos(){
//         
//        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
//        
//        
//        return listagem;
//    }
    
 
    PreparedStatement pstm;
    ResultSet rs;
  

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        conn = new conectaDAO().connectDB();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listagem;
    }
    
        
}


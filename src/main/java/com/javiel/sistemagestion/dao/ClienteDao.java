/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javiel.sistemagestion.dao;

import com.javiel.sistemagestion.models.Cliente;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class ClienteDao {
    
     public Connection conectar() {
        
            String baseDeDatos = "sgestion";
            String usuario = "root";
            String password = "";
            String host = "localhost";
            String puerto = "3306";
            String driver = "com.mysql.jdbc.Driver";
            String conexionUrl = "jdbc:mysql://"+host+":"+puerto+"/"+baseDeDatos+"?useSSL=false";
            
            Connection conexion = null;
            
                        
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(conexionUrl, usuario, password);          
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
        
    }
    
    public  void agregar(Cliente cliente) {   
                       
        try {
            
            Connection conexion = conectar();
            
            String sql = "INSERT INTO `clientes` (`id`, `nombre`, `Apellido`, `email`, `telefono`) VALUES (NULL, '"
                    + cliente.getNombre()+"', '"
                    + cliente.getApellido()+"', '"
                    + cliente.getEmail()+"', '"
                    + cliente.getTelefono()+"');";
            
            Statement statement = conexion.createStatement();
            statement.execute(sql);
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public  void editar(Cliente cliente) {   
                       
        try {
            
            Connection conexion = conectar();
            
            String sql = "UPDATE `clientes` SET "
                    + "`nombre` = '"+ cliente.getNombre()
                    + "', `Apellido` = '"+ cliente.getApellido()
                    + "', `email` = '"  + cliente.getEmail()
                    + "', `telefono` = '" + cliente.getTelefono()
                    + "' WHERE `clientes`.`id` ="+ cliente.getId()+";";
                    
            Statement statement = conexion.createStatement();
            statement.execute(sql);
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     public  List<Cliente> listar() {
        
            
            
            List<Cliente> listado = new ArrayList<>();
            
        try {
            
            Connection conexion = conectar();
            
            String sql = "SELECT * FROM `clientes`";
                   
            
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            
            while (resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getString("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefono(resultado.getString("telefono"));
                listado.add(cliente);
            
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return listado;
        
    }
     
     public void eliminar(String id) {
                                                       
        try {
            Connection conexion = conectar();
            
            String sql = "DELETE FROM clientes WHERE `clientes`.`id` = "+id;
                   
            
            Statement statement = conexion.createStatement();
            statement.execute(sql);                         
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
     
      public void guardar (Cliente cliente){
    
        if (StringUtils.isEmptyOrWhitespaceOnly(cliente.getId())) {
            agregar(cliente);
        }else{
            editar(cliente);
        }
        
    }
      
}

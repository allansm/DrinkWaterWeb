/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allan.connection.Conexao;
import com.allan.entity.Cup;

/**
 *
 * @author Allan de souza melo
 */
public class CupDao {
    public void add(Cup cup)throws Exception{
        Connection con = new Conexao().getConnection();
        String sql = "insert into tbl_cups(times,user_id,cupDate,cupTime) values(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, cup.getTimes());
        stmt.setInt(2, cup.getUser_id());
        stmt.setString(3, cup.getCupDate());
        stmt.setString(4, cup.getCupTime());
        stmt.execute();
        stmt.close();
    }
    public List<Cup> select()throws Exception{
        Connection con = new Conexao().getConnection();
        String sql = "select * from tbl_cups";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Cup> list = new ArrayList();
        Cup cup;
        while(rs.next()){
            cup = new Cup();
            cup.setId(rs.getInt("id"));
            cup.setTimes(rs.getInt("times"));
            cup.setUser_id(rs.getInt("user_id"));
            cup.setCupDate(rs.getString("cupDate"));
            cup.setCupTime(rs.getString("cupTime"));
            list.add(cup);
        }
        return list;
    }
    public List<Cup> select(int id) throws Exception{
    	List<Cup> lista = select();
    	List<Cup> lista2 = new ArrayList();
    	for(Cup c:lista) {
    		if(c.getUser_id() == id) {
    			lista2.add(c);
    		}
    	}
    	return lista2;
    }
}

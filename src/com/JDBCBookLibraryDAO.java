package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Book;
import com.library;

public class JDBCBookLibraryDAO implements bookDAOInterface,libraryDAOInterface{

	Connection con=null;

	@Override
	public void printAvailableBooks(int id) {
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from avail1 where l_id='"+id+"'");  

			
			while(rs.next())
			{
			 System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void borrowBook(String title, int id1) {
		int id=0;
		Boolean b=false;
		try {
			Statement stmt=con.createStatement();
			

			//String query="select book_id,borrowed from book where title="+title;
			//+title;
			ResultSet rs=stmt.executeQuery("select book_id,borrowed from borrow where title='"+ title +"' and l_id='"+id1+"'");
			while(rs.next())
			{
				id=rs.getInt(1);
				b=rs.getBoolean(2);
			}
			if(id==0)
			{
				System.out.println(title +" book not in catalog");
			
			}
			else if(id>0 && b==true)
			{
				System.out.println(title +" book not available");
			}
			else
			{
				String query3="update book set borrowed=1 where book_id='"+id+"'";
				stmt.executeUpdate(query3);
				System.out.println("Successfully borrowed book "+ title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void returnBook(String title,int id1) {
		int id=0;
		try {
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select book_id from book where title='"+ title +"' ");
			while(rs.next())
			{
				id=rs.getInt(1);
				//b=rs.getBoolean(2);
			}
			String query3="update book set borrowed=0 where book_id='"+id+"'";
				stmt.executeUpdate(query3);
				System.out.println("Successfully returned book"+ title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public Connection getConnection()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(con==null)
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
			con.setAutoCommit(false);
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return con;
	}
	public void commited()
	{
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rollbacked()
	{
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeConnection(){
        try {
        	
              if (con != null) {
                  con.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
	@Override
	public void insertIntoLib(library l) {
		
		try {
			String query = " insert into library(l_id,address)"
			        + " values (?,?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setInt (1, l.getL_id());
			      preparedStmt.setString (2, l.getAddress());
			      

			      // execute the preparedstatement
			      preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertBookLib(int l, int b) {
		try {
			String insert = " insert into sample_lib(l_id,book_id)"
			        + " values (?,?)";

			      // create the mysql insert preparedstatement
			    //  PreparedStatement preparedStmt = con.prepareStatement(query);
			      
			      
			      
			        PreparedStatement ps = con.prepareStatement(insert);  
			        ps.setInt (1,l);
			        ps.setInt (2,b);

			      // execute the preparedstatement
			      ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertIntoBook(Book b) {
  
		try {
			String query = " insert into book"
			        + " values (?,?,?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setInt (1, b.getBook_id());
			      preparedStmt.setString (2, b.getTitle());
			      preparedStmt.setBoolean(3, b.isBorrowed());
			      

			      // execute the preparedstatement
			      preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}

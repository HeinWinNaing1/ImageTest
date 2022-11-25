package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ImageDAO {
	
	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public ImageDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void close() {
		if(connection != null) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	public List<Image> getImageList(){
		List<Image> imageList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs=stmt.executeQuery("select * from image;");
			
			while (rs.next()) {
				imageList.add(new Image(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("path")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return imageList;
		
		
	}
	
	public int createImage(Image image) {
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("INSERT INTO `image` (`name`, `path`)"
					+ " VALUES"
					+ " (?,"
					+ " ?);");
			
			
			pStmt.setString(1, image.getName());
			pStmt.setString(2,image.getPath());
			
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		return rowEffected;
		
	}
	

}

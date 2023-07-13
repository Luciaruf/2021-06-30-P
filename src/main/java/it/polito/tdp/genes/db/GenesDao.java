package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	public List<String> getLocalization(){
		String sql = "SELECT DISTINCT c.`Localization` as localization "
				+ "FROM classification c ";
		
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("localization"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public Integer getPeso(String l1, String l2) {
		String sql = "select count(distinct i1.`Type`) as somma "
				+ "from interactions i1, classification c1, classification c2 "
				+ "where ( ( c1.`GeneID` = i1.`GeneID1` and c2.`GeneID` = i1.`GeneID2`) "
				+ "or ( c1.`GeneID` = i1.`GeneID2` and c2.`GeneID` = i1.`GeneID1`) ) "
				+ "and c1.`Localization`= ? and c2.`Localization`= ? ";
		
		Integer result = 0;
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, l1);
			st.setString(2, l2);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result = res.getInt("somma");
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}


	
}

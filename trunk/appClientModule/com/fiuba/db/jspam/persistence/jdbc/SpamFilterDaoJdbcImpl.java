package com.fiuba.db.jspam.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fiuba.db.jspam.entidad.Estadistica;
import com.fiuba.db.jspam.entidad.Word;
import com.fiuba.db.jspam.exception.IdExistenteException;
import com.fiuba.db.jspam.persistence.SpamFilterDao;
import com.fiuba.db.jspam.persistence.jdbc.util.ConnectionFactory;

/**
 * @author Demian
 *
 */
public class SpamFilterDaoJdbcImpl implements SpamFilterDao {

	/* (non-Javadoc)
	 * @see com.fiuba.db.jspam.business.SpamFilterDao#save(com.fiuba.db.jspam.entidad.Word)
	 */
	public void save(Word word) throws IdExistenteException {
		Connection conn = null;
        ConnectionFactory factory = ConnectionFactory.getInstance();
        PreparedStatement stmSelect = null;
        PreparedStatement stmInsert = null;
        ResultSet rs = null;
        try {
            conn = factory.getConnection();            
            stmSelect = conn.prepareStatement("SELECT word from WORD WHERE word = ?");
            stmSelect.setString(1,word.getId());
            rs = stmSelect.executeQuery();
            if (rs.next()){
            	//ya existe un registro
            	throw new IdExistenteException();
            } else {
            	stmInsert = conn.prepareStatement("insert into WORD(word, probabilidadSpam, probabilidadNoSpam) " +
            			"VALUES (?, ?, ?)");
            	stmInsert.setString(1, word.getId());
            	stmInsert.setBigDecimal(2, word.getProbabilidadSpam());
            	stmInsert.setBigDecimal(3, word.getProbabilidadNoSpam());
            	stmInsert.execute();
            }            
        } catch (SQLException ex) {
            throw new RuntimeException("Error al guardar una palabra",ex);
        } finally {
        	factory.closeResultSet(rs);
            factory.closeStatement(stmSelect);
            factory.closeStatement(stmInsert);
            factory.closeConnection(conn);
        }
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiuba.db.jspam.persistence.SpamFilterDao#update(com.fiuba.db.jspam.entidad.Word)
	 */
	public void update(Word word) {
		Connection conn = null;
        ConnectionFactory factory = ConnectionFactory.getInstance();        
        PreparedStatement stmUpdate = null;        
        try {
            conn = factory.getConnection();
            if ((word.getProbabilidadNoSpam() != null) && (word.getProbabilidadNoSpam().doubleValue() > 0)){
            	stmUpdate = conn.prepareStatement("update WORD set probabilidadNoSpam = ? where word = ?");            	
            	stmUpdate.setBigDecimal(1, word.getProbabilidadNoSpam());
            } else {
            	stmUpdate = conn.prepareStatement("update WORD set probabilidadSpam = ? where word = ?");            	
            	stmUpdate.setBigDecimal(1, word.getProbabilidadSpam());
            }
            
            stmUpdate.setString(2, word.getId());        	
        	stmUpdate.execute();      
        } catch (SQLException ex) {
            throw new RuntimeException("Error al guardar una palabra",ex);
        } finally {
            factory.closeStatement(stmUpdate);
            factory.closeConnection(conn);
        }
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiuba.db.jspam.persistence.SpamFilterDao#save(com.fiuba.db.jspam.entidad.Estadistica)
	 */
	public void save(Estadistica estadistica) {
		Connection conn = null;
        ConnectionFactory factory = ConnectionFactory.getInstance();        
        PreparedStatement stmInsert = null;        
        try {
            conn = factory.getConnection();            
        	stmInsert = conn.prepareStatement("INSERT INTO ESTADISTICAS(totalMailsSpam, totalMailsNoSpam) " +
        			"VALUES (?, ?)");
        	stmInsert.setInt(1, estadistica.getTotalMailsSpam());
        	stmInsert.setInt(2, estadistica.getTotalMailsNoSpam());        	
        	stmInsert.execute();            
        } catch (SQLException ex) {
            throw new RuntimeException("Error al guardar una estadistica",ex);
        } finally {        	
            factory.closeStatement(stmInsert);
            factory.closeConnection(conn);
        }		
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Estadistica findEstadistica() {
		Connection conn = null;
        ConnectionFactory factory = ConnectionFactory.getInstance();
        PreparedStatement stmSelect = null;        
        ResultSet rs = null;
        try {
            conn = factory.getConnection();            
            stmSelect = conn.prepareStatement("SELECT id, totalMailsSpam, totalMailsNoSpam"+
            								  " FROM estadisticas WHERE id = (SELECT MAX(id) FROM estadisticas)");            
            rs = stmSelect.executeQuery();
            if (rs.next()){
            	Estadistica estadistica = new Estadistica();
            	estadistica.setTotalMailsSpam(rs.getInt(2));
            	estadistica.setTotalMailsNoSpam(rs.getInt(3));
            	return estadistica;
            } else {
            	return null;
            }            
        } catch (SQLException ex) {
            throw new RuntimeException("Error buscar las estadisticas",ex);
        } finally {
        	factory.closeResultSet(rs);
            factory.closeStatement(stmSelect);            
            factory.closeConnection(conn);
        }
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Word findWord(String id) {
		Connection conn = null;
        ConnectionFactory factory = ConnectionFactory.getInstance();
        PreparedStatement stmSelect = null;        
        ResultSet rs = null;
        try {
            conn = factory.getConnection();            
            stmSelect = conn.prepareStatement("SELECT word, probabilidadSpam, probabilidadNoSpam FROM word "+
            								   "WHERE word = ?");
            stmSelect.setString(1, id);
            rs = stmSelect.executeQuery();
            if (rs.next()){
            	Word word = new Word();
            	word.setId(id);
            	word.setProbabilidadNoSpam(rs.getBigDecimal(3));
            	word.setProbabilidadSpam(rs.getBigDecimal(2));
            	return word;
            } else {
            	return null;
            }            
        } catch (SQLException ex) {
            throw new RuntimeException("Error buscar la palabra: "+id,ex);
        } finally {
        	factory.closeResultSet(rs);
            factory.closeStatement(stmSelect);            
            factory.closeConnection(conn);
        }
	}
}

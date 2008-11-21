package com.fiuba.db.jspam.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

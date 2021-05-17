package org.uniken.dao;

import java.sql.SQLException;
import java.util.List;

import org.uniken.bo.CorporateBO;

public interface ICorporateDAO {
	public int insertCorporate(CorporateBO bo) throws SQLException;
	public List<CorporateBO> getAllEmployees() throws SQLException;
	public CorporateBO getCorporateById(int id) throws SQLException;
	public int deleteCorporate(int id) throws SQLException;
	public int updateEmployee(CorporateBO bo) throws SQLException;
}

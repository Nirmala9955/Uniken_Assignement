package org.uniken.service;

import java.sql.SQLException;
import java.util.List;

import org.uniken.dto.CorporateDTO;

public interface ICorporateService {
	public String registerCorporate(CorporateDTO dto) throws SQLException;
	public List<CorporateDTO> fetchAllCorporates() throws SQLException;
	public CorporateDTO fetchCorporateById(int id) throws SQLException;
	public String removeCorporate(int id) throws SQLException;
	public String modifyCorporateById(CorporateDTO dto) throws SQLException;
}

package org.uniken.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.uniken.bo.CorporateBO;
import org.uniken.dao.CorporateDAOImpl;
import org.uniken.dao.ICorporateDAO;
import org.uniken.dto.CorporateDTO;

public class CorporateServiceImpl implements ICorporateService {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	
	private ICorporateDAO corpDAO;
	
	public CorporateServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
		
		corpDAO = new CorporateDAOImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public String registerCorporate(CorporateDTO corpDTO) throws SQLException {
		CorporateBO corpBO = new CorporateBO();
		int count = 0;
		//Convet DTO object to BO object
		corpBO.setCorporateName(corpDTO.getCorporateName());
		corpBO.setCorporateId(corpDTO.getCorporateId());
		corpBO.setAccountNo(corpDTO.getAccountNo());
		
		//use DAO
		count = corpDAO.insertCorporate(corpBO);
		return count!=0?"Corporate Details has added successfully!":"Corporate Details has not added successfully!";
	}

	@Override
	public List<CorporateDTO> fetchAllCorporates() throws SQLException {
		List<CorporateDTO> listDTO = new ArrayList<>();
		List<CorporateBO> listBO = corpDAO.getAllEmployees();
		//Convert listBO to listDTO
		listBO.forEach(bo->{
			CorporateDTO dto = new CorporateDTO();
			dto.setCorporateRID(bo.getCorporateRID());
			dto.setCorporateName(bo.getCorporateName());
			dto.setCorporateId(bo.getCorporateId());
			dto.setAccountNo(bo.getAccountNo());
			listDTO.add(dto);
		});
		return listDTO;
	}

	@Override
	public CorporateDTO fetchCorporateById(int id) throws SQLException {
		CorporateDTO corpDTO = new CorporateDTO();
		CorporateBO bo = corpDAO.getCorporateById(id);
		//convert BO to DTO
		corpDTO.setCorporateRID(bo.getCorporateRID());
		corpDTO.setCorporateName(bo.getCorporateName());
		corpDTO.setCorporateId(bo.getCorporateId());
		corpDTO.setAccountNo(bo.getAccountNo());
	
		return corpDTO;
	}

	@Override
	public String removeCorporate(int id) throws SQLException {
		int count = corpDAO.deleteCorporate(id);
		return count==0?"Corporate details not found for deletion":"Corporate  details found and deleted";
	}
	
	@Override
	public String modifyCorporateById(CorporateDTO dto) throws  SQLException {
		CorporateBO bo = new CorporateBO();
		
		int count = 0;
		//convert BO to DTO
		bo.setCorporateRID(dto.getCorporateRID());
		bo.setCorporateName(dto.getCorporateName());
		bo.setCorporateId(dto.getCorporateId());
		bo.setAccountNo(dto.getAccountNo());
		
		//use Service
		count = corpDAO.updateEmployee(bo);
		return count==0?"Corporate details are not found to update":"Corporate details are found to update";
	}

}

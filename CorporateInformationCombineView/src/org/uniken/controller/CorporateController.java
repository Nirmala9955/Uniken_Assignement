package org.uniken.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uniken.dto.Corporate;
import org.uniken.dto.CorporateDTO;
import org.uniken.service.CorporateServiceImpl;
import org.uniken.service.ICorporateService;

public class CorporateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICorporateService corpService;

	// init()
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		corpService = new CorporateServiceImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			switch (action) {
			case "/newCorp":
				showNewForm(req, resp);
				break;
			case "/editForm":
				editForm(req, resp);
				break;
			case "/updateCorp":
				updateDetais(req, resp);
				break;
			case "/addCorp":
				addNewCorporate(req, resp);
				break;
			case "/deleteCorp":
				deleteDetails(req, resp);
				break;
			case "/listCorps":
				listAllCorporate(req, resp);
			default:
				showHome(req, resp);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/pages/home.jsp");
		rd.forward(req, resp);
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/pages/corporate_register.jsp");
		rd.forward(req, resp);
	}

	private void addNewCorporate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		CorporateDTO corpDTO = new CorporateDTO();
		String corpName = req.getParameter("cname");
		String corpId = req.getParameter("cid");
		Long accNo = Long.parseLong(req.getParameter("account"));

		corpDTO.setCorporateName(corpName);
		corpDTO.setCorporateId(corpId);
		corpDTO.setAccountNo(accNo);

		corpService.registerCorporate(corpDTO);
		resp.sendRedirect("listCorps");
	}

	private void listAllCorporate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		List<CorporateDTO> listAllCorp = corpService.fetchAllCorporates();
		//get converted Map object
		Map<String, Map<String, List<Corporate>>> mapAllCorp = getFormatedData(listAllCorp);
		req.setAttribute("mapAllCorp", mapAllCorp);
		//forwarding
		RequestDispatcher rd = req.getRequestDispatcher("/pages/show_corporate.jsp");
		rd.forward(req, resp);
	}

	private Map<String, Map<String, List<Corporate>>> getFormatedData(List<CorporateDTO> listAllCorp) {

		Map<String, Map<String, List<Corporate>>> mapSM = new HashMap<>();

		for (CorporateDTO dto : listAllCorp) {
			
			Map<String, List<Corporate>> mapSL = mapSM.get(dto.getCorporateName());
			if (mapSL == null) { 
				mapSL = new HashMap<>();
				//create list object
				List<Corporate> list = new ArrayList<>();
				Corporate corp = new Corporate();
				corp.setCorporateRID(dto.getCorporateRID());
				corp.setAccountNo(dto.getAccountNo());
				//add to lsit
				list.add(corp);
				
				//add to mapSL
				mapSL.put(dto.getCorporateId(), list);
				//add to mapSM
				mapSM.put(dto.getCorporateName(), mapSL);
			} else {
				List<Corporate> corplist = mapSL.get(dto.getCorporateId());
				Corporate corp = new Corporate();
				if (corplist == null) {
					corplist = new ArrayList<Corporate>();
					corp.setCorporateRID(dto.getCorporateRID());
					corp.setAccountNo(dto.getAccountNo());
					corplist.add(corp);
					mapSL.put(dto.getCorporateId(), corplist);
				} else {	
					corp.setCorporateRID(dto.getCorporateRID());
					corp.setAccountNo(dto.getAccountNo());
					corplist.add(corp);
				}
			}
		}
		return mapSM;
	}

	private void editForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("crid"));
		CorporateDTO corpDTO = corpService.fetchCorporateById(id);

		req.setAttribute("corpDetail", corpDTO);

		RequestDispatcher rd = req.getRequestDispatcher("/pages/edit_corporate.jsp");
		rd.forward(req, resp);
	}

	private void updateDetais(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("corpRID"));

		CorporateDTO corpDTO = new CorporateDTO();

		corpDTO.setCorporateRID(id);
		corpDTO.setCorporateName(req.getParameter("corpName"));
		corpDTO.setCorporateId(req.getParameter("corpId"));
		corpDTO.setAccountNo(Long.parseLong(req.getParameter("accNo")));

		corpService.modifyCorporateById(corpDTO);

		resp.sendRedirect("listCorps");
	}

	private void deleteDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("crid"));
		corpService.removeCorporate(id);

		resp.sendRedirect("listCorps");
	}

}

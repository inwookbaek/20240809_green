package com.b_project.model.project.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;
import com.b_project.model.project.model.ProjectBoardBean;
import com.b_project.model.project.service.ProjectModifyService;
import com.b_project.model.project.service.ProjectReadService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProjectModifyAction implements Action {
	
	private static final String FORM_VIEW = "/view/project/projectModifyForm.jsp";

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}	
	}

	private ActionForward processForm(HttpServletRequest req, HttpServletResponse res) {
		
		ActionForward forward = new ActionForward();
		int project_no = Integer.parseInt(req.getParameter("project_no"));
		ProjectReadService projectReadService = new ProjectReadService();
		ProjectBoardBean article = projectReadService.getArticle(project_no);
		req.setAttribute("article", article);
		forward.setPath(FORM_VIEW);
		return forward;
	}

	private ActionForward processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ProjectModifyService projectModifyService = new ProjectModifyService();
		ActionForward forward = new ActionForward();
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		req.setAttribute("errors", errors);
		
		String realFolder = null;
		String saveFolder = "attachedFile";
		int fileSize = 1024 * 1024 * 5; // 5MB
		ServletContext context = req.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
		
		int project_no = Integer.parseInt(multi.getParameter("project_no"));
		
		ProjectBoardBean item = new ProjectBoardBean();
		item.setProjectNo(project_no);
		item.setSubject(multi.getParameter("subject"));
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = fmt.parse(multi.getParameter("end_date"));
		Date today = new Date();
		
		if(today.after(endDate)) {
			errors.put("invalidEndDate", Boolean.TRUE);
			forward.setPath(FORM_VIEW);
			return forward;
		}
		item.setEndDate(endDate);
		
		item.setContent(multi.getParameter("content"));
		
		int objFund = Integer.parseInt(multi.getParameter("obj_fund"));
		item.setObjFund(objFund);
		
		String category = multi.getParameter("category");
		if(category == null || category.isEmpty()) {
			item.setCategory("기타");
		} else {
			item.setCategory(multi.getParameter("category"));
		}
		
		item.setAttachedFile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		item.setCreator(multi.getParameter("creator"));
		
		boolean isUpdateSuccess = projectModifyService.modifyProject(item);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 수정에 실패했습니다!')");
			out.println("history.back()");	// history.back()는 이전 페이지로의 이동을 뜻함
			out.println("</script>");
		} else {
			forward.setRedirect(true);
			forward.setPath(req.getContextPath() + "/projectRead.do?project_no=" + project_no);
		}
		return forward;
	}

}

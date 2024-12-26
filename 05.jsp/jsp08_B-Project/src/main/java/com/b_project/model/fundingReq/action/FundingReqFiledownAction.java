package com.b_project.model.fundingReq.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b_project.controller.Action;
import com.b_project.controller.ActionForward;

public class FundingReqFiledownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String fileName = req.getParameter("downFile");
		String folder = req.getServletContext().getRealPath("fundingUploadFile");
		
		String filePath = folder + "/" + fileName;
		
		try {
			File file = new File(filePath);
			byte b[] = new byte[ (int) file.length()];
			
			res.reset();
			res.setContentType("application/octet-stream");
			
			String encoding = new String(fileName.getBytes("euc-kr"), "8859_1");
			
			res.setHeader("Content-Disposition", "attachment;filename=" + encoding);
			res.setHeader("Content-Length", String.valueOf(file.length()));

		if(file.isFile())
		{
			FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream servletOutputStream = res.getOutputStream();
           
            int readNum = 0;
            while ( (readNum = fileInputStream.read(b)) != -1) {
                servletOutputStream.write(b, 0, readNum);
            }
            servletOutputStream.close();
            fileInputStream.close();
		}
		
		} catch (Exception e) {
			System.out.println("다운로드 실패 " + e.getMessage());
		}
		
		
		return null;
	}

}

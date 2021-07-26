package kr.co.farmstory2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.CommonService;

public class MainController extends HttpServlet{  // Servlet�� ��� �޾ƾ��մϴ�.
	
	private static final long serialVersionUID = 1L; // 1�� Servlet��ȣ �޾ƿ���~
	private Map<String, Object> instances = new HashMap<>();  // property�� ������� map����ü
	
	
	@Override
	public void init(ServletConfig config) throws ServletException { // init = Maincontroller�� ���ʷ� ��û ������ ����Ǵ� Method [�ʱ�ȭ�ϴ³�]
		// �������� ȯ�� ������ ������մϴ�.
		
		// property file [�׼��ּ� ���� ����] ��� ���ϱ�
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF") + "/urlMapping.properties"; // server ���
		
		
		// property file�� inputStream connecting�� �Ŀ� property ��ü �����ϱ�
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		// property ��ü�� ���� Service ��ü �����ϱ�.
		Iterator iter = prop.keySet().iterator(); // Iterator = JavaCollection�� �ݺ���
		
		while(iter.hasNext()) { // �� ������ �������� �ݺ��ϱ�
			String k = iter.next().toString(); // keyset�� ��ȯ
			String v = prop.getProperty(k);    // value�� ��ȯ
		
			try {
				Class obj = Class.forName(v); // Class ����
				Object instance = obj.newInstance(); 
				
				instances.put(k, instance);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //doget = client�� get ������� ��û�ϸ� doget�� �����
		requestProc(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //dopost = client�� post ������� ��û�ϸ� dopost�� �����
		requestProc(req, resp);
	}
	
	// Get/Post �Ѵ� request + response��� ��ü�� ������ �ٸ� private�� �����ؼ� request�ؼ� ����ϰ� �ϱ�.
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		// ��û �ּҿ��� service ��ü�� key ���ϱ�
		String path = req.getContextPath(); // http://localhost:8080/Ch08/hello.do���� Ch08����
		String url = req.getRequestURI();	// localhost8080���� = url / �ڴ� uri�Դϴ�. 
		String key = url.substring(path.length()); // Ch08�ڿ��� �������� ���� = uri���� Ch08 ���� = /hello.do/ ���ϱ�
		
		// Map���� service ��ü ������
		CommonService instance = (CommonService)instances.get(key);
		
		// service ��ü ���� �� view return�� �ޱ�
		String result = instance.requestProc(req, resp); // ������ requestProc�� return�� �޾ƿ���
		// view���� result�� �ٲ����� -> post���۹�Ŀ��� redirect�� �޴� �����ε� �̷��� ���� forward�� ���� ���ؼ� result�� �ٲ����
		
		if(result.startsWith("redirect:")) {
			// redirect ���� ��
			
			String redirectUrl = result.substring(9);
			resp.sendRedirect(redirectUrl);
			
		}else if(result.startsWith("json:")){ // return ���� �����Ͱ� Json�� ���.
			//Json������ ���
			PrintWriter out = resp.getWriter();
			out.print(result.substring(5)); // json["result"] �� ������ index 5������ ���������� ���
			
			
		}else {	
		
		// view ���� ������ forward �ϱ�
		RequestDispatcher dispatcher = req.getRequestDispatcher(result);
		dispatcher.forward(req, resp);
		}
	}

	
}// MainController End
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

public class MainController extends HttpServlet{  // Servlet을 상속 받아야합니다.
	
	private static final long serialVersionUID = 1L; // 1번 Servlet번호 받아오기~
	private Map<String, Object> instances = new HashMap<>();  // property로 만들어진 map구조체
	
	
	@Override
	public void init(ServletConfig config) throws ServletException { // init = Maincontroller가 최초로 요청 받을때 실행되는 Method [초기화하는놈]
		// 전반적인 환경 설정을 해줘야합니다.
		
		// property file [액션주소 맵핑 파일] 경로 구하기
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF") + "/urlMapping.properties"; // server 경로
		
		
		// property file을 inputStream connecting한 후에 property 객체 생성하기
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		// property 객체로 부터 Service 객체 생성하기.
		Iterator iter = prop.keySet().iterator(); // Iterator = JavaCollection의 반복자
		
		while(iter.hasNext()) { // 다 끄집어 낼때까지 반복하기
			String k = iter.next().toString(); // keyset값 소환
			String v = prop.getProperty(k);    // value값 소환
		
			try {
				Class obj = Class.forName(v); // Class 정의
				Object instance = obj.newInstance(); 
				
				instances.put(k, instance);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //doget = client가 get 방식으로 요청하면 doget이 실행됨
		requestProc(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //dopost = client가 post 방식으로 요청하면 dopost이 실행됨
		requestProc(req, resp);
	}
	
	// Get/Post 둘다 request + response방식 자체는 같으니 다른 private를 선언해서 request해서 깔끔하게 하기.
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		// 요청 주소에서 service 객체의 key 구하기
		String path = req.getContextPath(); // http://localhost:8080/Ch08/hello.do에서 Ch08구함
		String url = req.getRequestURI();	// localhost8080까지 = url / 뒤는 uri입니다. 
		String key = url.substring(path.length()); // Ch08뒤에서 끝까지를 구함 = uri에서 Ch08 빼기 = /hello.do/ 구하기
		
		// Map에서 service 객체 꺼내기
		CommonService instance = (CommonService)instances.get(key);
		
		// service 객체 실행 후 view return을 받기
		String result = instance.requestProc(req, resp); // 각각의 requestProc의 return값 받아오기
		// view에서 result로 바뀐이유 -> post전송방식에서 redirect를 받는 형식인데 이러면 뒤의 forward를 받지 못해서 result로 바꿔버림
		
		if(result.startsWith("redirect:")) {
			// redirect 받을 때
			
			String redirectUrl = result.substring(9);
			resp.sendRedirect(redirectUrl);
			
		}else if(result.startsWith("json:")){ // return 받은 데이터가 Json일 경우.
			//Json데이터 출력
			PrintWriter out = resp.getWriter();
			out.print(result.substring(5)); // json["result"] 기 때문에 index 5번부터 마지막까지 출력
			
			
		}else {	
		
		// view 정보 가지고 forward 하기
		RequestDispatcher dispatcher = req.getRequestDispatcher(result);
		dispatcher.forward(req, resp);
		}
	}

	
}// MainController End
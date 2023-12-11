package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// jsp에서 받은 요청을 처리 , 처리결과를 다른 jsp로 보내는 역할을 하는 객체
	private RequestDispatcher rdp;
	
	// 목적지 주소를 저장하는 변수
	private String destPage;
	
	// 구문 체크 값 저장변수
	private int isOk;
	
	// 파일 저장 경로
	private String savePath;
	
	// "controller"와 "service"를 이어주는.. BoardService를 bsv로 줄여서 만들어준다.
	private BoardService bsv; //interface로 servive폴더에 생성
	
	
	
    public BoardController() {
    	//생성자
    	bsv = new BoardServiceImpl(); //class로 servive폴더에 생성 / bsv를 구현할 객체
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("system.out.println --- 처럼 사용할 기능");
		
		// 매개변수의 객체의 인코딩 설정
		request.setCharacterEncoding("utf-8"); // 요청
		response.setCharacterEncoding("utf-8"); // 응답
		response.setContentType("text/html; charset=UTF-8");
		
		//jsp에서 오는 요청주소를 받는 설정
		String uri = request.getRequestURI(); 
		log.info("log객체를 통한 로그 >>>>>>" + uri);
		
		// 마지막 /의 뒤의 값을 가져오겠다.
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("실제 경로 >>>>>>" + path);
		
		
		
		// ----------- 실제 처리 경로 ----------- 
		
		
		switch(path) {
		
		// 글쓰기 페이지로 넘어가는..
		case "register" :
			destPage = "/board/register.jsp";
			break;
			
		// 글을 쓰기위한..	
		case "insert" :
			try {
				
				// jsp에서 데이터를 입력후 => conteoller로 전송
				// DB에 등록한 후 => index.jsp로 이동
				
				// index.jsp에서 받은값의 title , writer , content를 꺼내온다.
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info(">>>>> insert 체크 1 <<<<<");
				
				// 받아온 값을 새로운 BoardVO에 넣는다.
				// 따로 title , writer , content 생성자가 있기때문에 불러오면된다.
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("board insert >>>>> {}" , isOk > 0 ? "통과~~" : "통과못함~~");
				
				// 만들어진 bvo를 db에 저장하기위해 Boardservice로 보낸다
				isOk = bsv.register(bvo);
				log.info("board register >>>>> {}" , isOk > 0 ? "통과~~" : "통과못함~~");
				
				// 목적지 주소
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("insert 에러에러에러에러에러");
				e.printStackTrace();
			}
			break;
			
		// 게시판 리스트를 뽑기위한..	
		case "list" :
			
			try {
				
				// index에서 list 버튼을 클릭하면 컨트롤러에서 db로 전체 리스트 요청
				// 전체 리스트를 가지고 list.jsp에 뿌리기
				log.info(">>>>> list 체크 1 <<<<<");
				
				List<BoardVO> list = bsv.getlist();
				log.info("list >>>>> {}" , list);
				
				// 서버에서 가지고있는거를 화면에 뿌리고싶을때
				// String = "list"   /   Object = list
				request.setAttribute("list",list);
				
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list 에러에러에러에러에러");
				e.printStackTrace();
			}
			
		// 상세페이지	
		case "detail" :
			
			try {
				
				// 화면에서 클릭한 게시글의 bno를 기준으로
				// 모든값을 불러온다.
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>>> detile 체크 1 <<<<<");
				
				BoardVO bvo = bsv.getdetail(bno);
				log.info("detile >>>>> {}" , bvo);
				
				// 서버에서 가지고있는거를 화면에 뿌리고싶을때
				// String = "bvo"   /   Object = bvo
				request.setAttribute("bvo", bvo);
				
				// detail.jsp로 갈거다
				destPage = "/board/detail.jsp"; 
				
			} catch (Exception e) {
				log.info("detail 에러에러에러에러에러");
				e.printStackTrace();
			}
			
			
		}
		
		
	
		
		// 목적지 주소로 데이터를 전달(RequestDispatcher)
		rdp = request.getRequestDispatcher(destPage);
		
		// 요청에 필요한 객체를 가지고 경로로 전송 
		rdp.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request : 입력값 가져올때
		// response : 화면이랑 관련이된
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}

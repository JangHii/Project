package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	//interface로 repository폴더에 생성한다
	private BoardDAO bdao; 
	
	public BoardServiceImpl() {
		//casll로 repository폴더에 생성한다
		bdao = new BoardDAOImpl(); 
	}

	// 글을 쓰기위한..
	@Override
	public int register(BoardVO bvo) { // BoardDAO로 보내주기위해 되도록이면 DB명령어로 바꿔서 보내주는게 좋다.
		log.info(">>>>> insert 체크 2 <<<<<");
		return bdao.insert(bvo);
	}
	
	// 게시판 리스트
	@Override
	public List<BoardVO> getlist() {
		log.info(">>>>> getlist 체크 2 <<<<<");
		return bdao.getlist();
	}

	@Override
	public BoardVO getdetail(int bno) {
		log.info(">>>>> getdetail 체크 2 <<<<<");
		
		// 상세페이지(detail)을 누를시 조회수(readcount)가 올라가는..
		int isOk = bdao.readcountupdate(bno);
		
		return bdao.getdetail(bno);
	}


	
	
	
}

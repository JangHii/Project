package repository;

import java.util.List;

import domain.BoardVO;

public interface BoardDAO {

	// 글쓰기
	int insert(BoardVO bvo);
	
	// 게시판리스트
	List<BoardVO> getlist();

	// 상세페이지
	BoardVO getdetail(int bno);

	// 상세페이지 클릭시 카운트 +1
	int readcountupdate(int bno);

}

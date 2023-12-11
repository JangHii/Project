package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {

	// 글을 쓰기위한..	
	int register(BoardVO bvo);

	// 게시판 리스트를 가지고오기위한..
	List<BoardVO> getlist();

	// 상세게시글
	BoardVO getdetail(int bno);



}

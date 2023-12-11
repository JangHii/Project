package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	// 로그 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//DB연결
	private SqlSession sql;
	
	public BoardDAOImpl() {
		// SqlSessionFactory의 객체를 생성하고 파일을 로드한다.
		new DatabaseBuilder();
		
		// DatabaseBuilder 클래스의 getFactoey를 호출하여 SqlSessionFactory를 얻은후
		// openSession 메서드를 호출하여 SqlSession 객체를 생성한다.
		sql = DatabaseBuilder.getFactory().openSession();
	}

	
	// 글쓰기
	@Override
	public int insert(BoardVO bvo) {
		
		log.info(">>>>> insert 체크 3 <<<<<");
		
		int isOk = sql.insert("boardMapper.add" , bvo);
		
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}


	// 게시판 리스트
	@Override
	public List<BoardVO> getlist() {
		log.info(">>>>> getlist 체크 3 <<<<<");
		return sql.selectList("boardMapper.list");
	}


	// 상세페이지
	@Override
	public BoardVO getdetail(int bno) {
		log.info(">>>>> getdetail 체크 3 <<<<<");
		return sql.selectOne("boardMapper.det", bno);
	}

	// 상세페이지 클릭시 조회수 카운트
	@Override
	public int readcountupdate(int bno) {
		log.info(">>>>> readcountupdate 체크 3 <<<<<");
		
		// 업데이트가되면 1  /  안되면 0 처리
		int isOk = sql.update("boardMapper.read", bno);
		if(isOk > 0) {
			
			// commit : 모든 작업을 정상적으로 처리하겠다 확정하는 명령어
			// if값이 참일때 작동한다
			sql.commit();
		}
		return isOk;
	}
	
	
}

package orm;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class DatabaseBuilder {

	// SqlSessionFactory이 생성됨으로써 SqlSession이 인스턴스를 생성해서 데이터베이스와 상호작용을 한다.
	private static SqlSessionFactory factory;
	
	// MyBatis의 파일 경로
	private static final String CONFIG = "orm/MybatisConfig.xml";
	
	
	// 처음 시작할때 로드가 되는 블록
	// SqlSessionFactory 객체를 생성하고 설정 파일을 로드
	static {
		try {
			// MybatisConfig.xml 파일을 읽어옴
			// SqlSessionFactoryBuilder() = > SqlSessionFactory 객체를 생성하기 위해 사용됨
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(CONFIG));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 생성된 SqlSessionFactory를 return
	// 다른 클래스에서 dbsession을 열수있다. 
	// getFactory
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}

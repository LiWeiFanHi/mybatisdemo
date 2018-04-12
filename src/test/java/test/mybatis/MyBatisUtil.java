package test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sqlSessionFactory;
	//类线程锁
	private static final Class CLASS_LOCK = MyBatisUtil.class;
	private MyBatisUtil() {}
	public static SqlSessionFactory initSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
//		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		synchronized (CLASS_LOCK) {
			if (sqlSessionFactory==null){
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		}
		return sqlSessionFactory;
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		return sqlSessionFactory;
	}
}

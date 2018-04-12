package test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TestMyBatis {
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	public static void main(String[] args) {
		testAdd();
		getPerson();
	}

	public static void testAdd() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
			Person person = new Person("lisi", new Integer(25));
			personMapper.insertPerson(person);
			sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
		} finally {
			sqlSession.close();
		}
	}

	public static void getPerson() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
			Person person = personMapper.getPerson("lisi");
			System.out.println("name: " + person.getName() + "|age: "
					+ person.getAge());
		} finally {
			sqlSession.close();
		}
	}
}

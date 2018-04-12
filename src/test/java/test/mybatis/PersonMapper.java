package test.mybatis;

public interface PersonMapper {
	public void insertPerson(Person person);

	public Person getPerson(String name);
}

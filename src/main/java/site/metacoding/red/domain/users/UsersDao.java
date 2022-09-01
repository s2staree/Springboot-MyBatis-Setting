package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.web.dto.request.users.JoinDto;

public interface UsersDao {
	public void insert(JoinDto joinDto);
	public Users findById(Integer id);
	public List<Users> findAll();	// List<리턴값> 함수이름() => 함수이름이 아이디
	public void update(Users users);
	public void delete(Integer id);
}

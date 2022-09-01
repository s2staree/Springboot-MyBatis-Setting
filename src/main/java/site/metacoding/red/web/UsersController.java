package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor	// final 붙어있는애만 생성자 만들어라.
@RestController
public class UsersController {

	private final UsersDao usersDao;	// final로 정의된 객체는 무조건 생성자가 있어야함
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id));
	}
	
	@GetMapping("/users")
	public RespDto<?> getUsersList(){
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return new RespDto<>(1, "회원가입완료", null);	// 201번 insert됨
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		
		// 1번 : id로 select 하자. (영속화: ~ 데이터 옮기는 행위)
		Users usersPS = usersDao.findById(id);
		
		// 2번 : 변경
		usersPS.전체수정(updateDto);

		// 3번 : 영속화된 오브젝트로 update하기
		usersDao.update(usersPS);
		
		return new RespDto<>(1, "회원수정완료", null);
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		usersDao.delete(id);
		return new RespDto<>(1, "삭제완료", null);
	}
	
	@PutMapping("/user/{id}/password")
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){
		
		// 1번 영속화(전체)
		Users usersPS = usersDao.findById(id);
		// 2번 부분 변경
		usersPS.패스워드수정(password);
		// 3번 전체 업데이트
		usersDao.update(usersPS);
		
		return new RespDto<>(1, "패스워드수정완료", null);
	}
}

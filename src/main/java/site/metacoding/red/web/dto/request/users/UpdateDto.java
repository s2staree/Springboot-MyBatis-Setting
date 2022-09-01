package site.metacoding.red.web.dto.request.users;

import lombok.Getter;
import lombok.Setter;

// 외부 통신을 위한 DTO

@Setter
@Getter
public class UpdateDto {
	private String username;
	private String password;
	private String email;
}

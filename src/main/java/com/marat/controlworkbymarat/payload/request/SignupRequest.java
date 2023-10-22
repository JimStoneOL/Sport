package com.marat.controlworkbymarat.payload.request;


import com.marat.controlworkbymarat.annotations.PasswordMatches;
import com.marat.controlworkbymarat.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

	@Email(message = "Не тот формат")
	@NotBlank(message = "Email обязательное поле")
	@ValidEmail
	private String email;
	@NotEmpty(message = "Имя: обязательное поле")
	private String firstname;
	@NotEmpty(message = "Фамилия: обязательное поле")
	private String lastname;
	@NotEmpty(message = "Отчество: обязательное поле")
	private String middlename;
	@NotEmpty(message = "Пароль: обязательное поле")
	@Size(min=6)
	private String password;
	private String confirmPassword;

}

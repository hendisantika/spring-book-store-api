package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.common.APIResponse;
import id.my.hendisantika.springbookstoreapi.dto.LoginRequestDTO;
import id.my.hendisantika.springbookstoreapi.dto.SignUpRequestDTO;
import id.my.hendisantika.springbookstoreapi.entity.User;
import id.my.hendisantika.springbookstoreapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:35
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = new APIResponse();

        // validation

        // dto to entity
        User userEntity = new User();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setActive(Boolean.TRUE);
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(signUpRequestDTO.getPassword());

        // store entity
        userEntity = userRepository.save(userEntity);

        // generate jwt
        String token = jwtUtils.generateJwt(userEntity);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        // return
        return apiResponse;
    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {
        APIResponse apiResponse = new APIResponse();

        // validation

        // verify user exist with given email and password
        User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());

        // response
        if (user == null) {
            apiResponse.setData("User login failed");
            return apiResponse;
        }

        // generate jwt
        String token = jwtUtils.generateJwt(user);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        return apiResponse;
    }
}

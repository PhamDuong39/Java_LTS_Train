package com.example.java_train.ServicesImp;

import com.example.java_train.Entities.Account;
import com.example.java_train.Entities.Enum.TokenType;
import com.example.java_train.Entities.Token;
import com.example.java_train.Models.AuthModel.LoginModel;
import com.example.java_train.Models.AuthModel.RegisterModel;
import com.example.java_train.Models.Response.LoginResponseModel;
import com.example.java_train.Repositories.AccountReposiroty;
import com.example.java_train.Repositories.TokenRepository;
import com.example.java_train.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final AccountReposiroty accountReposiroty;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseModel Register(RegisterModel registerModel) {
        var account = Account.builder()
                .firstname(registerModel.getFirstname())
                .lastname(registerModel.getLastname())
                .email(registerModel.getEmail())
                .password(passwordEncoder.encode(registerModel.getPassword()))
                .role(registerModel.getRole())
                .build();

        var savedAccount = accountReposiroty.save(account);
        var refreshToken = jwtService.GenerateRefreshToken(savedAccount);
        var accessToken = jwtService.generateToken(savedAccount);
        SaveAccountRefreshToken(savedAccount, refreshToken);
        return LoginResponseModel.builder()
                .AccessToken(accessToken)
                .RefreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponseModel Login(LoginModel loginModel) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var account = accountReposiroty.findByEmail(loginModel.getEmail()).orElseThrow();
        var refreshToken = jwtService.GenerateRefreshToken(account);
        var accessToken = jwtService.generateToken(account);
        // Thu hồi token
        RevokeAllUserToken(account);
        SaveAccountRefreshToken(account, refreshToken);

        return LoginResponseModel.builder()
                .AccessToken(accessToken)
                .RefreshToken(refreshToken)
                .build();
    }

    @Override
    public ResponseEntity<LoginResponseModel> RefreshToken() {
        return null;
    }

    private void SaveAccountRefreshToken(Account account, String refreshToken) {
        var token = Token.builder()
                .account(account)
                .token(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


    private void RevokeAllUserToken(Account account) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(account.getId());
        if(validUserTokens.isEmpty()) {
            return;
        }
        for(var token : validUserTokens) {
            token.setRevoked(true);
            token.setExpired(true);
        }
    }
}

package com.example.health.configs;

import com.example.health.services.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {
  //are rolul de a intercepta cerearea clientului de de a valda json web token(jwt)
    //inclus in antetul cererii http in functie de modul in care este configurat
    //poate fi configurat să efectueze și controlul accesului,
  // adică să verifice dacă utilizatorul
  // autentificat are permisiunile necesare pentru a accesa resursa cerută.
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    ////este o subclasa a clasei encoder care encodeaza parola

    //este utilizat pentru a compara o parolă introdusă de utilizator cu parola stocată criptată în
    // baza de date cu bcrypt. Aceasta implică luarea parolei introduse,
    // criptarea acesteia și apoi compararea rezultatului criptării cu valoarea stocată în baza de date.

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

    //primește obiecte de tip Authentication care conțin informații despre cererea de autentificare a utilizatorului.
    // Aceste informații includ numele de utilizator și parola sau alte detalii de autentificare necesare.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/public/**").permitAll()
                                .requestMatchers("/authenticate").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/doctor/**").hasRole("DOCTOR")
                                .requestMatchers("/patient/**").hasRole("PATIENT")
                                .requestMatchers(HttpMethod.POST, "/hospitals/add").hasRole("USER")
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }
}

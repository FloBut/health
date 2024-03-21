//package com.example.health.services;
//
//import com.auth0.jwt.interfaces.Claim;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//
//@Service
//public class JwtRequestFilter extends OncePerRequestFilter {
//    private JwtTokenService jwtTokenService;
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    public JwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService) {
//        this.jwtTokenService = jwtTokenService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//        //daca head- rul este null sau nu incepe cu "beare" atunci tu nu mi ai dat un token si ii dau return
//        if (header == null || !header.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//        String token = header.substring(7);
//        String userName = jwtTokenService.validateToken(token);
//        if (userName == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        filterChain.doFilter(request, response);
//    }
//}
//
////if (auth != null && auth.startsWith("Bearer")) {
////            String token = auth.split(" ")[1];
////            String username = null;
////            if (token == null) {
////                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
////                return;
////            }
////            try {
////                username = this.jwtHelper.getUsernameFromToken(token);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
////                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
////                Boolean validToken = this.jwtHelper.validateToken(token, userDetails);
////                if (validToken) {
////                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
////                }
////            }
////        }
////        filterChain.doFilter(request, response);
////    }
//

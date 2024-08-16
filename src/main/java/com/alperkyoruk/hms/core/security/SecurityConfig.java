package com.alperkyoruk.hms.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.alperkyoruk.hms.business.abstracts.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x


                                .requestMatchers("/api/facilities/addFacility").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/facilities/deleteFacility").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/facilities/updateFacility").hasAnyRole("ADMIN","MODERATOR")


                                .requestMatchers("/api/guests/**").hasAnyRole("ADMIN","MODERATOR")


                                .requestMatchers("/api/menuItems/addMenuItem").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/menuItems/deleteMenuItem").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/menuItems/updateMenuItem").hasAnyRole("ADMIN","MODERATOR")


                                .requestMatchers("/api/reservation/**").hasAnyRole("ADMIN","MODERATOR")


                                .requestMatchers("/api/rooms/addRoom").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/rooms/deleteRoom").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/rooms/updateRoom").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/rooms/**").hasAnyRole("ADMIN","MODERATOR","STAFF")


                                .requestMatchers("/api/roomServiceOrders/addRoomServiceOrder").hasAnyRole("ADMIN","MODERATOR","STAFF","GUEST")
                                .requestMatchers("/api/roomServiceOrders/deleteRoomServiceOrder").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/roomServiceOrders/updateRoomServiceOrder").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/roomServiceOrders/getOrdersByGuest").hasAnyRole("ADMIN","MODERATOR","STAFF","GUEST")
                                .requestMatchers("/api/roomServiceOrders/**").hasAnyRole("ADMIN","MODERATOR","STAFF")


                                .requestMatchers("/api/staff/**").hasAnyRole("ADMIN","MODERATOR")


                                .requestMatchers("/api/tickets/addTicket").hasAnyRole("ADMIN","MODERATOR","STAFF","GUEST")
                                .requestMatchers("/api/tickets/deleteTicket").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/tickets/updateTicket").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/tickets/getTickets").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/tickets/getById").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/tickets/getTicketsByGuest").hasAnyRole("ADMIN","MODERATOR","STAFF","GUEST")
                                .requestMatchers("/api/tickets/getTicketsByStaff").hasAnyRole("ADMIN","MODERATOR","STAFF")
                                .requestMatchers("/api/tickets/**").hasAnyRole("ADMIN","MODERATOR","STAFF")


                                .requestMatchers("/api/users/addUser").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/users/deleteUser").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/users/updateUser").hasAnyRole("ADMIN","MODERATOR")
                                .requestMatchers("/api/users/addModerator").hasAnyRole("ADMIN")
                                .requestMatchers("/api/users/removeModerator").hasAnyRole("ADMIN")
                                .requestMatchers("/api/users/**").hasAnyRole("ADMIN","MODERATOR")



                                .anyRequest().permitAll()

                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
package com.andersen.ticket_to_ride.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Custom password encoder that provides simple password encoding and matching.
 */
@Configuration
public class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * Encodes the raw password.
     *
     * @param rawPassword the raw password to encode
     * @return the encoded password
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    /**
     * Verifies if the raw password matches the encoded password.
     *
     * @param rawPassword     the raw password to check
     * @param encodedPassword the encoded password to compare with
     * @return true if passwords match, false otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}

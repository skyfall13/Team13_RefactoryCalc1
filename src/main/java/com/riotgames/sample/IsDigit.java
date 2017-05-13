package com.riotgames.sample;

/**
 * Created by gobug on 2017-05-14.
 */
public class IsDigit {
    public boolean isDigit(String token) {
        if (token.charAt(0) >= '0' && token.charAt(0) <= '9')
            return true;
        else
            return false;
    }
}

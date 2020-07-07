package com.microblog.buys.cart.service;

import com.microblog.util.response.ServerResponseDto;

public interface CartService {

    ServerResponseDto selectAll();
    ServerResponseDto delete();
}

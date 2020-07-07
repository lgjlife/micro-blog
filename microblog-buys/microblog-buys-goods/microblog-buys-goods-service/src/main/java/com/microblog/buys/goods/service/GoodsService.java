package com.microblog.buys.goods.service;

import com.microblog.util.response.ServerResponseDto;

public interface GoodsService {

    ServerResponseDto selectAll();

    ServerResponseDto delete();
}

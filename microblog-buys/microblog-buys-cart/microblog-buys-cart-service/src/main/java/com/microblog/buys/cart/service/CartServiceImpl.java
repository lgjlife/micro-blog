package com.microblog.buys.cart.service;

import com.microblog.buys.cart.dao.mapper.CartMapper;
import com.microblog.buys.cart.dao.model.Cart;
import com.microblog.util.result.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ServerResponseDto selectAll() {

        List<Cart> carts = cartMapper.selectAll();

        ServerResponseDto<List<Cart>> response = new ServerResponseDto(ResponseCode.SUCCESS.getCode(),
                carts,ResponseCode.SUCCESS.getMessage());
        return response;
    }

    @Override
    public ServerResponseDto delete() {

       // Integer result = cartMapper.deleteByPrimaryKey(1l);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUserId((long)(new Random().nextInt(100)));
        Integer result = cartMapper.updateByPrimaryKey(cart);


        if(result == 0){
            ServerResponseDto<Integer> response = new ServerResponseDto(ResponseCode.FAIL.getCode(),
                    result,"删除失败");
            return response;
        }
        else {
            ServerResponseDto<Integer> response = new ServerResponseDto(ResponseCode.SUCCESS.getCode(),
                    result,"删除成功");
            return response;
        }
    }
}

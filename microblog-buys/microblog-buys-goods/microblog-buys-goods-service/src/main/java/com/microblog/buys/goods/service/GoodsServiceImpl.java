package com.microblog.buys.goods.service;

import com.microblog.buys.goods.dao.mapper.GoodsMapper;
import com.microblog.buys.goods.dao.model.Goods;
import com.microblog.util.response.ResponseCode;
import com.microblog.util.response.ServerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper ;

    public ServerResponseDto selectAll(){

        List<Goods> goods = goodsMapper.selectAll();

        ServerResponseDto<List<Goods>> response = new ServerResponseDto(ResponseCode.SUCCESS.getCode(),
                goods,ResponseCode.SUCCESS.getMessage());
        return response;

    }


    @Override
    public ServerResponseDto delete() {

        //Integer result = goodsMapper.deleteByPrimaryKey(1l);

        Goods goods = new Goods();
        goods.setId(1L);
        goods.setName(new Random().nextInt(100)+"");
        Integer result  = goodsMapper.updateByPrimaryKey(goods);

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

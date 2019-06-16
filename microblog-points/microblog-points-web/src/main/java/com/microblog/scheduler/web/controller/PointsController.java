package com.microblog.scheduler.web.controller;

import com.microblog.common.module.points.PointsTypes;
import com.microblog.points.service.PointsService;
import com.microblog.scheduler.web.utils.UserUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Api(value = "/message",description = "积分 controller")
@Slf4j
@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PointsService pointsService;


    /**
     *功能描述
     * @author lgj
     * @Description  每日签到积分处理
     * @date 5/15/19
     * @param:
     * @return:
     *
    */
    public void signature(){
        try{
            pointsService.handlePoints(UserUtil.getUserId(request), PointsTypes.POINTS_ADD_TYPE_DAILY_SIGNATURE);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }
    }

}

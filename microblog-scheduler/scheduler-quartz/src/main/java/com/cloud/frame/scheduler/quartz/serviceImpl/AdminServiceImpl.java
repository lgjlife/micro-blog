package com.microblog.scheduler.quartz.serviceImpl;

import com.microblog.scheduler.quartz.dao.mapper.AdminMapper;
import com.microblog.scheduler.quartz.dao.model.Admin;
import com.microblog.scheduler.quartz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 00:19
 **/
@Service
public class AdminServiceImpl  implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public List<Admin> queryAdmin() {
        return adminMapper.selectAll();
    }
}

package com.microblog.scheduler.quartz.service;

import com.microblog.scheduler.quartz.dao.model.Admin;

import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-20 00:19
 **/
public interface AdminService {

    List<Admin> queryAdmin();
}

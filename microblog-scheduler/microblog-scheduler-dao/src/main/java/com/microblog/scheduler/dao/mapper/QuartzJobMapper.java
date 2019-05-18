package com.microblog.scheduler.dao.mapper;

import com.microblog.scheduler.dao.model.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuartzJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz_job
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz_job
     *
     * @mbg.generated
     */
    int insert(QuartzJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz_job
     *
     * @mbg.generated
     */
    QuartzJob selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz_job
     *
     * @mbg.generated
     */
    List<QuartzJob> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz_job
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(QuartzJob record);

    int deleteByGroupAndClass(@Param("jobGroup") String jobGroup,@Param("jobClass") String jobClass);
}
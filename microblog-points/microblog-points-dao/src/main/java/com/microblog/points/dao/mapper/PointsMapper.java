package com.microblog.points.dao.mapper;

import com.microblog.points.dao.model.Points;
import java.util.List;

public interface PointsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table points
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long pointsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table points
     *
     * @mbg.generated
     */
    int insert(Points record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table points
     *
     * @mbg.generated
     */
    Points selectByPrimaryKey(Long pointsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table points
     *
     * @mbg.generated
     */
    List<Points> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table points
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Points record);


    Points selectByUserId(Long userId);

}
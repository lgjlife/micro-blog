package com.microblog.points.dao.mapper;

import com.microblog.points.dao.model.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign
     *
     * @mbg.generated
     */
    int insert(Sign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign
     *
     * @mbg.generated
     */
    Sign selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign
     *
     * @mbg.generated
     */
    List<Sign> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Sign record);


    int updateByYear(@Param("sign") Sign sign,@Param("year") int year);
    Sign selectByYear(@Param("userId") Long userId,@Param("year")int year);
}
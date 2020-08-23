package com.xiu.dao;

import com.xiu.entity.Movie;
import com.xiu.entity.MovieExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieMapper {
    long countByExample(MovieExample example);

    int deleteByExample(MovieExample example);

    int insert(Movie record);

    int insertSelective(Movie record);

    List<Movie> selectByExampleWithBLOBs(MovieExample example);

    List<Movie> selectByExample(MovieExample example);

    int updateByExampleSelective(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByExampleWithBLOBs(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByExample(@Param("record") Movie record, @Param("example") MovieExample example);
}
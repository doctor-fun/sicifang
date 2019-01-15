package com.tensquare.eureka.recruit.dao;

import com.tensquare.eureka.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	public List<Recruit> findTopByStateOrderByCreatetimeDesc(String state);//Where state=? order by createtime



    public List<Recruit> findTopByStateNotOrderByCreatetimeDesc(String state);//where state !=? order by createtime,其中desc是降序

}

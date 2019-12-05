package com.discussion.qa.mapper;

import com.discussion.qa.model.Epiphany;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author by SuiDongyang
 * @date 2019/11/30 16:13
 */
@Mapper
public interface EpiphanyMapper {

    /**
     * 向epiphany表中插入数据
     * @param epiphany
     */
    @Insert("insert into tab_epiphany (title,description,tags,gmt_create,gmt_modified,creator) values (#{title},#{description},#{tags},#{gmtCreate},#{gmtModified},#{creator})")
    void shareEpiphany(Epiphany epiphany);

    /**
     * 查询epiphany表中所有数据，并以List的形式返回
     *
     * 注意：因为这里没有指定mybatis读取数据库表中数据项下划线命名与model中驼峰规则的映射关系
     *      因此可能出现无法获取model中使用驼峰规则命名的变量的值
     *      需要在application.properties中进行mybatis的相关配置
     * @return
     */
    @Select("select * from tab_epiphany")
    List<Epiphany> seekList();
}

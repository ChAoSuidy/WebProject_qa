package com.discussion.qa.mapper;

import com.discussion.qa.model.Epiphany;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by SuiDongyang
 * @date 2019/11/30 16:13
 */
@Mapper
public interface EpiphanyMapper {

    @Insert("insert into tab_epiphany (title,description,tags,gmt_create,gmt_modified,creator) values (#{title},#{description},#{tags},#{gmtCreate},#{gmtModified},#{creator})")
    void shareEpiphany(Epiphany epiphany);
}

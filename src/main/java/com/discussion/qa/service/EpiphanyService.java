package com.discussion.qa.service;

import com.discussion.qa.dto.EpiphanyDTO;
import com.discussion.qa.mapper.EpiphanyMapper;
import com.discussion.qa.mapper.UserMapper;
import com.discussion.qa.model.Epiphany;
import com.discussion.qa.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by SuiDongyang
 * @date 2019/12/5 23:04
 * <p>
 * 此service的作用是将从user表和epiphany表中的数据进行组合
 * <p>
 * 放入epiphanyDto中
 */
@Service
public class EpiphanyService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EpiphanyMapper epiphanyMapper;


    public List<EpiphanyDTO> getEpiphanyList() {

        List<Epiphany> epiphanyList = epiphanyMapper.seekList();
        List<EpiphanyDTO> epiphanyDTOList = new ArrayList<>();

        for (Epiphany epiphany : epiphanyList) {

            EpiphanyDTO epiphanyDTO = new EpiphanyDTO();
            User user = userMapper.findById(epiphany.getCreator());
            epiphanyDTO.setUser(user);
            BeanUtils.copyProperties(epiphany, epiphanyDTO);
            epiphanyDTOList.add(epiphanyDTO);
        }
        return epiphanyDTOList;
    }
}

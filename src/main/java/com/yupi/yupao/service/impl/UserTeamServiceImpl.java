package com.yupi.yupao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yupao.model.domain.UserTeam;
import com.yupi.yupao.mapper.UserTeamMapper;
import com.yupi.yupao.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author Shaosao
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2022-11-21 20:29:35
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}





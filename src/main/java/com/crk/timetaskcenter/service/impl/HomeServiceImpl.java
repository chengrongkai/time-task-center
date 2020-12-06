package com.crk.timetaskcenter.service.impl;

import com.crk.timetaskcenter.entity.SysDept;
import com.crk.timetaskcenter.entity.SysUser;
import com.crk.timetaskcenter.service.DeptService;
import com.crk.timetaskcenter.service.HomeService;
import com.crk.timetaskcenter.service.PermissionService;
import com.crk.timetaskcenter.service.UserService;
import com.crk.timetaskcenter.vo.resp.HomeRespVO;
import com.crk.timetaskcenter.vo.resp.PermissionRespNode;
import com.crk.timetaskcenter.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        SysUser sysUser = userService.getById(userId);
        UserInfoRespVO vo = new UserInfoRespVO();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.getById(sysUser.getDeptId());
            if (sysDept != null) {
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }
        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}

package com.eutwStudio.controller.systemManage;

import com.eutwStudio.dto.Result;
import com.eutwStudio.entity.ActiveUser;
import com.eutwStudio.entity.Role;
import com.eutwStudio.service.RoleService;
import com.eutwStudio.utils.Constant;
import com.eutwStudio.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ubuntu
 */
@Component
@RequestMapping("/roleDistribute")
public class RoleDistributeController {

    @Autowired
    private RoleService roleService;

    @RequestMapping()
    public String index() {
        return "systemManage/roleDistribute/list";
    }


    @RequestMapping("updateUserRole")
    @ResponseBody
    public Result updateUserRole(ActiveUser user) {
        try {
            roleService.updateUserRole(user);

            return Result.success(null, Constant.UPDATE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.failure(null, Constant.UPDATE_FAILURE);
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(PageUtil page, String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Map<String, String>> users = roleService.queryUserRoleList(page, name);
            List<Role> roles = roleService.queryAll();
            data.put("users", users);
            data.put("roles", roles);
            data.put("page", page);
            data.put("result", true);
            data.put("msg", Constant.SEARCH_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("msg", Constant.SEARCH_FAILURE);
        }

        return data;
    }

}

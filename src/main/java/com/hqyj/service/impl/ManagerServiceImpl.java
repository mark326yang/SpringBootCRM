package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqyj.pojo.Admin;
import com.hqyj.pojo.Manager;
import com.hqyj.dao.ManagerMapper;

import com.hqyj.service.ManagerService;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.management.QueryEval;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 经理表 服务实现类
 * </p>
 *
 * @author rock
 * @since 2021-10-21
 */

@Service
public class ManagerServiceImpl  implements ManagerService {
    // 注入ManageMapper
    @Autowired(required = false)
    private ManagerMapper managerMapper;

    @Override
    public HashMap<String, Object> select(Manager manager, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        // 创建分页对象
        Page<Manager> page = new Page<>(manager.getPage(),manager.getRow());
        // 构建查询条件构造器
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        // 匹配id
        queryWrapper.eq(manager.getManagerId()!=null,"manager_id",manager.getManagerId());
        // 匹配经理名
        queryWrapper.like((!StringUtils.isEmpty(manager.getManagerName()) && manager.getManagerName()!=null),
                "manager_name",manager.getManagerName()
                );
        // 显示当前经理的信息
        //Admin user = (Admin) session.getAttribute("user");
        //if (user.getManagerId()!=null){
        //    manager.setManagerId(user.getManagerId());
        //    queryWrapper.eq(user.getManagerId()!=null,"manager_id",manager.getManagerId());
        //}
        // 查询 并传入查询条件
        Page<Manager> mList = managerMapper.selectPage(page, queryWrapper);
        //当前页集合
        map.put("list",mList.getRecords());
        //总页数
        map.put("totalPage",mList.getPages());
        //总条数
        map.put("total",mList.getTotal());
        //当前页
        map.put("curPage",manager.getPage());
        if(manager.getPage()==1){
            //上一页
            map.put("prePage",1);

        }else{
            map.put("prePage",manager.getPage()-1);

        }
        if(manager.getPage()==mList.getPages()){
            //下一页
            map.put("nextPage",mList.getPages());

        }else{
            map.put("nextPage",manager.getPage()+1);

        }
        //每页显示条数
        map.put("row",manager.getRow());


        return map;
    }

    @Override
    public HashMap<String, Object> add(Manager manager) {
        HashMap<String, Object> map = new HashMap<>();
        // 新增
        int num = managerMapper.insert(manager);
        if (num > 0){
            map.put("info","保存成功");
        } else {
            map.put("info","保存失败");
        }
        return map;
    }

    /**
     * @Date: 2021/10/21
     * @param: manager:
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @Description:
     * */
    @Override
    public HashMap<String, Object> update(Manager manager) {
        HashMap<String, Object> map = new HashMap<>();
        // 修改
        int num = managerMapper.updateById(manager);
        if (num > 0){
            map.put("info","保存成功");
        } else {
            map.put("info","保存失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> del(Integer managerId) {
        HashMap<String, Object> map = new HashMap<>();
        // 删除
        int num = managerMapper.deleteById(managerId);
        if (num > 0){
            map.put("info","保存成功");
        } else {
            map.put("info","保存失败");
        }
        return map;
    }

    @Override
    public void excelManager(HttpServletResponse response) {
        OutputStream output = null;
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet();
            // 创建HSSFRow对象,先写入列名
            HSSFRow colName = sheet.createRow(0);
            // 写入 第一列的列名
            colName.createCell(0).setCellValue("编号");
            colName.createCell(1).setCellValue("姓名");
            colName.createCell(2).setCellValue("性别");
            colName.createCell(3).setCellValue("出生日期");
            colName.createCell(4).setCellValue("联系电话");
            colName.createCell(5).setCellValue("邮箱地址");
            colName.createCell(6).setCellValue("学历");
            colName.createCell(7).setCellValue("身份证号");
            colName.createCell(8).setCellValue("备注");
            // 查询所有经理信息
            List<Manager> managerList = managerMapper.selectList(null);

            for (int i = 1; i <= managerList.size(); i++) {
                // 从第二行开始写入数据
                HSSFRow row = sheet.createRow(i);
                // 编号
                row.createCell(0).setCellValue(managerList.get(i-1).getManagerId());
                // 姓名
                row.createCell(1).setCellValue(managerList.get(i-1).getManagerName());
                // 性别
                row.createCell(2).setCellValue(managerList.get(i-1).getManagerSex());
                // 出生日期
                row.createCell(3).setCellValue(managerList.get(i-1).getManagerBirthday());
                // 联系电话
                row.createCell(4).setCellValue(managerList.get(i-1).getManagerPhone());
                // 邮箱地址
                row.createCell(5).setCellValue(managerList.get(i-1).getManagerEmail());
                // 学历
                row.createCell(6).setCellValue(managerList.get(i-1).getManagerEducation());
                // 身份证号
                row.createCell(7).setCellValue(managerList.get(i-1).getManagerIdnumber());
                // 备注信息
                row.createCell(8).setCellValue(managerList.get(i-1).getComments());
            }

            // 输出Excel 文件到页面
            output = response.getOutputStream();
            String fileName = "经理表";
            // 解决中文文件名下载 编程下滑线的问题
            fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1") + "";
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
            response.setContentType("application/msexcel");
            wb.write(output);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                // 关闭流对象
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

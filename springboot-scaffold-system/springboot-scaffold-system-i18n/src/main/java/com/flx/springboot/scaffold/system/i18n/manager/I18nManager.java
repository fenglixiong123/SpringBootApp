package com.flx.springboot.scaffold.system.i18n.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseManager;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.system.i18n.dao.I18nDao;
import com.flx.springboot.scaffold.system.i18n.dto.I18nDTO;
import com.flx.springboot.scaffold.system.i18n.entity.I18nDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 17:24
 * @Description:
 */
@Service
public class I18nManager extends BaseManager<I18nDO, I18nDao> {

    public Long add(I18nDO I18nDO) throws Exception {
        return super.add(I18nDO);
    }

    public IPage<I18nDTO> queryAndPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        IPage<I18nDO> I18nDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(I18nDOList, I18nDTO.class);
    }


    public IPage<I18nDTO> queryAndPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        IPage<I18nDO> I18nDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(I18nDOList, I18nDTO.class);
    }

    public List<I18nDTO> queryI18n(Map<String, Object> query) throws Exception {
        List<I18nDO> I18nDOList = super.query(query);
        return I18nDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, I18nDTO.class)).collect(Collectors.toList());
    }

    public List<I18nDTO> queryI18nAllColumns(Map<String, Object> query) throws Exception {
        List<I18nDO> I18nDOList = super.queryAllColumns(query);
        return I18nDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, I18nDTO.class)).collect(Collectors.toList());
    }

    public List<I18nDTO> queryI18n(Object query) throws Exception {
        List<I18nDO> I18nDOList = super.query(query);
        return I18nDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, I18nDTO.class)).collect(Collectors.toList());
    }

    public Integer deleteById(Long id) throws Exception {
        return super.deleteById(id);
    }

    public Integer updateById(I18nDO i18nDO) throws Exception {
        return super.updateById(i18nDO);
    }

    public Integer updateNullById(I18nDO i18nDO) throws Exception {
        return super.updateNullById(i18nDO);
    }

    public Integer updateByCode(I18nDO i18nDO) throws Exception {
        return super.updateByCode(i18nDO,"i18nCode", i18nDO.getI18nCode());
    }

    public Integer updateNullByCode(I18nDO i18nDO) throws Exception {
        return super.updateNullByCode(i18nDO,"i18nCode", i18nDO.getI18nCode());
    }

    public I18nDTO getById(Long id) throws Exception {
        Object I18nDO = super.getById(id);
        return BeanUtils.copyProperties(I18nDO, I18nDTO.class);
    }

    public boolean isExist(String i18nCode) throws Exception {
        QueryWrapper<I18nDO> queryWrapper = getConditionBuilder().query("i18nCode", i18nCode).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public boolean isExist(Long id) throws Exception {
        QueryWrapper<I18nDO> queryWrapper = getConditionBuilder().query("id", id).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public Integer addByList(List<I18nDO> I18nDOList) throws Exception {
        try {
            return super.addByList(I18nDOList);
        } catch (Exception e) {
            return 0;
        }
    }
}

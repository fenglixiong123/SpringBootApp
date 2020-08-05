package com.flx.springboot.scaffold.system.dictionary.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.base.BaseManager;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;

import com.flx.springboot.scaffold.system.dictionary.dao.DictionaryDao;
import com.flx.springboot.scaffold.system.dictionary.dto.DictionaryDTO;
import com.flx.springboot.scaffold.system.dictionary.entity.DictionaryDO;
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
public class DictionaryManager extends BaseManager<DictionaryDO, DictionaryDao> {

    public Long add(DictionaryDO DictionaryDO) throws Exception {
        return super.add(DictionaryDO);
    }

    public IPage<DictionaryDTO> queryAndPage(Integer pageNum, Integer pageSize, Map<String, Object> query) throws Exception {
        IPage<DictionaryDO> DictionaryDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(DictionaryDOList, DictionaryDTO.class);
    }


    public IPage<DictionaryDTO> queryAndPage(Integer pageNum, Integer pageSize, Object query) throws Exception {
        IPage<DictionaryDO> DictionaryDOList = super.queryPage(pageNum, pageSize, query);
        return PageConvert.pageConvert(DictionaryDOList, DictionaryDTO.class);
    }

    public List<DictionaryDTO> queryDictionary(Map<String, Object> query) throws Exception {
        List<DictionaryDO> DictionaryDOList = super.query(query);
        return DictionaryDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, DictionaryDTO.class)).collect(Collectors.toList());
    }

    public List<DictionaryDTO> queryDictionaryAllColumns(Map<String, Object> query) throws Exception {
        List<DictionaryDO> DictionaryDOList = super.queryAllColumns(query);
        return DictionaryDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, DictionaryDTO.class)).collect(Collectors.toList());
    }

    public List<DictionaryDTO> queryDictionary(Object query) throws Exception {
        List<DictionaryDO> DictionaryDOList = super.query(query);
        return DictionaryDOList.parallelStream().map(e -> BeanUtils.copyProperties(e, DictionaryDTO.class)).collect(Collectors.toList());
    }

    public Integer deleteById(Long id) throws Exception {
        return super.deleteById(id);
    }

    public Integer updateById(DictionaryDO dictionaryDO) throws Exception {
        return super.updateById(dictionaryDO);
    }

    public Integer updateNullById(DictionaryDO dictionaryDO) throws Exception {
        return super.updateNullById(dictionaryDO);
    }

    public Integer updateByCode(DictionaryDO dictionaryDO) throws Exception {
        return super.updateByCode(dictionaryDO,"valueCode", dictionaryDO.getValueCode());
    }

    public Integer updateNullByCode(DictionaryDO dictionaryDO) throws Exception {
        return super.updateNullByCode(dictionaryDO,"valueCode", dictionaryDO.getValueCode());
    }

    public DictionaryDTO getById(Long id) throws Exception {
        Object DictionaryDO = super.getById(id);
        return BeanUtils.copyProperties(DictionaryDO, DictionaryDTO.class);
    }

    public boolean isExist(String dictionaryCode) throws Exception {
        QueryWrapper<DictionaryDO> queryWrapper = getConditionBuilder().query("dictionaryCode", dictionaryCode).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public boolean isExist(Long id) throws Exception {
        QueryWrapper<DictionaryDO> queryWrapper = getConditionBuilder().query("id", id).build(false);
        return dao.selectCount(queryWrapper)>0;
    }

    public Integer addByList(List<DictionaryDO> DictionaryDOList) throws Exception {
        try {
            return super.addByList(DictionaryDOList);
        } catch (Exception e) {
            return 0;
        }
    }
}

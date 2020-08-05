package com.flx.springboot.scaffold.system.dictionary.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.system.dictionary.dto.DictionaryDTO;
import com.flx.springboot.scaffold.system.dictionary.entity.DictionaryDO;
import com.flx.springboot.scaffold.system.dictionary.manager.DictionaryManager;
import com.flx.springboot.scaffold.system.dictionary.service.DictionaryService;
import com.flx.springboot.scaffold.system.dictionary.vo.DictionaryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: Fenglixiong
 * @Date: 2020/7/24 16:34
 * @Description:
 */
@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryManager dictionaryManager;

    private void convertVO(List<DictionaryVO> dictionaryVOS){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(DictionaryVO dictionaryVO) throws Exception {
        DictionaryDO dictionaryDO = BeanUtils.copyProperties(dictionaryVO, DictionaryDO.class);
        return dictionaryManager.add(dictionaryDO);
    }

    @Override
    public Integer deleteById(Long id) throws Exception {
        return dictionaryManager.deleteById(id);
    }

    @Override
    public Integer updateById(DictionaryVO dictionaryVO) throws Exception {
        DictionaryDO dictionaryDO = BeanUtils.copyProperties(dictionaryVO, DictionaryDO.class);
        return dictionaryManager.updateById(dictionaryDO);
    }

    @Override
    public void stateChange(List<DictionaryVO> DictionaryVOList, State state) throws Exception {

    }

    @Override
    public DictionaryVO getById(Long id) throws Exception {
        DictionaryDTO dictionaryDTO = dictionaryManager.getById(id);
        return BeanUtils.copyProperties(dictionaryDTO,DictionaryVO.class);
    }

    @Override
    public IPage<DictionaryVO> queryAndPage(QueryAndPage queryAndPage) throws Exception {
        IPage<DictionaryDTO> dictionaryDTOS = dictionaryManager.queryAndPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<DictionaryVO> dictionaryVOIPage = PageConvert.pageConvert(dictionaryDTOS,DictionaryVO.class);
        convertVO(dictionaryVOIPage.getRecords());
        return dictionaryVOIPage;
    }

    @Override
    public List<DictionaryVO> query(Map<String, Object> query) throws Exception {
        List<DictionaryDTO> dictionaryDTOS = dictionaryManager.queryDictionary(query);
        return BeanUtils.convertList(dictionaryDTOS,DictionaryVO.class);
    }

    @Override
    public List<DictionaryVO> query(Object query) throws Exception {
        List<DictionaryDTO> dictionaryDTOS = dictionaryManager.queryDictionary(query);
        return BeanUtils.convertList(dictionaryDTOS,DictionaryVO.class);
    }

    @Override
    public int addWholeByList(List<DictionaryVO> dictionaryVOList) throws Exception {
        return dictionaryManager.addByList(BeanUtils.convertList(dictionaryVOList,DictionaryDO.class));
    }

    @Override
    public int addByList(List<DictionaryVO> dictionaryVOList) throws Exception {
        return dictionaryManager.addByList(BeanUtils.convertList(dictionaryVOList,DictionaryDO.class));
    }
}

package com.flx.springboot.scaffold.system.i18n.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.flx.springboot.scaffold.common.servlet.BeanUtils;
import com.flx.springboot.scaffold.mybatis.plus.enums.State;
import com.flx.springboot.scaffold.mybatis.plus.page.PageConvert;
import com.flx.springboot.scaffold.mybatis.plus.page.QueryAndPage;
import com.flx.springboot.scaffold.system.i18n.dto.I18nDTO;
import com.flx.springboot.scaffold.system.i18n.entity.I18nDO;
import com.flx.springboot.scaffold.system.i18n.manager.I18nManager;
import com.flx.springboot.scaffold.system.i18n.service.I18nService;
import com.flx.springboot.scaffold.system.i18n.vo.I18nVO;
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
public class I18nServiceImpl implements I18nService {

    @Autowired
    private I18nManager i18nManager;

    private void convertVO(List<I18nVO> i18nVOS){

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(I18nVO i18nVO) throws Exception {
        I18nDO i18nDO = BeanUtils.copyProperties(i18nVO, I18nDO.class);
        return i18nManager.add(i18nDO);
    }

    @Override
    public Integer deleteById(Long id) throws Exception {
        return i18nManager.deleteById(id);
    }

    @Override
    public Integer updateById(I18nVO i18nVO) throws Exception {
        I18nDO i18nDO = BeanUtils.copyProperties(i18nVO, I18nDO.class);
        return i18nManager.updateById(i18nDO);
    }

    @Override
    public void stateChange(List<I18nVO> I18nVOList, State state) throws Exception {

    }

    @Override
    public I18nVO getById(Long id) throws Exception {
        I18nDTO i18nDTO = i18nManager.getById(id);
        return BeanUtils.copyProperties(i18nDTO,I18nVO.class);
    }

    @Override
    public IPage<I18nVO> queryAndPage(QueryAndPage queryAndPage) throws Exception {
        IPage<I18nDTO> i18nDTOS = i18nManager.queryAndPage(queryAndPage.getPageNum(),queryAndPage.getPageSize(),queryAndPage.getQuery());
        IPage<I18nVO> i18nVOIPage = PageConvert.pageConvert(i18nDTOS,I18nVO.class);
        convertVO(i18nVOIPage.getRecords());
        return i18nVOIPage;
    }

    @Override
    public List<I18nVO> query(Map<String, Object> query) throws Exception {
        List<I18nDTO> i18nDTOS = i18nManager.queryI18n(query);
        return BeanUtils.convertList(i18nDTOS,I18nVO.class);
    }

    @Override
    public List<I18nVO> query(Object query) throws Exception {
        List<I18nDTO> i18nDTOS = i18nManager.queryI18n(query);
        return BeanUtils.convertList(i18nDTOS,I18nVO.class);
    }

    @Override
    public int addWholeByList(List<I18nVO> i18nVOList) throws Exception {
        return i18nManager.addByList(BeanUtils.convertList(i18nVOList,I18nDO.class));
    }

    @Override
    public int addByList(List<I18nVO> i18nVOList) throws Exception {
        return i18nManager.addByList(BeanUtils.convertList(i18nVOList,I18nDO.class));
    }
}

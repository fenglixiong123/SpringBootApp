package com.flx.springboot.scaffold.system.i18n.resource;

import com.flx.springboot.scaffold.common.result.ResultResponse;
import com.flx.springboot.scaffold.system.i18n.cache.I18nCache;
import com.flx.springboot.scaffold.system.i18n.service.I18nService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author Fenglixiong
 * @Create 2020/8/2 21:36
 * @Description
 **/
@Api(tags = "国际化管理")
@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    private I18nCache i18nCache;
    @Autowired
    private I18nService i18nService;

    @GetMapping("/getFromCache")
    public ResultResponse getFromCache(String key,String language){
        try {
            return ResultResponse.success(i18nCache.getMessage(key,language));
        }catch (Exception e){
            return ResultResponse.error();
        }
    }

    @PostMapping("/query")
    public ResultResponse query(@RequestBody HashMap<String,Object> query){
        try {
            return ResultResponse.success(i18nService.query(query));
        }catch (Exception e){
            return ResultResponse.error();
        }
    }

}

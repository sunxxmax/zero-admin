package com.sunxx.biz.system.dict.controller;

import com.sunxx.biz.system.dict.bean.DictQuery;
import com.sunxx.biz.system.dict.domain.Dict;
import com.sunxx.biz.system.dict.service.DictService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dict")
public class DictController {

    @Resource
    private DictService dictService;

    /**
     * 分页列表
     */
    @PostMapping(value = "/page")
    public Page<Dict> page(Pageable pageable) {
        return dictService.page(pageable);
    }

    /**
     * 根据类型查询
     *
     * @param param 参数
     * @return 集合
     */
    @PostMapping(value = "/list/type")
    public List<Dict> list4Type(@Valid @RequestBody DictQuery param) {
        return dictService.list4Type(param.typeId());
    }
}

package com.atguigu.controller;
import com.atguigu.entity.PlatformPropertyKey;
import com.atguigu.entity.PlatformPropertyValue;
import com.atguigu.result.RetVal;
import com.atguigu.service.PlatformPropertyKeyService;
import com.atguigu.service.PlatformPropertyValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 属性表 前端控制器
 * </p>
 *
 * @author 林沛钿
 * @since 2023-04-29
 */
@RestController
@RequestMapping("/product")
@CrossOrigin
public class PlatformPropertyKeyController {
    @Autowired
    private PlatformPropertyKeyService platformPropertyKeyService;
    @Autowired
    private PlatformPropertyValueService platformPropertyValueService;
    @GetMapping("getPlatformPropertyByCategoryId/{category1Id}/{category2Id}/{category3Id}")
    public RetVal getPlatformPropertyByCategoryId(@PathVariable Long category1Id,
                                                  @PathVariable Long category2Id,
                                                  @PathVariable Long category3Id){
        List<PlatformPropertyKey> platformPropertyKeyList = platformPropertyKeyService.getPlatformPropertyByCategoryId(category1Id, category2Id, category3Id);
        return RetVal.ok(platformPropertyKeyList);
    }
    //3.保存平台属性 http://127.0.0.1:8000/product/savePlatformProperty
    @PostMapping("savePlatformProperty")
    public RetVal savePlatformProperty(@RequestBody PlatformPropertyKey platformPropertyKey) {
        boolean flag = platformPropertyKeyService.savePlatformProperty(platformPropertyKey);
        if (flag) {
            return RetVal.ok();
        } else {
            return RetVal.fail();
        }
    }
    //修改查询回显http://127.0.0.1:8000/product/getPropertyValueByPropertyKeyId/4
    @GetMapping("getPropertyValueByPropertyKeyId/{propertyKeyId}")
    public RetVal getPropertyValueByPropertyKeyId(@PathVariable Long propertyKeyId){
        LambdaQueryWrapper<PlatformPropertyValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlatformPropertyValue::getPropertyKeyId,propertyKeyId);
        List<PlatformPropertyValue> propertyValueList = platformPropertyValueService.list(queryWrapper);
        return RetVal.ok(propertyValueList);
    }



}


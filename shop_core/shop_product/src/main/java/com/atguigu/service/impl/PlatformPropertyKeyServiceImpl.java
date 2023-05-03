package com.atguigu.service.impl;

import com.atguigu.entity.PlatformPropertyKey;
import com.atguigu.entity.PlatformPropertyValue;
import com.atguigu.mapper.PlatformPropertyKeyMapper;
import com.atguigu.service.PlatformPropertyKeyService;
import com.atguigu.service.PlatformPropertyValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author 林沛钿
 * @since 2023-04-29
 */
@Service
public class PlatformPropertyKeyServiceImpl extends ServiceImpl<PlatformPropertyKeyMapper, PlatformPropertyKey> implements PlatformPropertyKeyService {
    @Autowired
    private PlatformPropertyKeyMapper platformPropertyKeyMapper;
    @Autowired
    private PlatformPropertyValueService platformPropertyValueService;

    @Override
    public List<PlatformPropertyKey> getPlatformPropertyByCategoryId(Long category1Id, Long category2Id, Long category3Id) {
        return platformPropertyKeyMapper.getPlatformPropertyByCategoryId(category1Id,category2Id,category3Id);
    }

    @Override
    public boolean savePlatformProperty(PlatformPropertyKey platformPropertyKey) {
        Long propertyKeyId = platformPropertyKey.getId();
        if (propertyKeyId!=null){
            baseMapper.updateById(platformPropertyKey);
            LambdaQueryWrapper<PlatformPropertyValue> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(PlatformPropertyValue::getPropertyKeyId,propertyKeyId);
            platformPropertyValueService.remove(wrapper);//修改直接删除value，在下面重新保存即可
        }else {
            baseMapper.insert(platformPropertyKey);
        }
        List<PlatformPropertyValue> propertyValueList = platformPropertyKey.getPropertyValueList();
        if (!CollectionUtils.isEmpty(propertyValueList)){
            for (PlatformPropertyValue platformPropertyValue : propertyValueList) {
                platformPropertyValue.setPropertyKeyId(platformPropertyKey.getId());
            }
            platformPropertyValueService.saveBatch(propertyValueList);
        }
        return true;
    }
}

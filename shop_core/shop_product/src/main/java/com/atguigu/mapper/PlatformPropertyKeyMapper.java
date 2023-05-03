package com.atguigu.mapper;

import com.atguigu.entity.PlatformPropertyKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;

import java.util.List;

/**
 * <p>
 * 属性表 Mapper 接口
 * </p>
 *
 * @author 林沛钿
 * @since 2023-04-29
 */
public interface PlatformPropertyKeyMapper extends BaseMapper<PlatformPropertyKey> {

    List<PlatformPropertyKey> getPlatformPropertyByCategoryId(@Param("category1Id") Long category1Id, @Param("category2Id")  Long category2Id, @Param("category3Id")  Long category3Id);
}

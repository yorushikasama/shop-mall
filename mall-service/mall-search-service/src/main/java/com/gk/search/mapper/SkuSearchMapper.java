package com.gk.search.mapper;

import com.gk.search.model.SkuEs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {

}

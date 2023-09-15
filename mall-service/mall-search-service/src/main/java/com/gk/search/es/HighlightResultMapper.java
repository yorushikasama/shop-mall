package com.gk.search.es;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.ByteBufferReference;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.nio.ByteBuffer;
import java.util.Map;

public class HighlightResultMapper extends DefaultResultMapper {
    /***
     * 处理结果集
     * 映射转换，将非高亮数据替换成高亮数据
     */
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        //所有数据
        for (SearchHit hit : response.getHits()) {
            //当前单条数据
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            //高亮数据
            for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
                String key = entry.getKey();
                if (sourceMap.containsKey(key)) {
                    Text[] fragments = entry.getValue().getFragments();
                    sourceMap.put(key, transTextArrayToString(fragments));
                }
            }
            hit.sourceRef(new ByteBufferReference(ByteBuffer.wrap(JSONObject.toJSONString(sourceMap).getBytes())));
        }
        return super.mapResults(response, clazz, pageable);
    }

    /***
     * 拼接数据碎片
     * Text转成字符串
     */
    private String transTextArrayToString(Text[] fragments) {
        if (null == fragments) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (Text fragment : fragments) {
            buffer.append(fragment.string());
        }
        return buffer.toString();
    }
}
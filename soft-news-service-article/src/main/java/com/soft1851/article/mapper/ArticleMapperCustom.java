package com.soft1851.article.mapper;

import com.soft1851.my.mapper.MyMapper;
import com.soft1851.pojo.Article;

/**
 * @ClassName $(Name)
 * @Description TODO
 * @Author qin jian
 * @Date 2020/11/25
 * @Version 1.0
 **/
public interface ArticleMapperCustom extends MyMapper<Article> {
    /**
     *  更新文章发布状态：定时->即时发布
     */
    void updateAppointToPublish();
}

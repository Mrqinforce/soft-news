package com.soft1851.article.task;

import com.soft1851.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author qj
 * @description TODO
 * @Data 2020/11/25
 */
@Configuration
@EnableScheduling
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskPublishArticle {
    private final ArticleService articleService;

    /**
     * 添加定时任务，注明定时任务表达式，这里表达式的含义是每10秒执行一次
     * https://qqe2.com/cron
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void publishArticle(){
        System.out.println("执行定时任务："+ LocalDateTime.now());
        articleService.updateAppointToPublish();
    }
}
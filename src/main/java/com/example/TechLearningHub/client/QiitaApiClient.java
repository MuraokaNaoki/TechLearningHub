package com.example.TechLearningHub.client;

import com.example.TechLearningHub.dto.QiitaArticleDTO;
import com.example.TechLearningHub.exception.QiitaApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class QiitaApiClient {
    private final RestTemplate qiitaRestTemplate;

    /**
     * タグで記事を検索
     *
     * @param tag タグ名
     * @param page ページ番号（1から開始）
     * @param perPage 1ページあたりの件数
     * @return 記事のリスト
     * @throws QiitaApiException API呼び出しに失敗した場合
     */

    public List<QiitaArticleDTO> searchByTag(String tag, int page, int perPage) {
        try{
            String url = UriComponentsBuilder.fromPath("/tags/{tag}/items")
                    .queryParam("page", page)
                    .queryParam("per_page",perPage)
                    .buildAndExpand(tag)
                    .toUriString();
            log.debug("Qiita APIからの呼び出し:GET {}",url);
            QiitaArticleDTO[] articles = qiitaRestTemplate.getForObject(
                    url,
                    QiitaArticleDTO[].class
            );

            if(articles==null || articles.length==0){
                log.warn("Qiita APIから空の情報が返されました。: tag={}",tag);
                return Collections.emptyList();
            }

            log.info("Qiita APIから記事を取得: tag={}, count={}",tag,articles.length);
            return Arrays.asList(articles);
        }catch (RestClientException e){
            log.error("Qiita API呼び出しエラー: tag={}, error={}",tag,e.getMessage());
            throw new QiitaApiException(
                    "Qiita APIからの記事取得に失敗しました:" + e.getMessage(),e
            );

        }
    }

    /**
     * キーワードで記事を検索
     *
     * @param query 検索キーワード
     * @param page ページ番号（1から開始）
     * @param perPage 1ページあたりの件数
     * @return 記事のリスト
     * @throws QiitaApiException API呼び出しに失敗した場合
     */

    public List<QiitaArticleDTO> searchByQuery(String query, int page, int perPage) {
        try{
            String url = UriComponentsBuilder.fromPath("/items")
                    .queryParam("query", query)
                    .queryParam("page", page)
                    .queryParam("perPage", perPage)
                    .toUriString();

            log.info("Qiita API呼び出し: GET {}",url);

            QiitaArticleDTO[] articleDTOS = qiitaRestTemplate.getForObject(
                    url,
                    QiitaArticleDTO[].class);

            if(articleDTOS==null || articleDTOS.length==0){
                log.warn("Qiita APIから空の情報が返されました。 query={}",query);
                return Collections.emptyList();
            }

            log.info("Qiita APIから記事を取得: query={}, count={}",query,articleDTOS.length);
            return Arrays.asList(articleDTOS);



        } catch (RestClientException e){
            log.error("Qiita API呼び出しエラー: query={}, error={}",query,e.getMessage());
            throw new QiitaApiException(
                    "Qiita API呼び出しに失敗しました。:" + e.getMessage(),e
            );
        }
    }

}

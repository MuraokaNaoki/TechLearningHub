package com.example.TechLearningHub.service;

import com.example.TechLearningHub.client.QiitaApiClient;
import com.example.TechLearningHub.dto.QiitaArticleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QiitaService {

    private final QiitaApiClient qiitaApiClient;

    private static final int DEFAULT_PER_PAGE = 20;
    private static final int MAX_PER_PAGE = 100;
    private static final int MIN_PAGE = 1;

    /**
     * タグで記事を取得
     *
     * @param tag タグ名
     * @param page ページ番号（nullの場合は1）
     * @param perPage 1ページあたりの件数（nullの場合は20）
     * @return 記事のリスト
     */

    public List<QiitaArticleDTO> getArticleByTag(String tag, Integer page, Integer perPage) {
        //パラメータのバリデーションと正規化
        int validPage = (page != null && page >= MIN_PAGE) ? page : MIN_PAGE;
        int validPerPage = (perPage != null && perPage > 0 && perPage <= MAX_PER_PAGE)
                ? perPage
                : DEFAULT_PER_PAGE;

        log.info("タグで記事を取得: tag={}, page={}, perPage={}",
                tag, validPage, validPerPage);

        return qiitaApiClient.searchByTag(tag, validPage, validPerPage);
    }

    /**
     * キーワードで記事を検索
     *
     * @param query 検索キーワード
     * @param page ページ番号（nullの場合は1）
     * @param perPage 1ページあたりの件数（nullの場合は20）
     * @return 記事のリスト
     */
    public List<QiitaArticleDTO> searchArticles(String query, Integer page, Integer perPage) {
        // パラメータのバリデーションと正規化
        int validPage = (page != null && page >= MIN_PAGE) ? page : MIN_PAGE;
        int validPerPage = (perPage != null && perPage > 0 && perPage <= MAX_PER_PAGE)
                ? perPage
                : DEFAULT_PER_PAGE;

        log.info("キーワードで記事を検索: query={}, page={}, perPage={}", query, validPage, validPerPage);

        return qiitaApiClient.searchByQuery(query, validPage, validPerPage);
    }

}

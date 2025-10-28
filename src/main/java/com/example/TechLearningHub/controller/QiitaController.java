package com.example.TechLearningHub.controller;


import com.example.TechLearningHub.dto.QiitaArticleDTO;
import com.example.TechLearningHub.service.QiitaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/qiita")
@RestController
@RequiredArgsConstructor
public class QiitaController {
    private final QiitaService qiitaService;

    /**
     * タグで記事を取得
     *
     * GET /api/qiita/articles/by-tag?tag=SpringBoot&page=1&perPage=20
     *
     * @param tag タグ名（必須）
     * @param page ページ番号（オプション、デフォルト1）
     * @param perPage 1ページあたりの件数（オプション、デフォルト20）
     * @return 記事のリスト
     */
    @GetMapping("/articles/by-tag")
    public ResponseEntity<List<QiitaArticleDTO>> getArticleByTag(
            @RequestParam String tag,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage) {

        log.info("タグで記事取得リクエスト: tag={}, page={}, perPage={}",
                tag, page, perPage);

        List<QiitaArticleDTO> articles = qiitaService.getArticleByTag(tag, page, perPage);

        return ResponseEntity.ok(articles);
    }

    /**
     * キーワードで記事を検索
     *
     * GET /api/qiita/articles/search?query=React&page=1&perPage=20
     *
     * @param query 検索キーワード（必須）
     * @param page ページ番号（オプション、デフォルト1）
     * @param perPage 1ページあたりの件数（オプション、デフォルト20）
     * @return 記事のリスト
     */
    @GetMapping("/articles/search")
    public ResponseEntity<List<QiitaArticleDTO>> searchArticles(
            @RequestParam String query,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage){
        log.info("キーワード検索リクエスト： query={}, page={}, perPage={}", query, page, perPage);

        List<QiitaArticleDTO> articles = qiitaService.searchArticles(query, page, perPage);
        return ResponseEntity.ok(articles);
    }
}

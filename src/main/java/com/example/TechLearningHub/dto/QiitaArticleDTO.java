package com.example.TechLearningHub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QiitaArticleDTO {
    private String id;
    private String title;
    @JsonProperty("like_count")
    private Integer likeCount;

    @JsonProperty("create_at")
    private LocalDateTime createAt;

    @JsonProperty("update_at")
    private LocalDateTime updateAt;

    private List<TagDTO> tags;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagDTO {
        private String name;
        private List<String> versions;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDTO {
        private String id;

        @JsonProperty("profile_image_url")
        private String profileImageUrl;

        private String name;
    }


}

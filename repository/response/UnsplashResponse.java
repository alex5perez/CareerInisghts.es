package com.etrivium.backend.repository.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UnsplashResponse {
    private int total;
    private int total_pages;
    private List<Result> results;

    @Data
    @NoArgsConstructor
    public static class Result {
        private String id;
        private String created_at;
        private int width;
        private int height;
        private String color;
        private String blur_hash;
        private int likes;
        private boolean liked_by_user;
        private String description;
        private User user;
        private List<Object> current_user_collections; // Assuming it's an empty array
        private Urls urls;
        private Links links;
    }

    @Data
    @NoArgsConstructor
    public static class User {
        private String id;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private String instagram_username;
        private String twitter_username;
        private String portfolio_url;
        private ProfileImage profile_image;
        private Links links;
    }

    @Data
    @NoArgsConstructor
    public static class ProfileImage {
        private String small;
        private String medium;
        private String large;
    }

    @Data
    @NoArgsConstructor
    public static class Urls {
        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;
    }

    @Data
    @NoArgsConstructor
    public static class Links {
        private String self;
        private String html;
        private String download;
        private String photos; // Only present in User.Links
        private String likes; // Only present in User.Links
    }
}

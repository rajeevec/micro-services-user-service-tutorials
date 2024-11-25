package com.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "RATINGSERVICE")
public interface FeignClientRatingService {
}

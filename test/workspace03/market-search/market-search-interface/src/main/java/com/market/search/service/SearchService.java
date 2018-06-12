package com.market.search.service;

import com.market.pojo.SearchResult;

public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}

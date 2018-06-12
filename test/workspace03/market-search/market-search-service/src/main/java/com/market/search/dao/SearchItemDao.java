package com.market.search.dao;

import com.market.pojo.SearchItem;

import java.util.List;

public interface SearchItemDao {

    List<SearchItem> getItemList();

    SearchItem getItemById(long itemId);
}

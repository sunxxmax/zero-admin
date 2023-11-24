package com.sunxx.biz.luke.purchase.service;

import com.sunxx.biz.luke.purchase.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PurchaseService {

    Page<Purchase> page(Pageable pageable);

    void create(Purchase source);

    List<Purchase> list4Usable();
}

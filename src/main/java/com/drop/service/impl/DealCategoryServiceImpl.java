package com.drop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drop.dao.IDealCategoryDao;
import com.drop.dao.domain.DealCategory;
import com.drop.service.IDealCategoryService;

@Service
public class DealCategoryServiceImpl implements IDealCategoryService {

	@Autowired
	private IDealCategoryDao categoryDao;

	@Override
	@Transactional
	public List<DealCategory> getAllDealCategories() {

		List<DealCategory> categories = null;

		if (null == CacheManager.getInstance().getCache("categoryCache")
				|| null == CacheManager.getInstance().getCache("categoryCache")
						.get("categories")) {

			if (null == CacheManager.getInstance().getCache("categoryCache")) {
				Cache categoryCache = new Cache("categoryCache", 2,
						MemoryStoreEvictionPolicy.LFU, false, null, true, 1800,
						500, false, 0, null);

				CacheManager.getInstance().addCache(categoryCache);
				CacheManager.getInstance().addCache(categoryCache);
			}

			categories = categoryDao.findAll();
			Cache categoryCache = CacheManager.getInstance().getCache(
					"categoryCache");

			categoryCache.put(new Element("categories", categories));

		} else {
			Cache categoryCache = CacheManager.getInstance().getCache(
					"categoryCache");
			Element e = categoryCache.get("categories");
			categories = (List<DealCategory>) e.getObjectValue();
			/*
			 * System.out.println("get from to cache size is " +
			 * categoryCache.getSize());
			 */
		}

		return categories;
	}

	@Override
	@Transactional
	public String getCategoryImageName(String categoryName) {
		String imageName = null;

		if (null == CacheManager.getInstance().getCache("categoryCache")
				|| null == CacheManager.getInstance().getCache("categoryCache")
						.get("categoryImages")) {

			if (null == CacheManager.getInstance().getCache("categoryCache")) {
				Cache categoryCache = new Cache("categoryCache", 2,
						MemoryStoreEvictionPolicy.LFU, false, null, true, 1800,
						500, false, 0, null);

				CacheManager.getInstance().addCache(categoryCache);
			}

			List<DealCategory> categories = getAllDealCategories();
			Map<String, String> categoryImages = new HashMap<>();
			Cache categoryCache = CacheManager.getInstance().getCache(
					"categoryCache");

			for (DealCategory category : categories) {
				categoryImages.put(category.getName(),
						category.getPicturePath());
			}

			categoryCache.put(new Element("categoryImages", categoryImages));

		}
		Cache categoryCache = CacheManager.getInstance().getCache(
				"categoryCache");
		Element e = categoryCache.get("categoryImages");
		Map<String, String> categoryImages = (Map<String, String>) e
				.getObjectValue();
		imageName = categoryImages.get(categoryName);

		/*
		 * System.out.println("get from to cache size is " +
		 * categoryCache.getSize());
		 */

		return imageName;
	}
}

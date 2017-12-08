package cn.raven.financial.yellowcow.service.impl;

import cn.raven.financial.yellowcow.common.bean.request.GoodBean;
import cn.raven.financial.yellowcow.dao.entity.Good;
import cn.raven.financial.yellowcow.dao.repository.GoodRepository;
import cn.raven.financial.yellowcow.service.GoodService;
import cn.raven.financial.yellowcow.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.raven.financial.yellowcow.util.DateUtil.FULL_CHINESE_PATTERN;
import static cn.raven.financial.yellowcow.util.DateUtil.getEndOfMonth;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:56 2017/12/5
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private EntityManager entityManager;

    @Value("${query.page.size}")
    private Integer pageSize;

    @Transactional
    @Override
    public Good newGood(GoodBean bean) {
        Good good = Good.builder().build();
        BeanUtils.copyProperties(bean, good);
        good.setCreateTime(new Date());
        return goodRepository.save(good);
    }

    @Override
    public Page<Good> goodsInDate(Integer page, Date date) {
        Sort sort = new Sort(Sort.Direction.DESC, "level");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        Page<Good> pages = goodRepository.findBySaleTimeBetween(DateUtil.getStartOfDate(date), DateUtil.getEndOfDate(date), pageable);
        return pages;
    }

    @Override
    public List<Good> findGoods(GoodBean goodBean) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("from Good where ");
        Field[] fields = GoodBean.class.getDeclaredFields();
        Map<String, String> valueMap = new HashMap<>();
        Map<String, String> typeMap = new HashMap<>();
        for (Field field : fields) {
            try {
                String fieldName = field.getName();
                String fieldType = field.getType().getSimpleName();
                String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = goodBean.getClass().getMethod(getter);
                Object obj = method.invoke(goodBean);
                if (obj == null) {
                    continue;
                }
                String value = null;
                if ("String".equals(fieldType)) {
                    value = (String) obj;
                } else if ("Date".equals(fieldType)) {
                    value = DateUtil.formatDate((Date) obj, FULL_CHINESE_PATTERN);
                } else if ("Integer".equals(fieldType)
                        || "int".equals(fieldType)) {
                    value = String.valueOf((Integer) obj);
                } else if ("Long".equalsIgnoreCase(fieldType)) {
                    value = String.valueOf((Long) obj);
                } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
                    value = ((BigDecimal) obj).toString();
                }
                if (StringUtils.isNotBlank(value)) {
                    if (!valueMap.isEmpty()) {
                        sqlBuffer.append(" and ");
                    }
                    sqlBuffer.append(fieldName).append(" = ").append(":").append(fieldName).append(" ");
                }
                valueMap.put(fieldName, value);
                typeMap.put(fieldName, fieldType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (valueMap.isEmpty()) {
            return null;
        }
        Query query = entityManager.createQuery(sqlBuffer.toString());
        for (Map.Entry<String, String> entry : valueMap.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            if ("String".equals(typeMap.get(field))) {
                query.setParameter(field, value);
            } else if ("Date".equals(typeMap.get(field))) {
                query.setParameter(field, DateUtil.parseDate(value, FULL_CHINESE_PATTERN));
            } else if ("Integer".equals(typeMap.get(field))) {
                query.setParameter(field, Integer.valueOf(value));
            } else if ("Long".equalsIgnoreCase(typeMap.get(field))) {
                query.setParameter(field, Long.valueOf(value));
            } else if ("BigDecimal".equalsIgnoreCase(typeMap.get(field))) {
                query.setParameter(field, new BigDecimal(value));
            }
        }
        List<Good> goods = query.getResultList();
        return goods;
    }


}

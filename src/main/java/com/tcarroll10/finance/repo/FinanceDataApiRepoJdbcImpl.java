package com.tcarroll10.finance.repo;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.tcarroll10.finance.utils.ParamsMapHelper;

/**
 * Repository class for Finance data api service.
 * 
 * @author tom carroll
 * @version 2023-12-27
 */

@Repository

@Primary
public class FinanceDataApiRepoJdbcImpl implements FinanceDataApiRepo {

  private NamedParameterJdbcTemplate jdbcTemplate;

  private static final Logger LOG = LogManager.getLogger(FinanceDataApiRepoJdbcImpl.class);

  /**
   * Constructor for controller allows service injection.
   * 
   * @param jdbcTemplate NamedParameterJdbcTemplate used for querying the database.
   */

  public FinanceDataApiRepoJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate) {

    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Map<String, Object>> getData(String dataset) {
    LOG.info("getData on {} table with no parameters called", dataset);

    String sql = "SELECT * FROM " + dataset;

    LOG.info("Sql statement {}", sql);

    return jdbcTemplate.queryForList(sql, new MapSqlParameterSource());

  }

  @Override
  public List<Map<String, Object>> getData(String dataset, Map<String, String> paramsMap) {

    String sql = ParamsMapHelper.processParamsMapToSql(dataset, paramsMap);

    LOG.info("Sql statement with parameters:  {}", sql);

    List<Map<String, Object>> resultList =
        jdbcTemplate.queryForList(sql, new MapSqlParameterSource());

    // Log the size of the result list
    LOG.info("Result List Size: {}", resultList.size());

    // Iterate through each row (map) and log its content
    for (int i = 0; i < resultList.size(); i++) {
      LOG.info("Row {} content: {}", i, resultList.get(i));
    }
    return resultList;

  }

}

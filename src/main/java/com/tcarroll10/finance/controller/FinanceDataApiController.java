package com.tcarroll10.finance.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcarroll10.finance.service.FinanceDataApiService;

/**
 * RestController class for Finance data api service.
 * 
 * @author tom carroll
 * @version 2023-12-27
 */

@RequestMapping(path = "/api/v2", produces = "application/json")

@RestController
public class FinanceDataApiController {

    private static final Logger LOG = LogManager
            .getLogger(FinanceDataApiController.class);

    private final FinanceDataApiService service;

    /**
     * Constructor for controller allows service injection.
     * 
     * @param service Command-line arguments.
     */

    public FinanceDataApiController(FinanceDataApiService service) {

        this.service = service;
    }

    /**
     * Main controller method.
     * 
     * @param dataset is name of table or dataset used in request.
     * @param paramsMap is name of Map of request parameters. Comes in form of
     * "param=<>". e.g., ?fields=record_date
     * @return returns a response entity with either data or an error message
     */

    @GetMapping("/{dataset}")
    public ResponseEntity<?> getSecurity(
            @PathVariable("dataset") String dataset,
            @RequestParam(required = false) Map<String, String> paramsMap) {

        LOG.info("dataset: {}", dataset);
        LOG.info("params: {}", paramsMap);

        if (paramsMap != null && !paramsMap.isEmpty()) {

            return service.validateRequestInput(dataset, paramsMap);
        } else {

            return service.processRequestNoParams(dataset);
        }

    }

}
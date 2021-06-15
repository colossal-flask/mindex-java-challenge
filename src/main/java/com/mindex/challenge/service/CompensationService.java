package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(String id, int salary, String date);
    Compensation read(String id);
}

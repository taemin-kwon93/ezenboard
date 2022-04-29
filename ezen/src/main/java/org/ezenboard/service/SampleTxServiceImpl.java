package org.ezenboard.service;

import org.ezenboard.mapper.SampleMapper1;
import org.ezenboard.mapper.SampleMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {
	
	@Setter(onMethod_ = {@Autowired})
	private SampleMapper1 mapper1;
	
	@Setter(onMethod_ = {@Autowired})
	private SampleMapper2 mapper2;
	
	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	} 
	
	@Transactional
	@Override
	public void insertTest(String testCol) {
		mapper1.insert1(testCol); 
		mapper2.insert2(testCol);
	}

}

package org.ezenboard.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper2 {

	//@Insert("insert into tbl_sample2 (col2) values (#{col2})")
	public void insert2(String col2);
	
}

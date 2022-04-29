package org.ezenboard.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper1 {
	
	//@Insert("insert into tbl_sample1 (col1) values (#{col1})")
	public void insert1(String col1);

}
